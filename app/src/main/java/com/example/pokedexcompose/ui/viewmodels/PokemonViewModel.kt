package com.example.pokedexcompose.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pokedexcompose.data.model.local.enums.TypeColoursEnum
import com.example.pokedexcompose.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcompose.ui.model.PokemonType
import com.example.pokedexcompose.ui.model.PokemonUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel(private val getPokemonListUseCase: GetPokemonListUseCase) : ViewModel() {

    private var _uiState: MutableStateFlow<PagingData<PokemonUi>> =
        MutableStateFlow(PagingData.from(emptyList()))
    val uiState get() = _uiState.asStateFlow()

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            getPokemonListUseCase().cachedIn(this).collect {
                _uiState.value = it.map {
                    PokemonUi(
                        name = it.name,
                        weight = it.weight,
                        height = it.height,
                        id = it.id,
                        idFormatted = it.id.toString(),
                        imageUrl = it.imageUrl,
                        pokemonTypes = it.types.map { typeName ->
                            PokemonType(
                                name = typeName,
                                colour = TypeColoursEnum.getTypeFromName(typeName)
                            )
                        }
                    )
                }
            }
        }
    }
}