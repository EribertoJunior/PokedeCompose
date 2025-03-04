package com.example.pokedexcompose.data.dataBase.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies

@Dao
fun interface PokemonSpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSpecie(species: List<PokemonSpecies> )
}