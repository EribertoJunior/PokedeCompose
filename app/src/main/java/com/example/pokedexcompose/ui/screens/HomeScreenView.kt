package com.example.pokedexcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.pokedexcompose.ui.viewmodels.HomeViewModel
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.samples.listPokemonSample
import com.example.pokedexcompose.ui.components.LoadingAnimation
import com.example.pokedexcompose.ui.components.PokemonItem
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(viewModel: HomeViewModel, onClickPokemon: (PokemonAndDetail) -> Unit = {}) {
    HomeScreenView(
        pokemonAndDetailLazyPagingItems = viewModel.uiState.collectAsLazyPagingItems(),
        onClickPokemon
    )
}

@Composable
fun HomeScreenView(
    pokemonAndDetailLazyPagingItems: LazyPagingItems<PokemonAndDetail>,
    onClickPokemon: (PokemonAndDetail) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(
                count = pokemonAndDetailLazyPagingItems.itemCount,
                key = pokemonAndDetailLazyPagingItems.itemKey { it.pokemon.pokemonId }
            ) { index ->
                pokemonAndDetailLazyPagingItems[index]?.let { pokemonAndDetail ->
                    PokemonItem(
                        pokemonAndDetail = pokemonAndDetail,
                        onClickPokemon = onClickPokemon
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp)
        ) {

            when (pokemonAndDetailLazyPagingItems.loadState.refresh) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }

                is LoadState.NotLoading -> Unit
            }

            when (pokemonAndDetailLazyPagingItems.loadState.append) {
                is LoadState.Error -> Unit
                LoadState.Loading -> {
                    LoadingAnimation()
                }

                is LoadState.NotLoading -> Unit
            }
        }
    }
}

@Preview(showBackground = true)
@Preview("Pokemon List Content - Pixel 2", device = Devices.PIXEL_2)
@Preview("Pokemon List Content - Pixel 4", device = Devices.PIXEL_4)
@Preview("Pokemon List Content - Nexus 5", device = Devices.NEXUS_5)
@Preview("Pokemon List Content - Nexus 6", device = Devices.NEXUS_6)
@Preview("Pokemon List (big font)", fontScale = 1.5f)
@Preview("Pokemon List (small screen)", widthDp = 320, heightDp = 480)
@Composable
fun PokemonListPreview() {
    PokedexComposeTheme {
        Surface {
            HomeScreenView(
                pokemonAndDetailLazyPagingItems = flowOf(
                    PagingData.from(
                        listPokemonSample
                    )
                ).collectAsLazyPagingItems()
            )
        }
    }
}