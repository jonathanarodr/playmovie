package br.com.jonathanarodr.playmovie.gradlebuild.convention

import br.com.jonathanarodr.playmovie.gradlebuild.config.PlatformConfig
import br.com.jonathanarodr.playmovie.gradlebuild.config.versionName
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinConvention(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    val platformConfig = PlatformConfig()

    commonExtension.apply {
        compileOptions {
            sourceCompatibility = platformConfig.javaVersion
            targetCompatibility = platformConfig.javaVersion
        }
    }

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = platformConfig.javaVersion
        targetCompatibility = platformConfig.javaVersion
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = platformConfig.javaVersion.versionName()
        }
    }
}
