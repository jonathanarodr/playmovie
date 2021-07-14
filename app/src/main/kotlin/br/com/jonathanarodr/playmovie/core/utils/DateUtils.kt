package br.com.jonathanarodr.playmovie.core.utils

import br.com.jonathanarodr.playmovie.core.utils.DateUtils.DATE_PATTERN_YYYY
import br.com.jonathanarodr.playmovie.core.utils.DateUtils.localePtBr
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val DATE_PATTERN_YYYY = "yyyy"
    val localePtBr = Locale("pt", "BR")
}

fun Date.format(): String {
    return SimpleDateFormat(DATE_PATTERN_YYYY, localePtBr).format(this)
}