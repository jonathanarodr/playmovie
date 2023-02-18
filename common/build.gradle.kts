plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.common"
}

dependencies {
    implementation(platform(LibraryDependency.OKHTTP_BOM))
    implementation(LibraryDependency.OKHTTP_CORE)
    implementation(LibraryDependency.COIL_CORE)

    testImplementation(project(Modules.TESTING))
}
