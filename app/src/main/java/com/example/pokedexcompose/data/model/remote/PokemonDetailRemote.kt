package com.example.pokedexcompose.data.model.remote

import com.example.pokedexcompose.data.dataBase.local.entities.Home
import com.example.pokedexcompose.data.dataBase.local.entities.OfficialArtwork
import com.example.pokedexcompose.data.dataBase.local.entities.Other
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetailSpecies
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.dataBase.local.entities.Sprites
import com.example.pokedexcompose.data.dataBase.local.entities.Stat
import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum
import com.google.gson.annotations.SerializedName

data class PokemonDetailRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("types") val types: List<DataTypesRemote>,
    @SerializedName("sprites") val sprites: SpritesRemote,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("species") val species: PokemonDetailRemoteSpecies?,
    @SerializedName("stats") val stats: List<PokemonDetailRemoteStats>,
)

data class PokemonDetailRemoteStats(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: StatRemote,
)

data class StatRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonDetailRemoteSpecies(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class SpritesRemote(
    @SerializedName("other") var other: OtherRemote
)

data class OtherRemote(
    @SerializedName("official-artwork") var officialArtwork: OfficialArtworkRemote,
    @SerializedName("home") var home: HomeRemote,
)

data class HomeRemote(
    @SerializedName("front_default") var frontDefault: String?
)

data class OfficialArtworkRemote(
    @SerializedName("front_default") var frontDefault: String?
)

data class DataTypesRemote(
    @SerializedName("slot") var slot: Int,
    @SerializedName("type") var type: TypeRemote
)

data class TypeRemote(
    @SerializedName("name") var name: String
)
