package com.example.pokedexcompose.list.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokedexcompose.list.data.PokemonRemoteMediator
import com.example.pokedexcompose.list.data.dataSource.local.PokemonListLocalDataSource
import com.example.pokedexcompose.list.data.mapper.PokemonListDataMapper
import com.example.pokedexcompose.list.domain.PokemonListRepository
import com.example.pokedexcompose.list.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonListRepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonListLocalDataSource: PokemonListLocalDataSource,
    private val pokemonListDataMapper: PokemonListDataMapper
) : PokemonListRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(pagingConfig: PagingConfig): Flow<PagingData<Pokemon>> {
        return Pager(
            config = pagingConfig,
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = {
                pokemonListLocalDataSource.getAllPokemon()
            }
        ).flow.map { pagingData ->
            pagingData.map {
                pokemonListDataMapper.toPokemon(it)
            }
        }
    }
}