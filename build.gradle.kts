buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

/**
 * FIXME use the catalog version
 * https://github.com/jonathanarodr/playmovie/issues/41
 */
plugins {
    id("com.android.application") version Version.Gradle.ANDROID_PLUGIN apply false
    id("org.jetbrains.kotlin.jvm") version Version.Kotlin.CORE apply false
    id("org.jetbrains.kotlinx.kover") version Version.Test.KOVER apply false
    id("com.google.devtools.ksp") version Version.Kotlin.KSP apply false
    id("io.gitlab.arturbosch.detekt") version Version.Lint.DETEKT apply false
    id("androidx.navigation.safeargs.kotlin") version Version.Jetpack.NAVIGATION apply false
}
