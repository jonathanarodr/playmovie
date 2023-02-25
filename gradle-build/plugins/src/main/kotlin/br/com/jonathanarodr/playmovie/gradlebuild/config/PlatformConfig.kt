package br.com.jonathanarodr.playmovie.gradlebuild.config

import org.gradle.api.JavaVersion

internal data class PlatformConfig(
    val javaVersion: JavaVersion = JavaVersion.VERSION_11
)
