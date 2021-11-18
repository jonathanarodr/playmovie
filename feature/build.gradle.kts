plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))

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
    testImplementation(TestDependency.RUNNER)
    testImplementation(TestDependency.RULES)
    testImplementation(TestDependency.ROOM)
    testImplementation(TestDependency.OKHTTP_MOCK)
    testImplementation(TestDependency.ROBOLECTRIC_CORE)

    androidTestUtil(TestDependency.ORCHESTRATOR)
    androidTestImplementation(TestDependency.RUNNER)
    androidTestImplementation(TestDependency.RULES)
    androidTestImplementation(TestDependency.JUNIT_EXT)
    androidTestImplementation(TestDependency.TRUTH_CORE)
    androidTestImplementation(TestDependency.TRUTH_EXTENSION)
    androidTestImplementation(TestDependency.MOCKK_ANDROID)
    androidTestImplementation(TestDependency.ROBOLECTRIC_ANNOTATIONS)
    androidTestImplementation(TestDependency.ESPRESSO) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}
