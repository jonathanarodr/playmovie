plugins {
    id("playmovie.android.application")
}

dependencies {
    implementation(
        fileTree(
            "dir" to "libs",
            "include" to listOf("*.jar", "*.aar"),
        )
    )

    implementation(project(Modules.COMMON))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.FEATURE))

    implementation(LibraryDependency.KOIN)
    implementation(LibraryDependency.TIMBER)

    debugImplementation(LibraryDependency.LEAKCANARY)
}
