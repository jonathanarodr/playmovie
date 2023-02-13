package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.android
import br.com.jonathanarodr.playmovie.gradlebuild.androidComponents
import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import org.gradle.api.Project

internal fun Project.configureAndroidConvention() {
    android {
        compileSdkVersion(AndroidConfig.SDK_COMPILER)
        buildToolsVersion(AndroidConfig.BUILD_TOOLS)

        defaultConfig {
            minSdk = AndroidConfig.SDK_MINIMUM
            targetSdk = AndroidConfig.SDK_TARGET
            vectorDrawables.useSupportLibrary = true
        }

        buildFeatures.viewBinding = true
    }
    androidComponents {
        registerSourceType(AndroidConfig.SOURCE_SHARED_TEST)
    }
}

internal fun Project.configureBuildTypeConvention() {
    android {
        buildTypes {
            getByName("debug") {
                isDebuggable = true
                isMinifyEnabled = false
                enableUnitTestCoverage = true
            }
            getByName("release") {
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro",
                )
            }
        }
    }
}
