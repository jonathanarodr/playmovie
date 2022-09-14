package br.com.jonathanarodr.playmovie.common.utils

import br.com.jonathanarodr.playmovie.common.annotation.DatePattern
import br.com.jonathanarodr.playmovie.common.utils.DateUtils.localePtBr
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    val localePtBr = Locale("pt", "BR")
}

fun Date.format(@DatePattern pattern: String): String {
    return SimpleDateFormat(pattern, localePtBr).format(this)
}
