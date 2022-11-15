plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlinx.kover") version "0.5.0"
}

android {
    namespace = "br.com.jonathanarodr.playmovie.common"
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
    api(platform(LibraryDependency.COMPOSE_BOM))
    api(LibraryDependency.COMPOSE_RUNTIME)
    api(LibraryDependency.COMPOSE_UI)
    api(LibraryDependency.COMPOSE_PREVIEW)
    api(LibraryDependency.COMPOSE_FOUNDATION)
    api(LibraryDependency.COMPOSE_MATERIAL3)
    api(LibraryDependency.COMPOSE_LIVEDATA)
    api(LibraryDependency.COIL_CORE)
    api(LibraryDependency.COIL_COMPOSE)

    debugApi(LibraryDependency.COMPOSE_TOOLING)

    testImplementation(project(Modules.TESTING))
}
