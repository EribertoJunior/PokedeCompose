package com.example.pokedexcompose.list.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity

interface PokemonListLocalDataSource {
    suspend fun saveAllPokemon(pokemonEntities: List<PokemonEntity>)
    suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>)
    suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKeyEntity
    fun getAllPokemon(): PagingSource<Int, PokemonEntity>
}