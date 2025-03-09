package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import com.example.pokedexcompose.comum.ui.TypeColoursEnum
import com.example.pokedexcompose.list.ui.model.PokemonUi
import com.example.pokedexcompose.list.ui.screen.component.PokemonItem
import org.junit.Rule
import org.junit.Test

class PokemonItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockPokemonUi get() = PokemonUi(
        id = 1,
        idFormatted = "#001",
        name = "Bulbassaur",
        imageUrl = "https://example.com/pokemon2.png",
        types = listOf(TypeColoursEnum.GRASS, TypeColoursEnum.POISON),
        weight = 69,
        height = 7
    )

    @Test
    fun pokemonItemTest_pokemonImage() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Imagem do pokémon Teste")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonId() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("#010")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Teste")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonImageType() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Tipo DRAGON")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_secondPokemonImageType() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Tipo FIGHTING")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_pokemonTypeName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Dragon")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_secondPokemonTypeName() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Fighting")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_imageWeightInKilogram() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Peso em quilograma")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_valueTextKilogram() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("23.8 kg")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_textWeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Altura")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_imageHeightInMeters() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithContentDescription("Altura em metros")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_valueTextHeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("1.3 m")
            .assertExists()
    }

    @Test
    fun pokemonItemTest_textHeight() {
        composeTestRule.setContent {
            PokemonItem(
                pokemonUiData = mockPokemonUi
            )
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("pokemonItemTest")

        composeTestRule
            .onNodeWithText("Altura")
            .assertExists()
    }
}