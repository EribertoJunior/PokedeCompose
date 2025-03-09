package com.example.pokedexcompose.details.data.repository

import com.example.pokedexcompose.details.data.dataSource.local.PokemonDetailLocalDataSource
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.details.domain.DetailRepository
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val pokemonDetailLocalDataSource: PokemonDetailLocalDataSource
) : DetailRepository {

    override fun searchPokemonById(pokemonId: Int): Flow<PokemonAndDetail> {
        return pokemonDetailLocalDataSource.searchPokemonById(pokemonId)
    }
}