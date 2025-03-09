package com.example.pokedexcompose.list.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity

@Dao
interface PokemonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonRemoteKeyEntity: List<PokemonRemoteKeyEntity>)

    @Query("Select * From PokemonRemoteKeyEntity Where id = :pokemonId")
    suspend fun getRemoteKeyByPokemonId(pokemonId: Int): PokemonRemoteKeyEntity

    @Query("Delete From PokemonRemoteKeyEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonRemoteKeyEntity: PokemonRemoteKeyEntity)
}