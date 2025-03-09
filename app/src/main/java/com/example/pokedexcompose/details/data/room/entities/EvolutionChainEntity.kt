package com.example.pokedexcompose.details.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EvolutionChainEntity(
    @PrimaryKey val evolutionChainId: Int
){
    companion object{
        const val EVOLUTION_CHAIN_ID = "evolutionChainId"
    }
}
