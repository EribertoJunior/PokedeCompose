package com.example.pokedexcompose.data.dataBase.remote

import com.example.pokedexcompose.data.model.remote.EvolutionChainRemote
import com.example.pokedexcompose.data.model.remote.ListPokemonRemote
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.data.model.remote.SpeciesRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonService {
    @GET("pokemon")
    suspend fun getListPokemon(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int
    ): ListPokemonRemote

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") pokemonName: Int) : PokemonDetailRemote

    @GET
    suspend fun searchPokemonSpecie(@Url pokemonSpecieUrl: String): SpeciesRemote?

    @GET
    suspend fun searchEvolutionChan(@Url evolutionChanUrl: String): EvolutionChainRemote
}