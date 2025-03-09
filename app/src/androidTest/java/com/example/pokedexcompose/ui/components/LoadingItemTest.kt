package com.example.pokedexcompose.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.pokedexcompose.comum.ui.component.LoadingItem
import org.junit.Rule
import org.junit.Test

class LoadingItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingItemTest() {
        composeTestRule.setContent {
            LoadingItem()
        }

        composeTestRule
            .onNodeWithContentDescription("Loading Pokemons")
    }
}