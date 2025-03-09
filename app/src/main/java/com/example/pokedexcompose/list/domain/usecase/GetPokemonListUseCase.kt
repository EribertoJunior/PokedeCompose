package com.example.pokedexcompose.list.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.list.domain.PokemonListRepository
import com.example.pokedexcompose.list.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val pokemonListRepository: PokemonListRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return pokemonListRepository.getPokemonList(createPagingConfig())
    }

    private fun createPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = PAGE_SIZE,
            maxSize = MAX_SIZE,
            initialLoadSize = INITIAL_LOAD_SIZE,
            prefetchDistance = PREFETCH_SIZE,
            enablePlaceholders = false
        )
    }

    companion object {
        private const val PAGE_SIZE = 500
        private const val PREFETCH_SIZE = 250
        private const val MAX_SIZE = PAGE_SIZE + (PREFETCH_SIZE * 2)
        private const val INITIAL_LOAD_SIZE = PAGE_SIZE + PREFETCH_SIZE
    }
}