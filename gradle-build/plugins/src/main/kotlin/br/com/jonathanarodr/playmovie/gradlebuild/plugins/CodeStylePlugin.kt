package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import br.com.jonathanarodr.playmovie.gradlebuild.config.PlatformConfig
import br.com.jonathanarodr.playmovie.gradlebuild.detektPlugins
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class CodeStylePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val platformConfig = PlatformConfig()

        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")

            extensions.configure<DetektExtension> {
                config = files("${project.rootDir}/tools/linters/detekt-rules.yml")
                allRules = false
                parallel = true
                ignoredBuildTypes = listOf("release")
                source = files(
                    AndroidConfig.JAVA_SOURCE_DIR,
                    AndroidConfig.JAVA_SOURCE_DIR_TEST,
                    AndroidConfig.JAVA_SOURCE_DIR_SHARED_TEST,
                    AndroidConfig.JAVA_SOURCE_DIR_ANDROID_TEST,
                    AndroidConfig.KOTLIN_SOURCE_DIR,
                    AndroidConfig.KOTLIN_SOURCE_DIR_TEST,
                    AndroidConfig.KOTLIN_SOURCE_DIR_SHARED_TEST,
                    AndroidConfig.KOTLIN_SOURCE_DIR_ANDROID_TEST,
                )
            }

            tasks.withType<Detekt>().configureEach {
                jvmTarget = platformConfig.javaVersion.toString()
            }

            tasks.withType<DetektCreateBaselineTask>().configureEach {
                jvmTarget = platformConfig.javaVersion.toString()
            }

            disableDetektOnCheck()

            val libs = extensions.libs

            dependencies {
                detektPlugins(libs.findLibrary("detekt-formatting").get())
                detektPlugins(libs.findLibrary("detekt-compose").get())
            }
        }
    }

    private fun Project.disableDetektOnCheck() {
        tasks.named("check").configure {
            this.setDependsOn(
                this.dependsOn.filterNot {
                    it is TaskProvider<*> && it.name == "detekt"
                }
            )
        }
    }
}
