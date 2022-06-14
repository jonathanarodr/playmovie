plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

dependencies {
    implementation(LibraryDependency.LIFECYCLE_LIVEDATA)

    api(TestDependency.JUNIT)
    api(TestDependency.JUNIT_EXT)
    api(TestDependency.TRUTH_CORE)
    api(TestDependency.TRUTH_EXT)
    api(TestDependency.MOCKK)
    api(TestDependency.COROUTINES)
    api(TestDependency.ARCH)
    api(TestDependency.RUNNER)
    api(TestDependency.RULES)
    api(TestDependency.ROOM)
    api(TestDependency.ROBOLECTRIC_CORE)

    androidTestUtil(TestDependency.ORCHESTRATOR)
    androidTestImplementation(TestDependency.RUNNER)
    androidTestImplementation(TestDependency.RULES)
    androidTestImplementation(TestDependency.JUNIT_EXT)
    androidTestImplementation(TestDependency.TRUTH_CORE)
    androidTestImplementation(TestDependency.TRUTH_EXT)
    androidTestImplementation(TestDependency.COROUTINES)
    androidTestImplementation(TestDependency.MOCKK_ANDROID)
    androidTestImplementation(TestDependency.ROBOLECTRIC_ANNOTATIONS)
    androidTestImplementation(TestDependency.ESPRESSO) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}