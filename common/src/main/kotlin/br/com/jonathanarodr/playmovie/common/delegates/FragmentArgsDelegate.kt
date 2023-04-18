package br.com.jonathanarodr.playmovie.common.delegates

import android.os.Parcelable
import androidx.fragment.app.Fragment
import br.com.jonathanarodr.playmovie.common.utils.getParcelableExtraCompat
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

internal const val FRAGMENT_ARGS = "FRAGMENT_ARGS"

class FragmentArgsDelegate<T : Parcelable> : ReadOnlyProperty<Fragment, T> {

    private var value: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        if (value == null) {
            val argument = thisRef.requireArguments().getParcelableExtraCompat<Any>(FRAGMENT_ARGS)

            @Suppress("UNCHECKED_CAST")
            value = requireNotNull(argument) {
                "Fragment $thisRef does not have argument with key '$FRAGMENT_ARGS'."
            } as? T
        }

        return value as T
    }
}
