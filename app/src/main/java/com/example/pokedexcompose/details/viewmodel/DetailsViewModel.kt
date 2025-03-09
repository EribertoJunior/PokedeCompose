package com.example.pokedexcompose.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.details.domain.DetailRepository
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailRepository: DetailRepository) : ViewModel() {

    private var _uiState: MutableStateFlow<PokemonAndDetail> =
        MutableStateFlow(
            PokemonAndDetail(
                pokemonEntity = PokemonEntity(),
                pokemonDetail = PokemonDetail(),
                specieAndEvolutionChain = null
            )
        )
    val uiState get() = _uiState.asStateFlow()

    fun searchEvolutionChain(pokemonId: Int) {
        viewModelScope.launch {
            detailRepository.searchPokemonById(pokemonId).collectLatest {
                _uiState.value = it
            }
        }
    }
}