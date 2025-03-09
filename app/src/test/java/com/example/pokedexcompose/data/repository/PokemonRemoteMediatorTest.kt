package com.example.pokedexcompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokedexcompose.details.data.dataSource.local.PokemonDetailLocalDataSourceImpl
import com.example.pokedexcompose.details.data.dataSource.remote.PokemonDetailRemoteDataSourceImpl
import com.example.pokedexcompose.data.model.local.PokemonAndDetail
import com.example.pokedexcompose.data.model.remote.ListPokemonRemote
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.list.data.PokemonRemoteMediator
import com.example.pokedexcompose.list.data.dataSource.local.PokemonListLocalDataSource
import com.example.pokedexcompose.list.data.dataSource.remote.PokemonListRemoteDataSource
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

@ExperimentalPagingApi
internal class PokemonRemoteMediatorTest {

    private val localDataSourceMock = mockk<PokemonListLocalDataSource>()
    private val remoteDataSourceMock = mockk<PokemonListRemoteDataSource>()
    private var response = mockk<ListPokemonRemote>()
    private val pokemonDetailMock = mockk<PokemonDetailRemote>(relaxed = true)
    private val remoteMediatorSpy = spyk(
        PokemonRemoteMediator(
            pokemonListLocalDataSource = localDataSourceMock,
            pokemonListRemoteDataSource = remoteDataSourceMock
        )
    )

    @After
    fun after() {
        response = mockk()
    }

    @Test
    fun `refresh Load Returns SuccessResult When More Data Is Present`() {

        coEvery { response.results } answers { mockk(relaxed = true) }
        coEvery { response.previous } answers { null }
        coEvery { response.next } answers { "https://pokeapi.co/api/v2/pokemon?offset=10&limit=10" }
        coEvery { remoteMediatorSpy.getOffsetParameter(any()) } returns 1

        coEvery {
            remoteDataSourceMock.getListPokemon(
                limit = 100,
                offset = 0
            )
        } answers { response }
        coEvery { remoteDataSourceMock.getPokemonDetails(any()) } answers { pokemonDetailMock }

        coEvery { localDataSourceMock.saveAllRemoteKey(any()) } answers {}

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached.not())
    }

    @Test
    fun `refresh Load Success And End OfPagination When No More Data`() {

        coEvery { response.results } answers { listOf() }
        coEvery { response.previous } answers { null }
        coEvery { response.next } answers { null }
        coEvery { remoteMediatorSpy.getOffsetParameter(any()) } returns null

        coEvery {
            remoteDataSourceMock.getListPokemon(
                limit = 100,
                offset = 0
            )
        } answers { response }

        coEvery { localDataSourceMock.saveAllRemoteKey(any()) } answers {}

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun `refresh Load Returns Error Result When Error Occurs`() {

        coEvery { remoteDataSourceMock.getListPokemon(limit = 100, offset = 0) }.throws(
            RuntimeException()
        )

        val result = runBlocking {
            remoteMediatorSpy.load(LoadType.REFRESH, getPagingState())
        }

        assert(result is RemoteMediator.MediatorResult.Error)
    }

    private fun getPagingState() = PagingState<Int, PokemonEntity>(
        pages = listOf(),
        anchorPosition = null,
        config = PagingConfig(10),
        leadingPlaceholderCount = 10
    )
}