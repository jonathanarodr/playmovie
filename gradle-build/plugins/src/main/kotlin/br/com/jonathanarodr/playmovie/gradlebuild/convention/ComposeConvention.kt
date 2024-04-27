package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.libs
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureComposeConvention(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val libs = extensions.libs

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("androidxComposeCompiler").get().toString()
        }
    }
}
