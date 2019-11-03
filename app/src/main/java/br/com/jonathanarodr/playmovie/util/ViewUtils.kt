package br.com.jonathanarodr.playmovie.util

import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity

fun <T : View> AppCompatActivity.findViewByLazy(@IdRes id: Int): Lazy<T> {
    return lazy { findViewById<T>(id) }
}