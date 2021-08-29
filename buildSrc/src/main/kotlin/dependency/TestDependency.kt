package dependency

object TestDependency {

    const val JUNIT = "junit:junit:${Version.Test.JUNIT}"
    const val MOCKK = "io.mockk:mockk:${Version.Test.MOCKK}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.Library.COROUTINES}"
}