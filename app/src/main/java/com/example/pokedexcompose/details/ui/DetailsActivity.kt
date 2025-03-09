package com.example.pokedexcompose.details.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.pokedexcompose.details.ui.screens.DetailsScreen
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import com.example.pokedexcompose.details.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                Surface {
                    val pokemonId = intent.getIntExtra(DETAILS_ACTIVITY_POKEMON_ID, 0)
                    DetailsScreen(pokemonId, getViewModel())
                }
            }
        }
    }

    companion object {
        const val DETAILS_ACTIVITY_POKEMON_ID = "pokemonId"
    }
}