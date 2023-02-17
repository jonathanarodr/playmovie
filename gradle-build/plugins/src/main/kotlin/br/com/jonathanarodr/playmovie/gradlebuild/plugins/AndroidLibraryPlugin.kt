package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidLibraryConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureComposeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureDeviceManagerConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureSourceConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.library",
                "kotlin-android",
                "kotlin-parcelize",
                "com.google.devtools.ksp",
            )
            extensions.configure<LibraryExtension> {
                configureAndroidLibraryConvention(this)
                configureKotlinConvention(this)
                configureUnitTestConvention(this)
                configureAndroidTestConvention(this)
                configureComposeConvention(this)
                configureDeviceManagerConvention(this)
            }
            extensions.configure<LibraryAndroidComponentsExtension> {
                configureSourceConvention(this)
            }
        }
    }
}
