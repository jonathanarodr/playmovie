plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
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
            isTestCoverageEnabled = true
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
    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
        animationsDisabled = true
        unitTests.apply {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
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

    implementation(project(Modules.NETWORK))
    implementation(project(Modules.COMMON))

    implementation(LibraryDependency.KOTLIN_STDLIB)

    implementation(LibraryDependency.COROUTINES_CORE)
    implementation(LibraryDependency.COROUTINES_ANDROID)
    implementation(LibraryDependency.COROUTINES_PLAY_SERVICES)

    implementation(LibraryDependency.ANDROIDX_CORE)
    implementation(LibraryDependency.ANDROIDX_APPCOMPAT)
    implementation(LibraryDependency.ANDROIDX_RECYCLER)
    implementation(LibraryDependency.ANDROIDX_CONSTRAINT)

    implementation(LibraryDependency.LIFECYCLE_VIEWMODEL)
    implementation(LibraryDependency.LIFECYCLE_LIVEDATA)
    implementation(LibraryDependency.LIFECYCLE_RUNTIME)
    implementation(LibraryDependency.LIFECYCLE_COMMON)

    implementation(LibraryDependency.ROOM_CORE)
    implementation(LibraryDependency.ROOM_RUNTIME)
    kapt(LibraryDependency.ROOM_COMPILER)

    implementation(LibraryDependency.NAVIGATION_FRAGMENT)
    implementation(LibraryDependency.NAVIGATION_UI)

    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.GSON)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.KOIN)

    testImplementation(TestDependency.JUNIT)
    testImplementation(TestDependency.JUNIT_EXT)
    testImplementation(TestDependency.TRUTH_CORE)
    testImplementation(TestDependency.TRUTH_EXTENSION)
    testImplementation(TestDependency.MOCKK)
    testImplementation(TestDependency.COROUTINES)
    testImplementation(TestDependency.ARCH)
    testImplementation(TestDependency.ROOM)
    testImplementation(TestDependency.OKHTTP_MOCK)
    testImplementation(TestDependency.ROBOLECTRIC_CORE)

    androidTestUtil(TestDependency.ORCHESTRATOR)
    androidTestImplementation(TestDependency.RUNNER)
    androidTestImplementation(TestDependency.RULES)
    androidTestImplementation(TestDependency.JUNIT_EXT)
    androidTestImplementation(TestDependency.TRUTH_CORE)
    androidTestImplementation(TestDependency.TRUTH_EXTENSION)
    androidTestImplementation(TestDependency.MOCKK)
    androidTestImplementation(TestDependency.ROBOLECTRIC_ANNOTATIONS)
    androidTestImplementation(TestDependency.ESPRESSO) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}