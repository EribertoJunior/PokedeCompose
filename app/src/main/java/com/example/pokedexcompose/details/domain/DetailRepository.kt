package com.example.pokedexcompose.details.domain

import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

fun interface DetailRepository {
    fun searchPokemonById(pokemonId: Int): Flow<PokemonAndDetail>
}