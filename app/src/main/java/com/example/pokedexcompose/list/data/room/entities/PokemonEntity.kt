package com.example.pokedexcompose.list.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedexcompose.comum.data.local.typeconverters.ListStringConverter

@Entity
data class PokemonEntity(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var imageUrl: String? = null,
    @TypeConverters(ListStringConverter::class)
    var types: List<String> = listOf()
)

data class SpecieToEvolution(
    val name: String,
    val pokemonId: Int,
)
