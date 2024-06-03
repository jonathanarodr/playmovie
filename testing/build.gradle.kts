plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.testing"

    testFixtures {
        enable = true
    }
}

/**
 * FIXME create submodules to control dependencies between unitTest and androidTest
 */
dependencies {
    testFixturesImplementation(libs.kotlin.stdlib)

    implementation(libs.androidx.lifecycle.livedata)

    api(libs.junit)
    api(libs.truth)
    api(libs.mockk.core)
    api(libs.mockk.android)
    api(libs.mockk.agent)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.test.junit)
    api(libs.androidx.test.truth)
    api(libs.androidx.arch.testing)
    api(libs.androidx.test.core)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.rules)
    api(libs.androidx.room.testing)
    api(libs.androidx.test.espresso) {
        exclude(group = "com.android.support", module = "support-annotations")
    }
    api(libs.robolectric.core)
    api(libs.robolectric.annotations)
    api(libs.koin.test)
    api(libs.koin.android.test)
}
