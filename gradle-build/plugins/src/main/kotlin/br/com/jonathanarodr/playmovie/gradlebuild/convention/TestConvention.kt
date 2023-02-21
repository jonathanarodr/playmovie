package br.com.jonathanarodr.playmovie.gradlebuild.convention

import com.android.build.api.dsl.CommonExtension
import kotlinx.kover.api.KoverClassFilter
import kotlinx.kover.api.KoverProjectConfig
import org.gradle.api.Project
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

internal fun Project.configureUnitTestConvention(
    extension: CommonExtension<*, *, *, *>,
) {
    extension.apply {
        testOptions {
            unitTests.apply {
                isReturnDefaultValues = true
                isIncludeAndroidResources = true
                all { test ->
                    test.testLogging {
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                }
            }
        }
    }
}

internal fun Project.configureAndroidTestConvention(
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
                all { test ->
                    test.testLogging {
                        exceptionFormat = TestExceptionFormat.FULL
                    }
                    // https://github.com/robolectric/robolectric/issues/3023
                    test.jvmArgs.addAll(
                        listOf("-ea", "-noverify")
                    )
                }
            }
        }
    }
}

internal fun KoverProjectConfig.excludeDefaultTasks(): List<String> {
    return listOf(
        "testReleaseUnitTest"
    )
}

internal fun KoverClassFilter.excludeDefaultClasses(): List<String> {
    return listOf(
        "*.databinding.*"
    )
}
