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

    implementation(projects.common)
    implementation(projects.network)
    implementation(projects.feature)

    debugImplementation(libs.leakcanary)
}
