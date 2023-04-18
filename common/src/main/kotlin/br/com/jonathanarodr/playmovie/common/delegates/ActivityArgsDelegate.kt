package br.com.jonathanarodr.playmovie.common.delegates

import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import br.com.jonathanarodr.playmovie.common.utils.getParcelableExtraCompat
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal const val ACTIVITY_ARGS = "ACTIVITY_ARGS"

class ActivityArgsDelegate<T : Parcelable> : ReadOnlyProperty<AppCompatActivity, T> {

    private var value: T? = null

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        if (value == null) {
            val argument = requireNotNull(thisRef.intent.extras) {
                "Activity $thisRef does not have any extras."
            }.getParcelableExtraCompat<Any>(ACTIVITY_ARGS)

            @Suppress("UNCHECKED_CAST")
            value = requireNotNull(argument) {
                "Activity $thisRef does not have argument with key '$ACTIVITY_ARGS'."
            } as? T
        }

        return value as T
    }
}
