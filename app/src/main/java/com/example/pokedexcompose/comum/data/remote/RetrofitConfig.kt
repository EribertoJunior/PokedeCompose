package com.example.pokedexcompose.comum.data.remote

import android.content.Context
import com.example.pokedexcompose.list.data.service.PokemonService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig(private val baseUrl: String, private val context: Context?) {

    private fun getRetroInstance() = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(OkHttpProvider.getOkHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getPokeService(): PokemonService = getRetroInstance().create(PokemonService::class.java)
}