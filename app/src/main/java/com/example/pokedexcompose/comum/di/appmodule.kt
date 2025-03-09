package com.example.pokedexcompose.comum.di

import com.example.pokedexcompose.comum.data.local.RoomConfig
import com.example.pokedexcompose.comum.data.remote.RetrofitConfig
import com.example.pokedexcompose.details.data.dataSource.local.PokemonDetailLocalDataSource
import com.example.pokedexcompose.details.data.dataSource.local.PokemonDetailLocalDataSourceImpl
import com.example.pokedexcompose.details.data.dataSource.remote.PokemonDetailRemoteDataSource
import com.example.pokedexcompose.details.data.dataSource.remote.PokemonDetailRemoteDataSourceImpl
import com.example.pokedexcompose.list.data.mapper.PokemonListDataMapper
import com.example.pokedexcompose.details.domain.DetailRepository
import com.example.pokedexcompose.details.data.repository.DetailRepositoryImpl
import com.example.pokedexcompose.list.ui.mapper.PokemonUiMapper
import com.example.pokedexcompose.details.viewmodel.DetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "SERVER_URL"

val appModule = module {
    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        RetrofitConfig(baseUrl, androidContext()).getPokeService()
    }
    single { RoomConfig.getDataBase(androidContext()) }

    factory<DetailRepository> {
        DetailRepositoryImpl(
            pokemonDetailLocalDataSource = get()
        )
    }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory { get<RoomConfig>().pokemonSpeciesDao() }
    factory { get<RoomConfig>().pokemonEvolutionChainDao() }

    factory<PokemonDetailRemoteDataSource> { PokemonDetailRemoteDataSourceImpl(get()) }
    factory<PokemonDetailLocalDataSource> {
        PokemonDetailLocalDataSourceImpl(
            pokemonDao = get(),
            pokemonDetailDao = get(),
            pokemonSpeciesDao = get(),
            evolutionChainDao = get(),
            pokemonRemoteKeyDao = get()
        )
    }

    single { PokemonListDataMapper() }
    single { PokemonUiMapper(context = androidContext()) }



    viewModel { DetailsViewModel(detailRepository = get()) }
}