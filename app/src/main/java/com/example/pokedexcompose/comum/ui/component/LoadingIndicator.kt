package com.example.pokedexcompose.comum.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier, pokemonAndDetailLazyPagingItems: LazyPagingItems<*>) {
    Box(
        modifier = modifier
    ) {
        val isLoading = listOf(
            pokemonAndDetailLazyPagingItems.loadState.refresh,
            pokemonAndDetailLazyPagingItems.loadState.append
        ).any { it is LoadState.Loading }

        if (isLoading) {
            //LoadingAnimation()
            LottieLoadingAnimation()

        }
    }
}