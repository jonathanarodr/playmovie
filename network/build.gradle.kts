plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    defaultConfig {
        buildConfigField("String", "SERVER_URL", "\"${ServerConfig.URL}\"")
        buildConfigField("String", "AUTHORIZATION_KEY", "\"${ServerConfig.AUTHORIZATION}\"")
    }
}

dependencies {
    api(LibraryDependency.RETROFIT_CORE)
    implementation(LibraryDependency.RETROFIT_CONVERTER)
    implementation(platform(LibraryDependency.OKHTTP_BOM))
    implementation(LibraryDependency.OKHTTP_CORE)
    implementation(LibraryDependency.OKHTTP_LOGGING)
    implementation(LibraryDependency.KOIN)
}
