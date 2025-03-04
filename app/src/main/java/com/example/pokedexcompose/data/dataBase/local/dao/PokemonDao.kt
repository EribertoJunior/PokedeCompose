package com.example.pokedexcompose.data.dataBase.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonEntity
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonEntities: List<PokemonEntity>)

    @Transaction
    @Query("Select * From PokemonEntity order by id")
    fun loadPokemons(): PagingSource<Int, PokemonEntity>

    @Query("Delete From PokemonEntity")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(pokemonEntity: PokemonEntity)

    @Transaction
    @Query("Select * From PokemonEntity Where name = :name")
    fun searchPokemonByName(name: String): Flow<PokemonAndDetail>
}