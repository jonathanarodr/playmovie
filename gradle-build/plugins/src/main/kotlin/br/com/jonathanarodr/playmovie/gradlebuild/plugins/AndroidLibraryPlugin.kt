package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidLibraryConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureDeviceManagerConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureSourceConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureTestRetryConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.library",
                "org.gradle.android.cache-fix",
                "org.gradle.test-retry",
                "kotlin-android",
                "kotlin-parcelize",
                "com.google.devtools.ksp",
            )

            extensions.configure<LibraryExtension> {
                configureKotlinConvention(this)
                configureAndroidLibraryConvention(this)
                configureUnitTestConvention(this)
                configureAndroidTestConvention(this)
                configureDeviceManagerConvention(this)
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                configureSourceConvention(this)
            }

            tasks.withType<Test>().configureEach {
                configureTestRetryConvention()
            }
        }
    }
}
