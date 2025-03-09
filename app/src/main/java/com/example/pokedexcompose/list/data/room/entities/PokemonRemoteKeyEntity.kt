package com.example.pokedexcompose.list.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonRemoteKeyEntity(
    @PrimaryKey()
    var id: Int = 0,
    var prevOffset: Int?,
    var nextOffset: Int?
)