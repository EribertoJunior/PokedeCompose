package com.example.pokedexcompose.samples

import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum
import com.example.pokedexcompose.ui.model.PokemonType
import com.example.pokedexcompose.ui.model.PokemonUi

private fun setUrlImage(idPokemon: Int) =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${idPokemon}.png"

val pokemonSample = listOf(
    PokemonUi(
        id = 1,
        name = "Bulbasaur",
        weight = 123,
        height = 123,
        imageUrl = "",
        pokemonTypes = listOf(
            PokemonType(
                name = "GRASS",
                colour = TypeColoursEnum.GRASS
            )
        ),
        idFormatted = "#0000",
    )
)
