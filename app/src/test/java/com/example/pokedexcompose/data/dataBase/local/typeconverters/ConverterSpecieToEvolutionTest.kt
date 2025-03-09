package com.example.pokedexcompose.data.dataBase.local.typeconverters

import com.example.pokedexcompose.details.data.room.typeconverters.ConverterSpecieToEvolution
import com.example.pokedexcompose.list.data.room.entities.SpecieToEvolution
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Test

class ConverterSpecieToEvolutionTest {

    private var converterSpecieToEvolution = spyk(ConverterSpecieToEvolution())

    @Test
    fun `should return a jsonArray when a SpecieToEvolution list is passed`() {

        val jsonArray = "[{\"name\":\"name\",\"imageUrl\":\"/321\"}]"
        val listOf = listOf(
            SpecieToEvolution(name = "name", pokemonId = 321)
        )

        run {
            val listString = converterSpecieToEvolution.saveListToListString(listOf)
            assertEquals(jsonArray, listString)
        }
    }

    @Test
    fun `should return a list of SpecieToEvolution when a jsonArray is passed`() {

        val jsonArray = "[{\"name\":\"name\",\"imageUrl\":\"/321\"}]"

        val listOf = listOf(
            SpecieToEvolution(name = "name", pokemonId = 321)
        )

        run {
            val list = converterSpecieToEvolution.restoreList(jsonArray)
            assertEquals(listOf, list)
        }
    }
}