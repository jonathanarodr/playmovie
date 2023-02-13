package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.dependencies.Version
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureComposeConvention(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = Version.Compose.COMPILER
        }
    }
}
