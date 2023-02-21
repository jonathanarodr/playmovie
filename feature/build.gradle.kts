plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.feature"
}

dependencies {
    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))

    implementation(LibraryDependency.ANDROIDX_CORE)
    implementation(LibraryDependency.ANDROIDX_APPCOMPAT)
    implementation(LibraryDependency.ANDROIDX_RECYCLER)
    implementation(LibraryDependency.ANDROIDX_CONSTRAINT)
    implementation(LibraryDependency.ANDROIDX_SWIPE_REFRESH)

    implementation(LibraryDependency.ROOM_CORE)
    implementation(LibraryDependency.ROOM_RUNTIME)
    ksp(LibraryDependency.ROOM_COMPILER)

    implementation(LibraryDependency.NAVIGATION_FRAGMENT)
    implementation(LibraryDependency.NAVIGATION_UI)

    implementation(LibraryDependency.MATERIAL)

    testImplementation(project(Modules.TESTING))
    androidTestImplementation(project(Modules.TESTING))
}
