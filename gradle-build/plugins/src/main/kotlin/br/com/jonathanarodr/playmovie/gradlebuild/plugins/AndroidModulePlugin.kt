package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.androidTestImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.androidTestUtil
import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.config.Modules
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import br.com.jonathanarodr.playmovie.gradlebuild.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "playmovie.android.library",
                "playmovie.codestyle",
                "playmovie.codecoverage",
                "androidx.navigation.safeargs.kotlin",
            )

            val libs = extensions.libs

            dependencies {
                implementation(libs.findLibrary("koin").get())
                implementation(libs.findLibrary("timber").get())
                implementation(libs.findLibrary("gson").get())

                implementation(libs.findLibrary("androidx-core").get())
                implementation(libs.findLibrary("androidx-appcompat").get())

                implementation(libs.findLibrary("androidx-lifecycle-viewmodel").get())
                implementation(libs.findLibrary("androidx-lifecycle-livedata").get())
                implementation(libs.findLibrary("androidx-lifecycle-runtime").get())

                implementation(libs.findLibrary("kotlinx-coroutines-core").get())
                implementation(libs.findLibrary("kotlinx-coroutines-android").get())
                implementation(libs.findLibrary("kotlinx-coroutines-playservices").get())

                androidTestUtil(libs.findLibrary("androidx-test-orchestrator").get())

                testImplementation(project(Modules.TESTING))
                androidTestImplementation(project(Modules.TESTING))
            }
        }
    }
}
