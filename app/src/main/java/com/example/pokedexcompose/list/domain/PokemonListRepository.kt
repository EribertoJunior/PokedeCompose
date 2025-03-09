package com.example.pokedexcompose.list.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.list.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

fun interface PokemonListRepository {
    fun getPokemonList(pagingConfig: PagingConfig): Flow<PagingData<Pokemon>>
}