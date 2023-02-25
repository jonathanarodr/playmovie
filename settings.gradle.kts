rootProject.name = "playmovie"

pluginManagement {
    includeBuild("gradle-build")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
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
