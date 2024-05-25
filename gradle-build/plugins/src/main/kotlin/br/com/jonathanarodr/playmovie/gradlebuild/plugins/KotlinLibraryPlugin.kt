package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinJvmConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureTestRetryConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

@Suppress("unused")
class KotlinLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "kotlin",
                "org.gradle.test-retry",
            )

            extensions.configure<LibraryExtension> {
                configureKotlinJvmConvention(this)
                configureUnitTestConvention(this)
            }

            tasks.withType<Test>().configureEach {
                configureTestRetryConvention()
            }
        }
    }
}
