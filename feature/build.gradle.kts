/**
 * FIXME create a plugin and configure in all modules
 * https://github.com/jonathanarodr/playmovie/issues/28
 */
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.feature"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Version.Compose.COMPILER
    }
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))

    implementation(LibraryDependency.COROUTINES_CORE)
    implementation(LibraryDependency.COROUTINES_ANDROID)
    implementation(LibraryDependency.COROUTINES_PLAY_SERVICES)

    implementation(LibraryDependency.ANDROIDX_CORE)
    implementation(LibraryDependency.ANDROIDX_APPCOMPAT)
    implementation(LibraryDependency.ANDROIDX_RECYCLER)
    implementation(LibraryDependency.ANDROIDX_CONSTRAINT)
    implementation(LibraryDependency.ANDROIDX_SWIPE_REFRESH)

    implementation(LibraryDependency.LIFECYCLE_VIEWMODEL)
    implementation(LibraryDependency.LIFECYCLE_LIVEDATA)
    implementation(LibraryDependency.LIFECYCLE_RUNTIME)
    implementation(LibraryDependency.LIFECYCLE_COMMON)

    implementation(LibraryDependency.ROOM_CORE)
    implementation(LibraryDependency.ROOM_RUNTIME)
    ksp(LibraryDependency.ROOM_COMPILER)

    implementation(LibraryDependency.NAVIGATION_FRAGMENT)
    implementation(LibraryDependency.NAVIGATION_UI)

    implementation(LibraryDependency.MATERIAL)
    implementation(LibraryDependency.GSON)
    implementation(LibraryDependency.TIMBER)
    implementation(LibraryDependency.KOIN)

    testImplementation(project(Modules.TESTING))
    androidTestImplementation(project(Modules.TESTING))
}
