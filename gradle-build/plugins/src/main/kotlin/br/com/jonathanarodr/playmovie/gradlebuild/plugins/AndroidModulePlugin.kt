package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.androidTestImplementation
import br.com.jonathanarodr.playmovie.gradlebuild.androidTestUtil
import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.LibraryDependency
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.TestDependency
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidModulePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "playmovie.android.library",
                "playmovie.codestyle",
                "playmovie.codecoverage",
                "androidx.navigation.safeargs.kotlin",
            )
            dependencies {
                implementation(LibraryDependency.KOIN)
                implementation(LibraryDependency.TIMBER)
                implementation(LibraryDependency.GSON)

                implementation(LibraryDependency.COROUTINES_CORE)
                implementation(LibraryDependency.COROUTINES_ANDROID)
                implementation(LibraryDependency.COROUTINES_PLAY_SERVICES)

                implementation(LibraryDependency.LIFECYCLE_VIEWMODEL)
                implementation(LibraryDependency.LIFECYCLE_LIVEDATA)
                implementation(LibraryDependency.LIFECYCLE_RUNTIME)
                implementation(LibraryDependency.LIFECYCLE_COMMON)

                androidTestUtil(TestDependency.ORCHESTRATOR)
                androidTestImplementation(TestDependency.JUNIT)
                androidTestImplementation(TestDependency.JUNIT_EXT)
                androidTestImplementation(TestDependency.TRUTH_CORE)
                androidTestImplementation(TestDependency.TRUTH_EXT)
                androidTestImplementation(TestDependency.MOCKK_ANDROID)
                androidTestImplementation(TestDependency.COROUTINES)
                androidTestImplementation(TestDependency.ARCH)
                androidTestImplementation(TestDependency.RUNNER)
                androidTestImplementation(TestDependency.RULES)
                androidTestImplementation(TestDependency.ROOM)
                androidTestImplementation(TestDependency.ROBOLECTRIC_CORE)
                androidTestImplementation(TestDependency.ROBOLECTRIC_ANNOTATIONS)
                androidTestImplementation(TestDependency.ESPRESSO)
            }
        }
    }
}
