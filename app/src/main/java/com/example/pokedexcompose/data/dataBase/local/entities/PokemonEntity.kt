package com.example.pokedexcompose.data.dataBase.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokedexcompose.data.dataBase.local.typeconverters.ListStringConverter

@Entity
data class PokemonEntity(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var weight: Int = 0,
    var height: Int = 0,
    var imageUrl: String? = null,
    @TypeConverters(ListStringConverter::class)
    var types: List<String> = listOf(),
//    var flavorText: String? = null,
//    @TypeConverters(ConverterSpecieToEvolution::class)
//    var evolutionList: List<SpecieToEvolution> = listOf()
) {
    val idFormatted: String
        get() {
            return if (id < 10) {
                "#00$id"
            } else if (id in 10..99) {
                "#0$id"
            } else {
                "#$id"
            }
        }

    companion object {
        const val POKEMON_ID = "id"
    }
}

data class SpecieToEvolution(
    val name: String,
    val pokemonId: Int,
)
