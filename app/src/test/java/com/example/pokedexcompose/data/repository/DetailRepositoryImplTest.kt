package com.example.pokedexcompose.data.repository

import com.example.pokedexcompose.comum.ui.TypeColoursEnum
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.details.data.dataSource.local.PokemonDetailLocalDataSource
import com.example.pokedexcompose.details.data.repository.DetailRepositoryImpl
import com.example.pokedexcompose.details.data.room.entities.Home
import com.example.pokedexcompose.details.data.room.entities.OfficialArtwork
import com.example.pokedexcompose.details.data.room.entities.Other
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.details.data.room.entities.PokemonDetailSpecies
import com.example.pokedexcompose.details.data.room.entities.Sprites
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.ui.samples.pokemonSample
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailRepositoryImplTest {

    private lateinit var pokemonDetailLocalDataSource: PokemonDetailLocalDataSource
    private lateinit var detailRepositoryImpl: DetailRepositoryImpl
    val pokemonAndDetail = PokemonAndDetail(
        pokemonEntity = PokemonEntity(
            id = 10,
            name = "Teste",
            imageUrl = ""
        ),
        pokemonDetail = PokemonDetail(
            colorTypeList = listOf(
                TypeColoursEnum.DRAGON,
                TypeColoursEnum.FIGHTING
            ),
            sprites = Sprites(
                Other(
                    officialArtwork = OfficialArtwork(""),
                    home = Home("")
                )
            ),
            weight = 238,
            height = 13,
            stats = emptyList(),
            species = PokemonDetailSpecies(
                name = "",
                url = ""
            )
        )
    )

    @Before
    fun setUp() {
        pokemonDetailLocalDataSource = mockk()
        detailRepositoryImpl = spyk(DetailRepositoryImpl(pokemonDetailLocalDataSource))
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return a PokemonAndDetail when localDataSource returns a PokemonAndDetail`() {

        every { pokemonDetailLocalDataSource.searchPokemonById(any()) } answers {
            flow {
                emit(pokemonAndDetail)
            }
        }

        runBlocking {
            val searchPokemonByName = detailRepositoryImpl.searchPokemonById(0)

            assertEquals(pokemonAndDetail, searchPokemonByName.first())
        }
    }

    @Test
    fun `should return NoSuchElementException when a PokemonAndDetail from localDataSource is not returned`() {
        every { pokemonDetailLocalDataSource.searchPokemonById(any()) } answers {
            flow {}
        }
        assertThrows(
            "Expected at least one element",
            NoSuchElementException::class.java
        ) {
            runBlocking {
                val searchPokemonByName = detailRepositoryImpl.searchPokemonById(0)
                searchPokemonByName.first()
            }
        }
    }

}