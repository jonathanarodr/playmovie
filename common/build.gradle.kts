plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.common"
}

dependencies {
    api(LibraryDependency.KOIN)
    api(LibraryDependency.TIMBER)

    testImplementation(project(Modules.TESTING))
}
