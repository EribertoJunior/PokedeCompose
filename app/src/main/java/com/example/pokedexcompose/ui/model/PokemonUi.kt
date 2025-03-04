package com.example.pokedexcompose.ui.model

import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum

data class PokemonUi(
    val id: Int,
    var idFormatted: String,
    var name: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var imageUrl: String? = null,
    var pokemonTypes: List<PokemonType> = listOf(),
)

data class PokemonType(
    var name: String,
    var colour: TypeColoursEnum,
)
