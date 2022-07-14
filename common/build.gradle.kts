plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.Compose.COMPILER
    }
}

dependencies {
    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.COIL)

    implementation(LibraryDependency.COMPOSE_UI)
    implementation(LibraryDependency.COMPOSE_MATERIAL)

    testImplementation(project(Modules.TESTING))
}
