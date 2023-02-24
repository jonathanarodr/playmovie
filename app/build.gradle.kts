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

    implementation(project(":common"))
    implementation(project(":network"))
    implementation(project(":feature"))

    debugImplementation(libs.leakcanary)
}
