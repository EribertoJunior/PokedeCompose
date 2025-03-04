package com.example.pokedexcompose.data.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

fun interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>
}