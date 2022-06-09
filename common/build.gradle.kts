plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

dependencies {
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.COIL)

    testImplementation(TestDependency.JUNIT)
    testImplementation(TestDependency.MOCKK)
    testImplementation(TestDependency.COROUTINES)
}
