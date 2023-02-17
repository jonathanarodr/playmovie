plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.core.testing"
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
