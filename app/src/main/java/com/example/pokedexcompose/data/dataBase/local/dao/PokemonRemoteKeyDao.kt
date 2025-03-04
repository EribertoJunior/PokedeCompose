package com.example.pokedexcompose.data.dataBase.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey

@Dao
interface PokemonRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonRemoteKey: List<PokemonRemoteKey>)

    @Query("Select * From PokemonRemoteKey Where id = :pokemonId")
    suspend fun getPokemonRemoteKeyFromName(pokemonId: Int): PokemonRemoteKey

    @Query("Delete From PokemonRemoteKey")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonRemoteKey: PokemonRemoteKey)
}