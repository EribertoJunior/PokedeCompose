package com.example.pokedexcompose.data.dataSource.local

import com.example.pokedexcompose.data.dataBase.local.dao.EvolutionChainDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonDetailDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonSpeciesDao
import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonSpeciesDao: PokemonSpeciesDao,
    private val evolutionChainDao: EvolutionChainDao,
) : LocalDataSource {
    override fun searchPokemonByName(name: String): Flow<PokemonAndDetail> {
        return pokemonDao.searchPokemonByName(name)
    }

    override suspend fun deleteAllPokemon() {
        pokemonDao.deleteAll()
    }

    override suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>) {
        return pokemonDao.saveAll(pokemonEntities)
    }

    override suspend fun savePokemon(pokemonEntity: PokemonEntity) {
        return pokemonDao.save(pokemonEntity)
    }

    override suspend fun saveAllRemoteKey(pokemonRemoteKeys: List<PokemonRemoteKey>) {
        return pokemonRemoteKeyDao.saveAll(pokemonRemoteKeys)
    }

    override suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKey {
        return pokemonRemoteKeyDao.getPokemonRemoteKeyFromName(pokemonId)
    }

    override suspend fun deleteAllRemoteKey() {
        pokemonRemoteKeyDao.deleteAll()
    }

    override suspend fun saveRemoteKey(pokemonRemoteKey: PokemonRemoteKey) {
        pokemonRemoteKeyDao.save(pokemonRemoteKey)
    }

    override suspend fun saveAllPokemonDetail(pokemonDetails: List<PokemonDetail>) {
        pokemonDetailDao.saveAll(pokemonDetails)
    }

    override suspend fun saveAllPokemonSpecies(species: List<PokemonSpecies>) {
        pokemonSpeciesDao.saveAllSpecie(species)
    }

    override suspend fun saveAllEvolutionChain(evolutionChainEntity: List<EvolutionChainEntity>) {
        evolutionChainDao.saveAll(evolutionChainEntity)
    }

    override suspend fun saveEvolutionChain(evolutionChainEntity: EvolutionChainEntity) {
        evolutionChainDao.save(evolutionChainEntity)
    }

    override fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity? {
        return evolutionChainDao.searchEvolutionChainById(chainId)
    }

}