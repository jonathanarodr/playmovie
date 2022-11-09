object LibraryDependency {

    const val GRADLE_TOOLS = "com.android.tools.build:gradle:${Version.Gradle.CORE}"

    const val KOTLIN_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.Kotlin.CORE}"

    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.Kotlin.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.Kotlin.COROUTINES}"
    const val COROUTINES_PLAY_SERVICES = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.Kotlin.COROUTINES}"

    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Version.Jetpack.CORE}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Version.Jetpack.APPCOMPAT}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${Version.Jetpack.ACTIVITY_COMPOSE}"
    const val ANDROIDX_RECYCLER = "androidx.recyclerview:recyclerview:${Version.Jetpack.RECYCLER}"
    const val ANDROIDX_CONSTRAINT = "androidx.constraintlayout:constraintlayout:${Version.Jetpack.CONSTRAINT}"
    const val ANDROIDX_SWIPE_REFRESH = "androidx.swiperefreshlayout:swiperefreshlayout:${Version.Jetpack.SWIPE_REFRESH}"

    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.Jetpack.LIFECYCLE}"
    const val LIFECYCLE_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.Jetpack.LIFECYCLE}"
    const val LIFECYCLE_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Jetpack.LIFECYCLE}"
    const val LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.Jetpack.LIFECYCLE}"
    const val LIFECYCLE_COMMON = "androidx.lifecycle:lifecycle-common-java8:${Version.Jetpack.LIFECYCLE}"

    const val COMPOSE_BOM = "androidx.compose:compose-bom:${Version.Compose.CORE}"
    const val COMPOSE_RUNTIME = "androidx.compose.runtime:runtime"
    const val COMPOSE_LIVEDATA = "androidx.compose.runtime:runtime-livedata"
    const val COMPOSE_UI = "androidx.compose.ui:ui"
    const val COMPOSE_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val COMPOSE_TOOLING = "androidx.compose.ui:ui-tooling"
    const val COMPOSE_FOUNDATION = "androidx.compose.foundation:foundation"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3"

    const val ROOM_CORE = "androidx.room:room-ktx:${Version.Jetpack.ROOM}"
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${Version.Jetpack.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Version.Jetpack.ROOM}"

    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${Version.Jetpack.NAVIGATION}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Version.Jetpack.NAVIGATION}"
    const val NAVIGATION_SAFE_ARGS = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.Jetpack.NAVIGATION}"

    const val RETROFIT_CORE = "com.squareup.retrofit2:retrofit:${Version.Square.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.Square.RETROFIT}"

    const val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:${Version.Square.OKHTTP}"
    const val OKHTTP_CORE = "com.squareup.okhttp3:okhttp"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor"

    const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:${Version.Square.LEAKCANARY}"

    const val MATERIAL = "com.google.android.material:material:${Version.Tools.MATERIAL}"
    const val GSON = "com.google.code.gson:gson:${Version.Tools.GSON}"
    const val TIMBER = "com.jakewharton.timber:timber:${Version.Tools.TIMBER}"
    const val KOIN = "io.insert-koin:koin-android:${Version.Tools.KOIN}"

    const val COIL = "io.coil-kt:coil:${Version.Tools.COIL}"
}