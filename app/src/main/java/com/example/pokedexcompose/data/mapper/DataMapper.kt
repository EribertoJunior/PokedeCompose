package com.example.pokedexcompose.data.mapper

import com.example.pokedexcompose.data.dataBase.local.entities.Home
import com.example.pokedexcompose.data.dataBase.local.entities.OfficialArtwork
import com.example.pokedexcompose.data.dataBase.local.entities.Other
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetailSpecies
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetailStats
import com.example.pokedexcompose.data.dataBase.local.entities.Sprites
import com.example.pokedexcompose.data.dataBase.local.entities.Stat
import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote

class DataMapper {
    fun mapPokeDetailRemoteToPokeDetail(pokemonDetailRemote: PokemonDetailRemote): PokemonDetail {
        return pokemonDetailRemote.run {
            PokemonDetail(
                pokemonDetailId = id,
                pokemonOwnerId = id,
                colorTypeList = types.map { dataTypes ->
                    TypeColoursEnum.getTypeFromName(dataTypes.type.name)
                },
                weight = weight,
                height = height,
                sprites = Sprites(
                    Other(
                        officialArtwork = OfficialArtwork(sprites.other.officialArtwork.frontDefault),
                        home = Home(sprites.other.home.frontDefault)
                    )
                ),
                species = PokemonDetailSpecies(
                    name = species?.name,
                    url = species?.url
                ),
                stats = stats.map { pokemonDetailRemoteStats ->
                    PokemonDetailStats(
                        baseStat = pokemonDetailRemoteStats.baseStat,
                        effort = pokemonDetailRemoteStats.effort,
                        stat = Stat(
                            name = pokemonDetailRemoteStats.stat.name,
                            url = pokemonDetailRemoteStats.stat.url
                        )
                    )
                }
            )
        }
    }

}