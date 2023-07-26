rootProject.name = "playmovie"

pluginManagement {
    includeBuild("gradle-build")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

include(
    ":app",
    ":testing",
    ":common",
    ":network",
    ":feature",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
