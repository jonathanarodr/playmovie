plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

android {
    namespace = AndroidConfig.APPLICATION_ID
    compileSdk = AndroidConfig.SDK_COMPILER
    buildToolsVersion = AndroidConfig.BUILD_TOOLS

    defaultConfig {
        applicationId = AndroidConfig.APPLICATION_ID

        minSdk = AndroidConfig.SDK_MINIMUM
        targetSdk = AndroidConfig.SDK_TARGET

        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        vectorDrawables.useSupportLibrary = true
    }
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    packagingOptions {
        resources.excludes += setOf(
            "META-INF/LICENSE*",
            "META-INF/NOTICE",
            "META-INF/*.kotlin_module",
            "META-INF/AL2.0",
            "META-INF/LGPL2.1",
            "DebugProbesKt.bin",
        )
    }
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true

            all {
                if (name != "testDebugUnitTest") {
                    kover.isDisabled = true
                }
            }
        }
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
}

dependencies {
    implementation(
        fileTree(
            "dir" to "libs",
            "include" to listOf("*.jar", "*.aar"),
        )
    )

    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.FEATURE))

    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)

    debugImplementation(LibraryDependency.LEAKCANARY)
}
