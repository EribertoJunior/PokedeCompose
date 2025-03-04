package com.example.pokedexcompose.di

import com.example.pokedexcompose.data.dataBase.local.RoomConfig
import com.example.pokedexcompose.data.dataBase.remote.PokemonPagingSource
import com.example.pokedexcompose.data.dataBase.remote.RetrofitConfig
import com.example.pokedexcompose.data.dataSource.local.LocalDataSource
import com.example.pokedexcompose.data.dataSource.local.LocalDataSourceImpl
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSource
import com.example.pokedexcompose.data.dataSource.remote.RemoteDataSourceImpl
import com.example.pokedexcompose.data.mapper.DataMapper
import com.example.pokedexcompose.data.repository.DetailRepository
import com.example.pokedexcompose.data.repository.DetailRepositoryImpl
import com.example.pokedexcompose.data.repository.PokemonRepository
import com.example.pokedexcompose.data.repository.PokemonRepositoryImpl
import com.example.pokedexcompose.data.repository.PokemonRemoteMediator
import com.example.pokedexcompose.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcompose.ui.viewmodels.DetailsViewModel
import com.example.pokedexcompose.ui.viewmodels.PokemonViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val PROPERTY_BASE_URL = "SERVER_URL"

val modules = module {
    single {
        val baseUrl = getProperty<String>(PROPERTY_BASE_URL)
        RetrofitConfig(baseUrl, androidContext()).getPokeServide()
    }
    single { RoomConfig.getDataBase(androidContext()) }

    factory { PokemonPagingSource(get()) }

    factory<PokemonRepository> {
        PokemonRepositoryImpl(
            pokemonRemoteMediator = get(),
            pokemonDao = get()
        )
    }
    factory<DetailRepository> {
        DetailRepositoryImpl(
            localDataSource = get()
        )
    }

    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }
    factory { get<RoomConfig>().pokemonDetailDao() }
    factory { get<RoomConfig>().pokemonSpeciesDao() }
    factory { get<RoomConfig>().pokemonEvolutionChainDao() }

    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    factory<LocalDataSource> {
        LocalDataSourceImpl(
            pokemonDao = get(),
            pokemonDetailDao = get(),
            pokemonSpeciesDao = get(),
            evolutionChainDao = get(),
            pokemonRemoteKeyDao = get()
        )
    }

    single { DataMapper() }

    factory {
        PokemonRemoteMediator(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }

    factory { GetPokemonListUseCase(get()) }

    viewModel { PokemonViewModel(getPokemonListUseCase = get()) }
    viewModel { DetailsViewModel(detailRepository = get()) }
}