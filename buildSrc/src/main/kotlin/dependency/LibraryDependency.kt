package dependency

object LibraryDependency {

    const val RETROFIT_CORE = "com.squareup.retrofit2:retrofit:${Version.Library.RETROFIT}"
    const val RETROFIT_CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.Library.RETROFIT}"

    const val OKHTTP_BOM = "com.squareup.okhttp3:okhttp-bom:${Version.Library.OKHTTP}"
    const val OKHTTP_CORE = "com.squareup.okhttp3:okhttp"
    const val OKHTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor"

    const val GLIDE_CORE = "com.github.bumptech.glide:glide:${Version.Library.GLIDE}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.Library.GLIDE}"

    const val KOIN = "io.insert-koin:koin-android:${Version.Library.KOIN}"
    const val TIMBER = "com.jakewharton.timber:timber:${Version.Library.TIMBER}"
}