package com.example.pokedexcompose.list.ui.screen.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.pokedexcompose.R
import com.example.pokedexcompose.comum.ui.component.PokemonType
import com.example.pokedexcompose.comum.ui.TypeColoursEnum
import com.example.pokedexcompose.comum.extensions.color
import com.example.pokedexcompose.comum.extensions.titlecase
import com.example.pokedexcompose.comum.extensions.toDoubleFormat
import com.example.pokedexcompose.list.ui.model.PokemonUi
import com.example.pokedexcompose.ui.theme.PokedexComposeTheme

@Composable
fun PokemonItem(
    pokemonUiData: PokemonUi,
    onClickPokemon: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier.clickable { onClickPokemon(pokemonUiData.id) },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 150.dp, max = 180.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        pokemonUiData.types
                            .map { it.codColor.color }
                            .plus(Color.Transparent)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)

            ) {
                Column(Modifier.align(Center)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(pokemonUiData.imageUrl)
                                .crossfade(true)
                                .build(),
                            error = painterResource(
                                id = R.drawable.pokebola
                            ),
                            placeholder = painterResource(id = R.drawable.pokebola)
                        ),
                        contentDescription = stringResource(
                            R.string.pokemon_image_content_description,
                            pokemonUiData.name
                        ),
                        modifier = Modifier
                            .size(130.dp)
                            .align(CenterHorizontally),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = pokemonUiData.idFormatted,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .align(CenterHorizontally)
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = pokemonUiData.name.titlecase,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .padding(top = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .padding(end = 4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 4.dp,
                        alignment = CenterHorizontally
                    )
                ) {
                    pokemonUiData.types.forEach {
                        PokemonType(it)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(CenterHorizontally)
                ) {
                    PokemonMeasure(
                        formattedMeasure = "${
                            pokemonUiData.weight.toDoubleFormat(
                                2
                            )
                        } kg",
                        iconId = R.drawable.weight_kilogram,
                        iconDescription = R.string.weight,
                        iconContentDescription = R.string.pokemon_weight_image_description
                    )
                    PokemonMeasure(
                        formattedMeasure = "${
                            pokemonUiData.height.toDoubleFormat(
                                2
                            )
                        } m",
                        iconId = R.drawable.ruler_square,
                        iconDescription = R.string.height,
                        iconContentDescription = R.string.pokemon_height_image_description
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PokemonItemPreview() {
    PokedexComposeTheme {
        Surface {
            PokemonItem(
                PokemonUi(
                    id = 1,
                    name = "Bulbasaur",
                    weight = 123,
                    height = 123,
                    imageUrl = "",
                    types = listOf(
                        TypeColoursEnum.GRASS,
                        TypeColoursEnum.FIGHTING
                    ),
                    idFormatted = "#0000",
                )
            )
        }
    }
}
