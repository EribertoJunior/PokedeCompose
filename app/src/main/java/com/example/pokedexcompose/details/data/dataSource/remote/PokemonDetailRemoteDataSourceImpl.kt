package com.example.pokedexcompose.details.data.dataSource.remote

import com.example.pokedexcompose.list.data.service.PokemonService
import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.data.model.remote.ListPokemonRemote
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.data.model.remote.SpeciesRemote

class PokemonDetailRemoteDataSourceImpl(private val pokemonService: PokemonService) : PokemonDetailRemoteDataSource {
    override suspend fun getListPokemon(limit: Int, offset: Int): ListPokemonRemote {
        return pokemonService.getListPokemon(limit = limit, offset = offset)
    }

    override suspend fun getPokemonDetails(pokemonId: Int): PokemonDetailRemote {
        return pokemonService.getPokemonDetails(pokemonId)
    }

    override suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote? {
        return pokemonService.searchPokemonSpecie(pokemonSpecieUrl)
    }

    override suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote {
        return pokemonService.searchEvolutionChan(evolutionChanUrl)
    }
}