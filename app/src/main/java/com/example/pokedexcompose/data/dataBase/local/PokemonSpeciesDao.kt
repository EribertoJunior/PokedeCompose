package com.example.pokedexcompose.data.dataBase.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies

@Dao
interface PokemonSpeciesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllSpecie(species: List<PokemonSpecies> )
}