plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(LibraryDependency.LIFECYCLE_LIVEDATA)

    androidTestUtil(TestDependency.ORCHESTRATOR)

    api(TestDependency.JUNIT)
    api(TestDependency.JUNIT_EXT)
    api(TestDependency.TRUTH_CORE)
    api(TestDependency.TRUTH_EXT)
    api(TestDependency.MOCKK)
    api(TestDependency.MOCKK_ANDROID)
    api(TestDependency.COROUTINES)
    api(TestDependency.ARCH)
    api(TestDependency.RUNNER)
    api(TestDependency.RULES)
    api(TestDependency.ROOM)
    api(TestDependency.ROBOLECTRIC_CORE)
    api(TestDependency.ROBOLECTRIC_ANNOTATIONS)
    api(TestDependency.ESPRESSO) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}
