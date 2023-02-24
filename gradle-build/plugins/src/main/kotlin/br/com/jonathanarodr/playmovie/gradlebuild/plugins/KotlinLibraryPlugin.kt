package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class KotlinLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin")
            extensions.configure<LibraryExtension> {
                configureKotlinConvention(this)
                configureUnitTestConvention(this)
            }
        }
    }
}
