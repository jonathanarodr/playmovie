package br.com.jonathanarodr.playmovie.gradlebuild.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureComposeConvention(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
}
