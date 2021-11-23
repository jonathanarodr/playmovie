object TestDependency {

    const val JUNIT = "junit:junit:${Version.Test.JUNIT}"
    const val JUNIT_EXT = "androidx.test.ext:junit:${Version.Test.JUNIT_EXT}"

    const val TRUTH_CORE = "com.google.truth:truth:${Version.Test.TRUTH}"
    const val TRUTH_EXT = "androidx.test.ext:truth:${Version.Test.TRUTH_EXT}"

    const val ARCH = "android.arch.core:core-testing:${Version.Jetpack.CORE}"
    const val ROOM = "androidx.room:room-testing:${Version.Jetpack.ROOM}"
    const val ORCHESTRATOR = "androidx.test:orchestrator:${Version.Test.ORCHESTRATOR}"
    const val RUNNER = "androidx.test:runner:${Version.Test.RUNNER}"
    const val RULES = "androidx.test:rules:${Version.Test.RULES}"

    const val MOCKK = "io.mockk:mockk:${Version.Test.MOCKK}"
    const val MOCKK_ANDROID = "io.mockk:mockk-android:${Version.Test.MOCKK}"
    const val OKHTTP_MOCK = "com.squareup.okhttp3:mockwebserver"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Kotlin.COROUTINES}"

    const val ROBOLECTRIC_CORE = "org.robolectric:robolectric:${Version.Test.ROBOLECTRIC}"
    const val ROBOLECTRIC_ANNOTATIONS = "org.robolectric:annotations:${Version.Test.ROBOLECTRIC}"

    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.Test.ESPRESSO}"
}