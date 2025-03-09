package com.example.pokedexcompose.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexcompose.list.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcompose.list.ui.mapper.PokemonUiMapper
import com.example.pokedexcompose.list.ui.model.PokemonUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val pokemonUiMapper: PokemonUiMapper
) : ViewModel() {

    private var _uiState: MutableStateFlow<PagingData<PokemonUi>> =
        MutableStateFlow(PagingData.Companion.from(emptyList()))
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            getPokemonListUseCase().cachedIn(this).collect { pagingData ->
                _uiState.value = pagingData.map {
                    pokemonUiMapper.mapToPokemonUi(it)
                }
            }
        }
    }
}