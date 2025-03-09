package com.example.pokedexcompose.comum.di

import android.app.Application
import com.example.pokedexcompose.details.di.pokemonDetailModule
import com.example.pokedexcompose.list.di.pokemonListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

const val BASE_URL = "https://pokeapi.co/api/v2/"
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            properties(
                mapOf(
                    PROPERTY_BASE_URL to BASE_URL
                )
            )
            modules(appModule, pokemonListModule, pokemonDetailModule)
        }
    }
}