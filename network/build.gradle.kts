import config.ServerConfig
import dependency.LibraryDependency

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    defaultConfig {
        buildConfigField("String", "SERVER_URL",  "\"${ServerConfig.URL}\"")
        buildConfigField("String", "AUTHORIZATION_KEY", "\"${ServerConfig.AUTHORIZATION}\"")
    }
}

dependencies {
    api(LibraryDependency.Retrofit.CORE)
    implementation(LibraryDependency.Retrofit.CONVERTER)
    implementation(platform(LibraryDependency.Okhttp.BOM))
    implementation(LibraryDependency.Okhttp.CORE)
    implementation(LibraryDependency.Okhttp.LOGGING)
    implementation(LibraryDependency.Koin.CORE)
}