package br.com.jonathanarodr.playmovie.common.utils

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import br.com.jonathanarodr.playmovie.common.delegates.ACTIVITY_ARGS
import br.com.jonathanarodr.playmovie.common.delegates.ActivityArgsDelegate
import br.com.jonathanarodr.playmovie.common.delegates.FRAGMENT_ARGS
import br.com.jonathanarodr.playmovie.common.delegates.FragmentArgsDelegate

inline fun <reified T> Bundle.getParcelableExtraCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelable(key) as? T
    }
}

inline fun <reified T : Parcelable> AppCompatActivity.safeArgs() = ActivityArgsDelegate<T>()

fun Intent.putSafeArgs(value: Parcelable) = apply {
    putExtra(ACTIVITY_ARGS, value)
}

inline fun <reified T : Parcelable> Fragment.safeArgs() = FragmentArgsDelegate<T>()

fun <T : Fragment> T.putSafeArgs(argument: Parcelable): T = apply {
    arguments = bundleOf(FRAGMENT_ARGS to argument)
}
