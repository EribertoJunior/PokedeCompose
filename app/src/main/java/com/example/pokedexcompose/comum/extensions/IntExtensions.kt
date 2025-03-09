package com.example.pokedexcompose.comum.extensions

fun Int.toDoubleFormat(decimals: Int):String {
    return (this.toDouble()/10).toString().format("%.${decimals}f", this)
}