package com.example.pokedexcompose.data.dataSource.local

import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun searchPokemonByName(name: String): Flow<PokemonAndDetail>

    suspend fun deleteAllPokemon()

    suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>)

    suspend fun savePokemon(pokemonEntity: PokemonEntity)

    suspend fun saveAllRemoteKey(pokemonRemoteKeys: List<PokemonRemoteKey>)

    suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKey

    suspend fun deleteAllRemoteKey()

    suspend fun saveRemoteKey(pokemonRemoteKey: PokemonRemoteKey)

    suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>)

    suspend fun saveAllPokemonSpecies(species: List<PokemonSpecies> )

    suspend fun saveAllEvolutionChain(evolutionChainEntity: List<EvolutionChainEntity>)

    suspend fun saveEvolutionChain(evolutionChainEntity: EvolutionChainEntity)

    fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity?
}