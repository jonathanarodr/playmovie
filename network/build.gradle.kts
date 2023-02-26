plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.network"

    defaultConfig {
        buildConfigField("String", "SERVER_URL", "\"https://api.themoviedb.org\"")
        buildConfigField("String", "AUTHORIZATION_KEY", "\"[my-authorization-key]\"")
    }
}

dependencies {
    api(libs.retrofit.core)
    implementation(libs.retrofit.converter)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
}
