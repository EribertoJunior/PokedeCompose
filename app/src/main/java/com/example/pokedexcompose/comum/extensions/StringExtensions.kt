package com.example.pokedexcompose.comum.extensions

import androidx.compose.ui.graphics.Color
import java.util.Locale
import androidx.core.graphics.toColorInt

val String.color
    get() = Color(this.toColorInt())

val String.getUrlId
    get() = run {
        val regex = "/\\d+".toRegex()
        regex.find(this)?.value?.replace("/", "")?.toInt() ?: 0
    }

val String.titlecase
    get() = run {
        replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
