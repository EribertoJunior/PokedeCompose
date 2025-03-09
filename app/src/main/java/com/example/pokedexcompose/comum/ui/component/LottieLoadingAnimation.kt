package com.example.pokedexcompose.comum.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pokedexcompose.R
import com.airbnb.lottie.compose.animateLottieCompositionAsState

@Composable
fun LottieLoadingAnimation(modifier: Modifier = Modifier) {
    // Cria um 'remember' para a composição Lottie
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pikachu_loading)) // Substitua R.raw.pokemon_loading pelo seu arquivo JSON

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // Exibe a animação com LottieAnimation
    LottieAnimation(
        composition = composition,
        modifier = modifier.size(150.dp), // Ajuste o tamanho conforme necessário
        progress = { progress }
    )
}