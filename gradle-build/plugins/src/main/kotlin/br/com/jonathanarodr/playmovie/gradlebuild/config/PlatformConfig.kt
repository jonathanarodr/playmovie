package br.com.jonathanarodr.playmovie.gradlebuild.config

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal data class PlatformConfig(
    val javaJvmVersion: JavaVersion = JavaVersion.VERSION_17,
    val kotlinJvmVersion: JvmTarget = JvmTarget.JVM_17,
    val allWarningsAsErrors: Boolean = false,
    val freeCompilerArgs: String = "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
)

fun JavaVersion.versionName(): String = this.toString()

fun JavaVersion.versionCode(): Int = this.versionName().toInt()
