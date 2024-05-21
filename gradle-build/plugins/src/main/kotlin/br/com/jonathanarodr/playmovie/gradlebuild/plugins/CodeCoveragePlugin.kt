package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.convention.configureCoverageConvention
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class CodeCoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")

            extensions.configure<KoverProjectExtension>("kover") {
                configureCoverageConvention()
            }
        }
    }
}
