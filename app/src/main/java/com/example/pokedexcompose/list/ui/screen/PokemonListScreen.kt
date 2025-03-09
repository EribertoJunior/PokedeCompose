package com.example.pokedexcompose.list.ui.screen

import android.content.res.Configuration
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
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.pokedexcompose.list.ui.samples.pokemonSample
import com.example.pokedexcompose.comum.ui.component.LoadingIndicator
import com.example.pokedexcompose.list.ui.screen.component.PokemonItem
import com.example.pokedexcompose.list.ui.model.PokemonUi
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import com.example.pokedexcompose.list.viewmodel.PokemonListViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel, onClickPokemon: (Int) -> Unit = {}) {
    PokemonListScreen(
        pokemonAndDetailLazyPagingItems = viewModel.uiState.collectAsLazyPagingItems(),
        onClickPokemon
    )
}

@Composable
fun PokemonListScreen(
    pokemonAndDetailLazyPagingItems: LazyPagingItems<PokemonUi>,
    onClickPokemon: (Int) -> Unit = {}
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
                key = pokemonAndDetailLazyPagingItems.itemKey { it.id }
            ) { index ->
                pokemonAndDetailLazyPagingItems[index]?.let { pokemonAndDetail ->
                    PokemonItem(
                        pokemonUiData = pokemonAndDetail,
                        onClickPokemon = onClickPokemon
                    )
                }
            }
        }

        LoadingIndicator(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 8.dp, end = 8.dp),
            pokemonAndDetailLazyPagingItems
        )
    }
}

@Preview("Pixel 4", device = Devices.PIXEL_4)
@Preview("Pixel 4 - Dark", device = Devices.PIXEL_4, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PokemonListPreview() {
    PokedexComposeTheme {
        Surface {
            PokemonListScreen(
                pokemonAndDetailLazyPagingItems = flowOf(
                    PagingData.from(
                        pokemonSample
                    )
                ).collectAsLazyPagingItems()
            )
        }
    }
}
