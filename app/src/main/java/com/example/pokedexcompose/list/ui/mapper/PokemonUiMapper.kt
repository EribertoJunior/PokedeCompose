package com.example.pokedexcompose.list.ui.mapper

import android.content.Context
import com.example.pokedexcompose.R
import com.example.pokedexcompose.comum.ui.TypeColoursEnum
import com.example.pokedexcompose.list.domain.model.Pokemon
import com.example.pokedexcompose.list.ui.model.PokemonUi

class PokemonUiMapper(private val context: Context) {
    fun mapToPokemonUi(pokemon: Pokemon): PokemonUi {
        return PokemonUi(
            name = pokemon.name,
            weight = pokemon.weight,
            height = pokemon.height,
            id = pokemon.id,
            idFormatted = pokemon.id.toString(),
            imageUrl = context.getString(R.string.pokemon_image_url, pokemon.id),
            types = pokemon.types.map { typeName ->
                TypeColoursEnum.getTypeFromName(typeName)
            }
        )
    }
}