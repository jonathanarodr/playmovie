plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.GLIDE_CORE) {
        exclude(group = "com.android.support")
    }
    kapt(LibraryDependency.GLIDE_COMPILER)

    testImplementation(TestDependency.JUNIT)
    testImplementation(TestDependency.MOCKK)
    testImplementation(TestDependency.COROUTINES)
}