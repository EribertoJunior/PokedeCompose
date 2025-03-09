package com.example.pokedexcompose.list.ui.samples

import com.example.pokedexcompose.comum.ui.TypeColoursEnum
import com.example.pokedexcompose.list.ui.model.PokemonUi

val pokemonSample = listOf(
    PokemonUi(
        id = 1,
        name = "Bulbasaur",
        weight = 123,
        height = 123,
        imageUrl = "",
        types = listOf(
            TypeColoursEnum.GRASS,
            TypeColoursEnum.FIGHTING,
        ),
        idFormatted = "#0001",
    ),
    PokemonUi(
        id = 2,
        name = "Bulbasaur",
        weight = 123,
        height = 123,
        imageUrl = "",
        types = listOf(
            TypeColoursEnum.GRASS,
            TypeColoursEnum.GHOST
        ),
        idFormatted = "#0002",
    ),
    PokemonUi(
        id = 3,
        name = "Bulbasaur",
        weight = 123,
        height = 123,
        imageUrl = "",
        types = listOf(
            TypeColoursEnum.GRASS,
            TypeColoursEnum.FIRE
        ),
        idFormatted = "#0003",
    ),
    PokemonUi(
        id = 4,
        name = "Bulbasaur",
        weight = 123,
        height = 123,
        imageUrl = "",
        types = listOf(
            TypeColoursEnum.GRASS,
            TypeColoursEnum.WATER
        ),
        idFormatted = "#0004",
    ),
)
