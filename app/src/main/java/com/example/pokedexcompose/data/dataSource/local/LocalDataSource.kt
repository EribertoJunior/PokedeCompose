package com.example.pokedexcompose.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChain
import com.example.pokedexcompose.data.dataBase.local.entities.Pokemon
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getPokemons(): PagingSource<Int, PokemonAndDetail>

    fun searchPokemonByName(name: String): Flow<PokemonAndDetail>

    suspend fun deleteAllPokemon()

    suspend fun saveAllPokemons(pokemons: List<Pokemon>)

    suspend fun savePokemon(pokemon: Pokemon)

    suspend fun saveAllRemoteKey(pokemonRemoteKeys: List<PokemonRemoteKey>)

    suspend fun getPokemonRemoteKeyByName(pokemonName: String): PokemonRemoteKey

    suspend fun deleteAllRemoteKey()

    suspend fun saveRemoteKey(pokemonRemoteKey: PokemonRemoteKey)

    suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>)

    suspend fun saveAllPokemonSpecies(species: List<PokemonSpecies> )

    suspend fun saveAllEvolutionChain(evolutionChain: List<EvolutionChain>)

    suspend fun saveEvolutionChain(evolutionChain: EvolutionChain)

    fun searchEvolutionChainById(chainId: Int): EvolutionChain?
}