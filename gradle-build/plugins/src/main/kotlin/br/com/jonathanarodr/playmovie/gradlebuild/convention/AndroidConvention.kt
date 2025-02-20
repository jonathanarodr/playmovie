package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project

internal fun Project.configureAndroidApplicationConvention(
    extension: ApplicationExtension,
) {
    extension.apply {
        compileSdk = AndroidConfig.SDK_COMPILER

        defaultConfig {
            applicationId = AndroidConfig.APPLICATION_ID

            versionCode = AndroidConfig.VERSION_CODE
            versionName = AndroidConfig.VERSION_NAME

            minSdk = AndroidConfig.SDK_MINIMUM
            targetSdk = AndroidConfig.SDK_TARGET

            vectorDrawables.useSupportLibrary = true
        }

        packaging {
            resources {
                excludes.add("META-INF/*.kotlin_module")
                excludes.add("META-INF/LICENSE**")
                excludes.add("META-INF/NOTICE")
                excludes.add("META-INF/AL2.0")
                excludes.add("META-INF/LGPL2.1")
                excludes.add("DebugProbesKt.bin")
            }
        }

        buildFeatures {
            buildConfig = true
            viewBinding = true
        }
    }
}

internal fun Project.configureAndroidLibraryConvention(
    extension: LibraryExtension,
) {
    extension.apply {
        compileSdk = AndroidConfig.SDK_COMPILER

        defaultConfig {
            minSdk = AndroidConfig.SDK_MINIMUM
            vectorDrawables.useSupportLibrary = true
        }

        packaging {
            resources {
                excludes.add("META-INF/*.kotlin_module")
                excludes.add("META-INF/LICENSE**")
                excludes.add("META-INF/NOTICE")
                excludes.add("META-INF/AL2.0")
                excludes.add("META-INF/LGPL2.1")
                excludes.add("DebugProbesKt.bin")
            }
        }

        buildFeatures {
            viewBinding = true
        }
    }
}

internal fun Project.configureSourceConvention(
    extension: AndroidComponentsExtension<*, *, *>,
) {
    extension.registerSourceType(AndroidConfig.KOTLIN_SOURCE_DIR_SHARED_TEST)
}

internal fun Project.configureBuildTypeConvention(
    extension: ApplicationExtension,
) {
    extension.buildTypes {
        getByName("debug") {
            isDebuggable = true
            enableUnitTestCoverage = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            enableUnitTestCoverage = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles("proguard-rules.pro")
            proguardFiles(
                extension.getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
