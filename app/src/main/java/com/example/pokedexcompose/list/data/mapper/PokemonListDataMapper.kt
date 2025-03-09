package com.example.pokedexcompose.list.data.mapper

import com.example.pokedexcompose.data.model.remote.ChainRemote
import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.list.domain.model.Pokemon
import com.example.pokedexcompose.comum.extensions.getUrlId
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.SpecieToEvolution

class PokemonListDataMapper {
    fun mapToEvolutionChain(evolutionData: EvolutionChainRemote): List<SpecieToEvolution> {
        val mutableListSpecieToEvolution: ArrayList<SpecieToEvolution> = arrayListOf()
        evolutionData.chain.species.run {
            mutableListSpecieToEvolution.add(
                SpecieToEvolution(
                    name = name,
                    pokemonId = url.getUrlId
                )
            )
        }
        mutableListSpecieToEvolution.addAll(buildListOfEvolutions(evolutionData.chain.evolvesTo))

        return mutableListSpecieToEvolution.toList()
    }

    private fun buildListOfEvolutions(chainRemote: List<ChainRemote>): List<SpecieToEvolution> {
        return chainRemote.flatMap { evolution ->
            listOf(
                SpecieToEvolution(
                    name = evolution.species.name,
                    pokemonId = evolution.species.url.getUrlId
                )
            ) + buildListOfEvolutions(evolution.evolvesTo)
        }
    }

    fun toPokemon(pokemonEntity: PokemonEntity): Pokemon {
        return Pokemon(
            id = pokemonEntity.id,
            name = pokemonEntity.name,
            height = pokemonEntity.height,
            weight = pokemonEntity.weight,
            types = pokemonEntity.types,
            imageUrl = pokemonEntity.imageUrl ?: ""
        )
    }
}