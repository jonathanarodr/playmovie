package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.androidTestImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidAppicationConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureBuildTypeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureSourceConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import br.com.jonathanarodr.playmovie.gradlebuild.testImplementation
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.application",
                "kotlin-android",
                "kotlin-parcelize",
                "playmovie.codestyle",
                "playmovie.codecoverage",
            )
            extensions.configure<ApplicationExtension> {
                configureAndroidAppicationConvention(this)
                configureBuildTypeConvention(this)
                configureKotlinConvention(this)
                configureUnitTestConvention(this)
                configureAndroidTestConvention(this)

                namespace = AndroidConfig.APPLICATION_ID

                dependenciesInfo {
                    includeInApk = true
                    includeInBundle = true
                }
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureSourceConvention(this)
            }

            val libs = extensions.libs

            dependencies {
                implementation(libs.findLibrary("koin").get())
                implementation(libs.findLibrary("timber").get())

                testImplementation(project(":testing"))
                androidTestImplementation(project(":testing"))
            }
        }
    }
}
