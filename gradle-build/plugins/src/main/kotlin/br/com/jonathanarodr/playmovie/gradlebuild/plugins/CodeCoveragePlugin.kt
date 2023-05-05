package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureCoverageConvention
import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureFilterConvention
import kotlinx.kover.gradle.plugin.dsl.KoverReportExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("unused")
class CodeCoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")

            extensions.configure<KoverReportExtension> {
                configureCoverageConvention()
                configureFilterConvention()
            }
        }
    }
}
