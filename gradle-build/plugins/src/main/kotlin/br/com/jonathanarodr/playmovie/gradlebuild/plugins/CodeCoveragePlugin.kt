package br.com.jonathanarodr.playmovie.gradlebuild.plugins

import br.com.jonathanarodr.playmovie.gradlebuild.convention.excludeDefaultClasses
import br.com.jonathanarodr.playmovie.gradlebuild.convention.excludeDefaultTasks
import kotlinx.kover.api.KoverMergedConfig
import kotlinx.kover.api.KoverProjectConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class CodeCoveragePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlinx.kover")
            extensions.configure<KoverProjectConfig> {
                instrumentation {
                    excludeDefaultTasks()
                }
            }
            extensions.configure<KoverMergedConfig> {
                enable()
                filters {
                    classes {
                        excludeDefaultClasses()
                    }
                }
            }
        }
    }
}