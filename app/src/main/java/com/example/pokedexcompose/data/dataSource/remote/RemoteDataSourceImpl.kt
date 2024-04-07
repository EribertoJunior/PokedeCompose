package com.example.pokedexcompose.data.dataSource.remote

import com.example.pokedexcompose.data.dataBase.remote.PokemonService
import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.data.model.remote.ListPokemonRemote
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.data.model.remote.SpeciesRemote

class RemoteDataSourceImpl(private val pokemonService: PokemonService) : RemoteDataSource {
    override suspend fun getListPokemon(limit: Int, offset: Int): ListPokemonRemote {
        return pokemonService.getListPokemon(limit = limit, offset = offset)
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokemonDetailRemote {
        return pokemonService.getPokemonDetails(pokemonName)
    }

    override suspend fun searchPokemonSpecie(pokemonSpecieUrl: String): SpeciesRemote? {
        return pokemonService.searchPokemonSpecie(pokemonSpecieUrl)
    }

    override suspend fun searchEvolutionChan(evolutionChanUrl: String): EvolutionChainRemote {
        return pokemonService.searchEvolutionChan(evolutionChanUrl)
    }
}