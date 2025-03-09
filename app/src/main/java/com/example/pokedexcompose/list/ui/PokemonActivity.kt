package com.example.pokedexcompose.list.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.pokedexcompose.details.ui.DetailsActivity
import com.example.pokedexcompose.details.ui.DetailsActivity.Companion.DETAILS_ACTIVITY_POKEMON_ID
import com.example.pokedexcompose.list.ui.screen.PokemonListScreen
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexComposeTheme {
                App(
                    content = {
                        PokemonListScreen(
                            viewModel = getViewModel(),
                            onClickPokemon = { pokemonId ->
                                startActivity(
                                    Intent(this, DetailsActivity::class.java)
                                        .run { putExtra(DETAILS_ACTIVITY_POKEMON_ID, pokemonId) })
                            })
                    }
                )
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit = {}) {
    PokedexComposeTheme {
        Surface {
            content()
        }
    }
}