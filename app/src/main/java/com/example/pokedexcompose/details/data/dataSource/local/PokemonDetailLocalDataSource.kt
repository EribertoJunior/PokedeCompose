package com.example.pokedexcompose.details.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity
import kotlinx.coroutines.flow.Flow

interface PokemonDetailLocalDataSource {
    fun searchPokemonById(name: Int): Flow<PokemonAndDetail>

    suspend fun deleteAllPokemon()

    suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>)

    suspend fun savePokemon(pokemonEntity: PokemonEntity)

    suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>)

    suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKeyEntity

    suspend fun deleteAllRemoteKey()

    suspend fun saveRemoteKey(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity)

    suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>)

    suspend fun saveAllPokemonSpecies(species: List<PokemonSpecies>)

    suspend fun saveAllEvolutionChain(evolutionChainEntity: List<EvolutionChainEntity>)

    suspend fun saveEvolutionChain(evolutionChainEntity: EvolutionChainEntity)

    fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity?

    fun getAllPokemon(): PagingSource<Int, PokemonEntity>
}