package com.example.pokedexcompose.details.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail

@Dao
fun interface PokemonDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(pokemonDetails: List<PokemonDetail>)
}