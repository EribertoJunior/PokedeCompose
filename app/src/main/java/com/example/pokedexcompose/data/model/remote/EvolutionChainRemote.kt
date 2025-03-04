package com.example.pokedexcompose.data.model.remote

import com.example.pokedexcompose.data.dataBase.local.entities.SpecieToEvolution
import com.example.pokedexcompose.extensions.getUrlId
import com.google.gson.annotations.SerializedName

data class EvolutionChainRemote(
    @SerializedName("id") val id: Int,
    @SerializedName("chain") val chain: ChainRemote
) {
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

data class ChainRemote(
    @SerializedName("evolves_to") val evolvesTo: List<ChainRemote>,
    @SerializedName("species") val species: SpecieToEvolutionRemote
)

data class SpecieToEvolutionRemote(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)
