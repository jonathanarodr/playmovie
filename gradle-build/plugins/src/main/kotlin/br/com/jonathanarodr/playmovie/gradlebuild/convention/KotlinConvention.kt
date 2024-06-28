package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.PlatformConfig
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

private fun KotlinJvmCompilerOptions.configureKotlinCompiler(platformConfig: PlatformConfig) {
    jvmTarget.set(platformConfig.kotlinJvmVersion)
    allWarningsAsErrors.set(platformConfig.allWarningsAsErrors)
    freeCompilerArgs.add(platformConfig.freeCompilerArgs)
}

internal fun Project.configureKotlinJvmConvention(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val platformConfig = PlatformConfig()

    commonExtension.apply {
        compileOptions {
            sourceCompatibility = platformConfig.javaJvmVersion
            targetCompatibility = platformConfig.javaJvmVersion
        }
    }

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = platformConfig.javaJvmVersion
        targetCompatibility = platformConfig.javaJvmVersion
    }

    configure<KotlinJvmProjectExtension> {
        compilerOptions {
            configureKotlinCompiler(platformConfig)
        }
    }
}

internal fun Project.configureKotlinAndroidConvention(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val platformConfig = PlatformConfig()

    commonExtension.apply {
        compileOptions {
            sourceCompatibility = platformConfig.javaJvmVersion
            targetCompatibility = platformConfig.javaJvmVersion
        }
    }

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = platformConfig.javaJvmVersion
        targetCompatibility = platformConfig.javaJvmVersion
    }

    configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            configureKotlinCompiler(platformConfig)
        }
    }
}
