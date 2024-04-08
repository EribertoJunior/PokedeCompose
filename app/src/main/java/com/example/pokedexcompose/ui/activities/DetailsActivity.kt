package com.example.pokedexcompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.pokedexcompose.ui.screens.DetailsScreen
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import com.example.pokedexcompose.ui.viewmodels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : ComponentActivity() {

    private val viewModel: DetailsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                Surface {
                    val stringExtra = intent.getStringExtra(DETAILS_ACTIVITY_POKEMON_NAME).orEmpty()
                    //Toast.makeText(this, stringExtra, Toast.LENGTH_SHORT).show()

                    DetailsScreen(stringExtra, getViewModel())
                }
            }
        }
    }

    companion object {
        const val DETAILS_ACTIVITY_POKEMON_NAME = "pokemonName"
    }
}