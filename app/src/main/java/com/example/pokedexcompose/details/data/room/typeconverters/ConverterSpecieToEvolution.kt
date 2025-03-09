package com.example.pokedexcompose.details.data.room.typeconverters

import androidx.room.TypeConverter
import com.example.pokedexcompose.list.data.room.entities.SpecieToEvolution
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterSpecieToEvolution {
    @TypeConverter
    fun restoreList(typeSpeciesString: String): List<SpecieToEvolution> {
        return Gson().fromJson(
            typeSpeciesString,
            object : TypeToken<List<SpecieToEvolution>>() {}.type
        )
    }

    @TypeConverter
    fun saveListToListString(typeColoursEnums: List<SpecieToEvolution>): String {
        return Gson().toJson(typeColoursEnums)
    }
}