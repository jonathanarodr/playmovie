package br.com.jonathanarodr.playmovie.gradlebuild.config

internal object AndroidConfig {

    const val APPLICATION_ID = "br.com.jonathanarodr.playmovie"

    const val SDK_COMPILER = 33
    const val SDK_TARGET = 33
    const val SDK_MINIMUM = 21

    const val BUILD_TOOLS = "33.0.0"

    const val VERSION_CODE = 7
    const val VERSION_NAME = "2.2.4"

    const val JAVA_SOURCE_DIR = "src/main/java"
    const val JAVA_SOURCE_DIR_TEST = "src/test/java"
    const val JAVA_SOURCE_DIR_SHARED_TEST = "src/sharedTest/java"
    const val JAVA_SOURCE_DIR_ANDROID_TEST = "src/androidTest/java"
    const val KOTLIN_SOURCE_DIR = "src/main/kotlin"
    const val KOTLIN_SOURCE_DIR_TEST = "src/test/kotlin"
    const val KOTLIN_SOURCE_DIR_SHARED_TEST = "src/sharedTest/kotlin"
    const val KOTLIN_SOURCE_DIR_ANDROID_TEST = "src/androidTest/kotlin"
}
