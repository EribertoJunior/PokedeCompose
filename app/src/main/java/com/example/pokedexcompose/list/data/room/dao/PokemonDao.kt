package com.example.pokedexcompose.list.data.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonEntities: List<PokemonEntity>)

    @Query("Select * From PokemonEntity order by id")
    fun getAllPokemon(): PagingSource<Int, PokemonEntity>

    @Query("Delete From PokemonEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonEntity: PokemonEntity)

    @Transaction
    @Query("Select * From PokemonEntity Where id = :pokemonId")
    fun searchPokemonById(pokemonId: Int): Flow<PokemonAndDetail>
}