package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.AndroidConfig
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Project

internal fun Project.configureAndroidAppicationConvention(
    extension: ApplicationExtension,
) {
    extension.apply {
        compileSdk = AndroidConfig.SDK_COMPILER
        buildToolsVersion = AndroidConfig.BUILD_TOOLS

        defaultConfig {
            versionCode = AndroidConfig.VERSION_CODE
            versionName = AndroidConfig.VERSION_NAME
            minSdk = AndroidConfig.SDK_MINIMUM
            targetSdk = AndroidConfig.SDK_TARGET
            vectorDrawables.useSupportLibrary = true
        }

        buildFeatures.viewBinding = true
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

       buildFeatures.viewBinding = true
   }
}

internal fun Project.configureSourceConvention(
    extension: AndroidComponentsExtension<*, *, *>
) {
    extension.registerSourceType(AndroidConfig.SOURCE_SHARED_TEST)
}

internal fun Project.configureBuildTypeConvention(
    extension: ApplicationExtension,
) {
    extension.buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            enableUnitTestCoverage = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            buildDir
            proguardFiles("proguard-rules.pro")
        }
    }
}
