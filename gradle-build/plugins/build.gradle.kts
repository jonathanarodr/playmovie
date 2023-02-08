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
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.20")
}

gradlePlugin {
    plugins {
        //todo
    }
}
