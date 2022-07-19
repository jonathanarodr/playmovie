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
    api(LibraryDependency.KOIN)
    api(LibraryDependency.TIMBER)
    api(LibraryDependency.COMPOSE_RUNTIME)
    api(LibraryDependency.COMPOSE_UI)
    api(LibraryDependency.COMPOSE_PREVIEW)
    debugApi(LibraryDependency.COMPOSE_TOOLING)
    api(LibraryDependency.COMPOSE_FOUNDATION)
    api(LibraryDependency.COMPOSE_MATERIAL)
    api(LibraryDependency.COMPOSE_LIVEDATA)
    api(LibraryDependency.COIL_CORE)
    api(LibraryDependency.COIL_COMPOSE)

    testImplementation(project(Modules.TESTING))
}
