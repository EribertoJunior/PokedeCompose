package com.example.pokedexcompose.data.mapper

import com.example.pokedexcompose.data.dataBase.local.entities.SpecieToEvolution
import com.example.pokedexcompose.data.model.remote.ChainRemote
import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.extensions.getUrlId

class DataMapper {
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
        val mutableList = arrayListOf<SpecieToEvolution>()
        if (chainRemote.isNotEmpty()) {
            chainRemote.forEach {
                mutableList.add(
                    SpecieToEvolution(
                        name = it.species.name,
                        pokemonId = it.species.url.getUrlId
                    ),
                )
                mutableList.addAll(buildListOfEvolutions(it.evolvesTo))
            }
        } else {
            mutableList.addAll(emptyList())
        }

        return mutableList.toList()
    }
}