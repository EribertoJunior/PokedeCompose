package com.example.pokedexcompose.details.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies

@Dao
fun interface PokemonSpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSpecie(species: List<PokemonSpecies> )
}