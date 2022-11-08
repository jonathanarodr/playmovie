plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

android {
    namespace = "br.com.jonathanarodr.playmovie.common"
}

dependencies {
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.COIL)

    testImplementation(project(Modules.TESTING))
}
