package com.example.pokedexcompose.data.dataBase.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonRemoteKey(
    @PrimaryKey()
    var id: Int = 0,
    var prevOffset: Int?,
    var nextOffset: Int?
)