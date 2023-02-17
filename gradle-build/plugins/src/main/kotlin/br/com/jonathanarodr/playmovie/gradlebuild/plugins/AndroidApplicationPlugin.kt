package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.apply
import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidAppicationConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureAndroidTestConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureBuildTypeConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureKotlinConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureSourceConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureUnitTestConvention
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply(
                "com.android.application",
                "kotlin-android",
                "kotlin-parcelize",
            )
            extensions.configure<ApplicationExtension> {
                configureAndroidAppicationConvention(this)
                configureBuildTypeConvention(this)
                configureKotlinConvention(this)
                configureUnitTestConvention(this)
                configureAndroidTestConvention(this)

                namespace = AndroidConfig.APPLICATION_ID

                dependenciesInfo {
                    includeInApk = true
                    includeInBundle = true
                }
            }
            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureSourceConvention(this)
            }
        }
    }
}