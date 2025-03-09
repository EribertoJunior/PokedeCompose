package com.example.pokedexcompose.details.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity

@Dao
interface EvolutionChainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(evolutionChainEntity: List<EvolutionChainEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(evolutionChainEntity: EvolutionChainEntity)

    @Query("Select * From EvolutionChainEntity Where evolutionChainId = :chainId")
    fun searchEvolutionChainById(chainId: Int): EvolutionChainEntity?
}