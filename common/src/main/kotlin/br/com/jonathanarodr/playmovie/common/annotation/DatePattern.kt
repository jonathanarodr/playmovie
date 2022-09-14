package br.com.jonathanarodr.playmovie.common.annotation

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(DATE_PATTERN_YYYY, DATE_PATTERN_DD_MM_YYYY)
annotation class DatePattern
const val DATE_PATTERN_YYYY = "yyyy"
const val DATE_PATTERN_DD_MM_YYYY = "dd/MM/yyyy"