package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.androidTestImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import br.com.jonathanarodr.playmovie.gradlebuild.config.ModuleConfig
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidAppicationConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureBuildTypeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinAndroidConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureSourceConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureTestRetryConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import br.com.jonathanarodr.playmovie.gradlebuild.testImplementation
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.application",
                "org.gradle.android.cache-fix",
                "org.gradle.test-retry",
                "org.jetbrains.kotlin.android",
                "kotlin-parcelize",
                "playmovie.codestyle",
                "playmovie.codecoverage",
            )

            extensions.configure<ApplicationExtension> {
                configureAndroidAppicationConvention(this)
                configureBuildTypeConvention(this)
                configureKotlinAndroidConvention(this)
                configureUnitTestConvention(this)
                configureAndroidTestConvention(this)

                namespace = AndroidConfig.APPLICATION_ID
                testNamespace = "${AndroidConfig.APPLICATION_ID}.test"

                dependenciesInfo {
                    includeInApk = true
                    includeInBundle = true
                }
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureSourceConvention(this)
            }

            tasks.withType<Test>().configureEach {
                configureTestRetryConvention()
            }

            val libs = extensions.libs

            dependencies {
                implementation(platform(libs.findLibrary("koin-bom").get()))
                implementation(libs.findLibrary("koin-android").get())
                implementation(libs.findLibrary("timber").get())

                testImplementation(project(ModuleConfig.testing))
                androidTestImplementation(project(ModuleConfig.testing))
            }
        }
    }
}
