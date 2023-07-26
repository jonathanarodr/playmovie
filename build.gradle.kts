buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

/**
 * FIXME remove suppress DSL_SCOPE_VIOLATION after fixing issue
 * issue: https://github.com/gradle/gradle/issues/22797
 */
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.gradle.doctor) apply true
    alias(libs.plugins.gradle.dependency.analysis) apply true
    alias(libs.plugins.gradle.testretry) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlinx.kover) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.androidx.navigation.safeargs) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

doctor {
    warnWhenJetifierEnabled.set(false)
}

dependencyAnalysis {
    issues {
        all {
            ignoreKtx(true)
        }
    }
}
