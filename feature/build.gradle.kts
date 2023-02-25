plugins {
    id("playmovie.android.module")
}

android {
    namespace = "br.com.jonathanarodr.playmovie.feature"
}

dependencies {
    implementation(projects.common)
    implementation(projects.network)

    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.room.core)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.material)
}
