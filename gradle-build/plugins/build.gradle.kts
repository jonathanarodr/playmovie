plugins {
    `kotlin-dsl`
}

group = "br.com.jonathanarodr.playmovie.gradlebuild"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.kotlinx.kover.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.detekt.gradlePlugin)
    compileOnly(libs.androidx.navigation.safeargs.gradlePlugin)
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
