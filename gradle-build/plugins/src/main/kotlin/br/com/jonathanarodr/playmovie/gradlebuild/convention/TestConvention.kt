package br.com.jonathanarodr.playmovie.gradlebuild.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureTestConvention(
    extension: CommonExtension<*, *, *, *>,
) {
    extension.apply {
        defaultConfig {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            testInstrumentationRunnerArguments["clearPackageData"] = "true"
        }
        testOptions {
            execution = "ANDROIDX_TEST_ORCHESTRATOR"
            animationsDisabled = true
            unitTests.apply {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
            }
        }
    }
}
