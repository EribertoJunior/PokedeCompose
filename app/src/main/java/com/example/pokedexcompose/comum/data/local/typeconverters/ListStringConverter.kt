package com.example.pokedexcompose.comum.data.local.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {

    @TypeConverter
    fun listToJson(value: List<String>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String?): List<String>? {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }
}