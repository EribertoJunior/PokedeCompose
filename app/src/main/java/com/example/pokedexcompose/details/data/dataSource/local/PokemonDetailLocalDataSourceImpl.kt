package com.example.pokedexcompose.details.data.dataSource.local

import androidx.paging.PagingSource
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.details.data.room.dao.EvolutionChainDao
import com.example.pokedexcompose.details.data.room.dao.PokemonDetailDao
import com.example.pokedexcompose.details.data.room.dao.PokemonSpeciesDao
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies
import com.example.pokedexcompose.list.data.room.dao.PokemonDao
import com.example.pokedexcompose.list.data.room.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity
import kotlinx.coroutines.flow.Flow

class PokemonDetailLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val pokemonRemoteKeyDao: PokemonRemoteKeyDao,
    private val pokemonDetailDao: PokemonDetailDao,
    private val pokemonSpeciesDao: PokemonSpeciesDao,
    private val evolutionChainDao: EvolutionChainDao,
) : PokemonDetailLocalDataSource {
    override fun searchPokemonById(pokemonId: Int): Flow<PokemonAndDetail> {
        return pokemonDao.searchPokemonById(pokemonId)
    }

    override suspend fun deleteAllPokemon() {
        pokemonDao.deleteAll()
    }

    override suspend fun saveAllPokemons(pokemonEntities: List<PokemonEntity>) {
        return pokemonDao.insertAll(pokemonEntities)
    }

    override suspend fun savePokemon(pokemonEntity: PokemonEntity) {
        return pokemonDao.save(pokemonEntity)
    }

    override suspend fun saveAllRemoteKey(pokemonRemoteKeyEntities: List<PokemonRemoteKeyEntity>) {
        return pokemonRemoteKeyDao.insertAll(pokemonRemoteKeyEntities)
    }

    override suspend fun getPokemonRemoteKeyById(pokemonId: Int): PokemonRemoteKeyEntity {
        return pokemonRemoteKeyDao.getRemoteKeyByPokemonId(pokemonId)
    }

    override suspend fun deleteAllRemoteKey() {
        pokemonRemoteKeyDao.deleteAll()
    }

    override suspend fun saveRemoteKey(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity) {
        pokemonRemoteKeyDao.save(pokemonRemoteKeyEntity)
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

    override fun getAllPokemon(): PagingSource<Int, PokemonEntity> {
        return pokemonDao.getAllPokemon()
    }

}