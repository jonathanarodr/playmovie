package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.config.PlatformConfig
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.LintDependency
import br.com.jonathanarodr.playmovie.gradlebuild.detektPlugins
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

class CodeStylePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val platformConfig = PlatformConfig()

        with(target) {
            pluginManager.apply("io.gitlab.arturbosch.detekt")
            extensions.configure<DetektExtension> {
                config = files("${project.rootDir}/tools/linters/detekt-rules.yml")
                allRules = false
                parallel = true
                ignoreFailures = true
            }
            tasks.withType<Detekt>().configureEach {
                jvmTarget = platformConfig.javaVersion.toString()
            }
            tasks.withType<DetektCreateBaselineTask>().configureEach {
                jvmTarget = platformConfig.javaVersion.toString()
            }
            dependencies {
                detektPlugins(LintDependency.DETEKT_FORMATTING)
            }
        }
    }
}
