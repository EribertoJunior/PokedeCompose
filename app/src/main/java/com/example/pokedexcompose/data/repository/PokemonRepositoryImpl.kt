package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonDao
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonRemoteMediator: PokemonRemoteMediator,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    @ExperimentalPagingApi
    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                initialLoadSize = PAGE_SIZE + PREFETCH_SIZE,
                prefetchDistance = PREFETCH_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = {
                pokemonDao.loadPokemons()
            }
        ).flow.map { pagingData ->
            pagingData.map {
                Pokemon(
                    id = it.id,
                    name = it.name,
                    height = it.height,
                    weight = it.weight,
                    types = it.types,
                    imageUrl = it.imageUrl ?: ""
                )
            }
        }
    }

    companion object {
        const val PAGE_SIZE = 100
        private const val PREFETCH_SIZE = 50
        private const val MAX_SIZE = PAGE_SIZE + (PREFETCH_SIZE * 2)
    }
}