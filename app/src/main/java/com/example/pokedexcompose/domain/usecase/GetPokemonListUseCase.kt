package com.example.pokedexcompose.domain.usecase

import androidx.paging.PagingData
import com.example.pokedexcompose.data.repository.PokemonRepository
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

class GetPokemonListUseCase(private val pokemonRepository: PokemonRepository) {
    operator fun invoke(): Flow<PagingData<Pokemon>> {
        return pokemonRepository.getPokemonList()
    }
}