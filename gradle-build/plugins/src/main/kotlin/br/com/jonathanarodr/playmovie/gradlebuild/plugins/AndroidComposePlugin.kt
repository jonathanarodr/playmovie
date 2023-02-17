package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureComposeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.debugApi
import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.LibraryDependency
import br.com.jonathanarodr.playmovie.gradlebuild.implementation
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidComposePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            extensions.configure<LibraryExtension> {
                configureComposeConvention(this)
            }
            dependencies {
                implementation(platform(LibraryDependency.COMPOSE_BOM))
                implementation(LibraryDependency.COMPOSE_RUNTIME)
                implementation(LibraryDependency.COMPOSE_UI)
                implementation(LibraryDependency.COMPOSE_PREVIEW)
                implementation(LibraryDependency.COMPOSE_FOUNDATION)
                implementation(LibraryDependency.COMPOSE_MATERIAL3)
                implementation(LibraryDependency.COMPOSE_LIVEDATA)
                implementation(LibraryDependency.COIL_CORE)
                implementation(LibraryDependency.COIL_COMPOSE)

                debugApi(LibraryDependency.COMPOSE_TOOLING)
            }
        }
    }
}
