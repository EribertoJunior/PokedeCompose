package com.example.pokedexcompose.list.di

import com.example.pokedexcompose.comum.data.local.RoomConfig
import com.example.pokedexcompose.list.data.PokemonRemoteMediator
import com.example.pokedexcompose.list.data.dataSource.local.PokemonListLocalDataSource
import com.example.pokedexcompose.list.data.dataSource.local.PokemonListLocalDataSourceImpl
import com.example.pokedexcompose.list.data.dataSource.remote.PokemonListRemoteDataSource
import com.example.pokedexcompose.list.data.dataSource.remote.PokemonListRemoteDataSourceImpl
import com.example.pokedexcompose.list.data.repository.PokemonListRepositoryImpl
import com.example.pokedexcompose.list.domain.PokemonListRepository
import com.example.pokedexcompose.list.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcompose.list.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonListModule = module {
    factory { get<RoomConfig>().pokemonDao() }
    factory { get<RoomConfig>().pokemonRemoteKeyDao() }

    factory { GetPokemonListUseCase(pokemonListRepository = get()) }
    factory<PokemonListLocalDataSource> {
        PokemonListLocalDataSourceImpl(
            pokemonDao = get(),
            pokemonRemoteKeyDao = get()
        )
    }
    factory<PokemonListRemoteDataSource> { PokemonListRemoteDataSourceImpl(pokemonService = get()) }
    factory<PokemonListRepository> {
        PokemonListRepositoryImpl(
            pokemonRemoteMediator = get(),
            pokemonListLocalDataSource = get(),
            pokemonListDataMapper = get()
        )
    }

    factory {
        PokemonRemoteMediator(
            pokemonListRemoteDataSource = get(),
            pokemonListLocalDataSource = get()
        )
    }

    viewModel { PokemonListViewModel(getPokemonListUseCase = get(), pokemonUiMapper = get()) }
}