plugins {
    id("playmovie.android.module")
    id("playmovie.android.compose")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.feature"
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
