package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.androidTestImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureComposeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.debugImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            extensions.configure<LibraryExtension> {
                configureComposeConvention(this)
            }

            val libs = extensions.libs

            dependencies {
                implementation(platform(libs.findLibrary("androidx-compose-bom").get()))
                implementation(libs.findLibrary("androidx-compose-runtime").get())
                implementation(libs.findLibrary("androidx-compose-runtime-livedata").get())
                implementation(libs.findLibrary("androidx-compose-runtime-tracing").get())
                implementation(libs.findLibrary("androidx-compose-ui").get())
                implementation(libs.findLibrary("androidx-compose-foundation").get())
                implementation(libs.findLibrary("androidx-compose-material3").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
                implementation(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
                implementation(libs.findLibrary("androidx-activity-compose").get())
                implementation(libs.findLibrary("coil-compose").get())

                debugImplementation(libs.findLibrary("androidx-compose-ui-tooling").get())
                debugImplementation(libs.findLibrary("androidx-compose-ui-tooling-preview").get())

                androidTestImplementation(platform(libs.findLibrary("androidx-compose-bom").get()))
            }
        }
    }
}
