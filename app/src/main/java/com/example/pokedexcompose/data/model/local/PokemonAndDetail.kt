package com.example.pokedexcompose.data.model.local

import androidx.room.Embedded
import androidx.room.Relation
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity.Companion.EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail.Companion.POKEMON_DETAIL_OWNER_ID
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies.Companion.POKEMON_SPECIES_EVOLUTION_CHAIN_ID
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies.Companion.POKEMON_SPECIES_OWNER_ID
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity


data class PokemonAndDetail(
    @Embedded val pokemonEntity: PokemonEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = POKEMON_DETAIL_OWNER_ID
    )
    val pokemonDetail: PokemonDetail,
    @Relation(
        entity = PokemonSpecies::class,
        parentColumn = "id",
        entityColumn = POKEMON_SPECIES_OWNER_ID,
    )
    val specieAndEvolutionChain: SpecieAndEvolutionChain? = null
)

data class SpecieAndEvolutionChain(
    @Embedded val pokemonSpecies: PokemonSpecies? = null,
    @Relation(
        parentColumn = POKEMON_SPECIES_EVOLUTION_CHAIN_ID,
        entityColumn = EVOLUTION_CHAIN_ID
    )
    val evolutionChainEntity: EvolutionChainEntity
)