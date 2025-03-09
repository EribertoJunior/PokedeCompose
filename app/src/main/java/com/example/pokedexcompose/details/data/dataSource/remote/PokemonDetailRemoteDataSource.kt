package com.example.pokedexcompose.details.data.dataSource.remote

import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.data.model.remote.ListPokemonRemote
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.data.model.remote.SpeciesRemote

interface PokemonDetailRemoteDataSource {
    suspend fun getListPokemon(
        limit: Int = 10,
        offset: Int
    ): ListPokemonRemote

    suspend fun getPokemonDetails(pokemonId: Int) : PokemonDetailRemote

    suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote?

    suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote
}