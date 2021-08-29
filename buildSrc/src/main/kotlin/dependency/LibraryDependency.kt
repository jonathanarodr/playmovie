package dependency

class LibraryDependency {

    object Retrofit {
        const val CORE = "com.squareup.retrofit2:retrofit:${Version.Library.RETROFIT}"
        const val CONVERTER = "com.squareup.retrofit2:converter-gson:${Version.Library.RETROFIT}"
    }

    object Koin {
        const val CORE = "io.insert-koin:koin-android:${Version.Library.KOIN}"
    }

    object Okhttp {
        const val BOM = "com.squareup.okhttp3:okhttp-bom:${Version.Library.OKHTTP}"
        const val CORE = "com.squareup.okhttp3:okhttp"
        const val LOGGING = "com.squareup.okhttp3:logging-interceptor"
    }
}