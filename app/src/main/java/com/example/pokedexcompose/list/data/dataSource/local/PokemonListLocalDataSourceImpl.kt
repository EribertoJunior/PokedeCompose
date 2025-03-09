package com.example.pokedexcompose.list.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.list.data.room.dao.PokemonDao
import com.example.pokedexcompose.list.data.room.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity

class PokemonListLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
) : PokemonListLocalDataSource {
    override suspend fun saveAllPokemon(pokemonEntities: List<PokemonEntity>) {
        pokemonDao.insertAll(pokemonEntities)
    }

    override suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>) {
        pokemonRemoteKeyDao.insertAll(pokemonRemoteKeyEntities)
    }

    override suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKeyEntity {
        return pokemonRemoteKeyDao.getRemoteKeyByPokemonId(pokemonId)
    }

    override fun getAllPokemon(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.getAllPokemon()
    }
}