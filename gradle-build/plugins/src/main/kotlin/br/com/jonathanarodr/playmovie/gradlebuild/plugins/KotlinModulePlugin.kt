package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.LibraryDependency
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.TestDependency
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import br.com.jonathanarodr.playmovie.gradlebuild.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "playmovie.kotlin.library",
                "playmovie.codestyle",
                "playmovie.codecoverage",
            )
            dependencies {
                implementation(LibraryDependency.KOIN)
                implementation(LibraryDependency.TIMBER)
                implementation(LibraryDependency.GSON)

                implementation(LibraryDependency.COROUTINES_CORE)

                testImplementation(TestDependency.JUNIT)
                testImplementation(TestDependency.JUNIT_EXT)
                testImplementation(TestDependency.TRUTH_CORE)
                testImplementation(TestDependency.TRUTH_EXT)
                testImplementation(TestDependency.MOCKK)
                testImplementation(TestDependency.COROUTINES)
            }
        }
    }
}
