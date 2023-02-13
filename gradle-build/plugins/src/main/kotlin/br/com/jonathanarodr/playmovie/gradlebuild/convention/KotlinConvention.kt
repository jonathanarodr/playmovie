package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.kotlinOptions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinConvention(
    extension: CommonExtension<*, *, *, *>,
) {
    val javaVersion = JavaVersion.VERSION_11

    extension.apply {
        compileOptions {
            targetCompatibility = javaVersion
            sourceCompatibility = javaVersion
        }
        kotlinOptions {
            jvmTarget = javaVersion.toString()
        }
    }
}
