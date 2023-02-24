package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.libs
import br.com.jonathanarodr.playmovie.gradlebuild.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class KotlinModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "playmovie.kotlin.library",
                "playmovie.codestyle",
                "playmovie.codecoverage",
            )

            val libs = extensions.libs

            dependencies {
                implementation(libs.findLibrary("koin").get())
                implementation(libs.findLibrary("timber").get())
                implementation(libs.findLibrary("gson").get())

                implementation(libs.findLibrary("kotlinx-coroutines-core").get())

                testImplementation(project(":testing"))
            }
        }
    }
}
