package com.example.pokedexcompose.list.ui.model

import com.example.pokedexcompose.comum.ui.TypeColoursEnum

data class PokemonUi(
    val id: Int,
    var idFormatted: String,
    var name: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var imageUrl: String? = null,
    var types: List<TypeColoursEnum> = listOf(),
)