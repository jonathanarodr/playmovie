plugins {
    `kotlin-dsl`
}

group = "br.com.jonathanarodr.playmovie.gradlebuild"

/**
 * FIXME use the catalog version
 * https://github.com/jonathanarodr/playmovie/issues/41
 */
dependencies {
    compileOnly("com.android.tools.build:gradle:7.4.1")
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.21")
    compileOnly("org.jetbrains.kotlinx:kover:0.6.1")
    compileOnly("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.7.21-1.0.8")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
    compileOnly("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "playmovie.android.application"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.AndroidApplicationPlugin"
        }
        register("android-library") {
            id = "playmovie.android.library"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.AndroidLibraryPlugin"
        }
        register("android-compose") {
            id = "playmovie.android.compose"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.AndroidComposePlugin"
        }
        register("kotlin-library") {
            id = "playmovie.kotlin.library"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.KotlinLibraryPlugin"
        }
        register("code-style") {
            id = "playmovie.codestyle"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.CodeStylePlugin"
        }
        register("code-coverage") {
            id = "playmovie.codecoverage"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.CodeCoveragePlugin"
        }
        register("kotlin-module") {
            id = "playmovie.kotlin.module"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.KotlinModulePlugin"
        }
        register("android-module") {
            id = "playmovie.android.module"
            implementationClass = "br.com.jonathanarodr.playmovie.gradlebuild.plugins.AndroidModulePlugin"
        }
    }
}
