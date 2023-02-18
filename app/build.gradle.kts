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

    debugImplementation(LibraryDependency.LEAKCANARY)
}
