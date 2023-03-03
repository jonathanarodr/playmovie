package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.PlatformConfig
import br.com.jonathanarodr.playmovie.gradlebuild.config.versionCode
import br.com.jonathanarodr.playmovie.gradlebuild.config.versionName
import br.com.jonathanarodr.playmovie.gradlebuild.kotlinOptions
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.kotlinExtension

internal fun Project.configureKotlinConvention(
    extension: CommonExtension<*, *, *, *>,
) {
    val platformConfig = PlatformConfig()

    extension.apply {
        compileOptions {
            targetCompatibility = platformConfig.javaVersion
            sourceCompatibility = platformConfig.javaVersion
        }
        kotlinOptions {
            jvmTarget = platformConfig.javaVersion.versionName()
        }
        kotlinExtension.jvmToolchain(platformConfig.javaVersion.versionCode())
    }
}
