package br.com.jonathanarodr.playmovie.gradlebuild.convention

import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinConvention() {
    val javaVersion = JavaVersion.VERSION_11.toString()

    tasks.run {
        withType<JavaCompile>().configureEach {
            targetCompatibility = javaVersion
            sourceCompatibility = javaVersion
        }
        withType<KotlinCompile>().configureEach {
            kotlinOptions.jvmTarget = javaVersion
        }
    }
}
