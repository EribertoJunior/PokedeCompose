package com.example.pokedexcompose.list.data

import android.net.Uri
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokedexcompose.comum.extensions.getUrlId
import com.example.pokedexcompose.data.model.remote.PokemonDetailRemote
import com.example.pokedexcompose.list.data.dataSource.local.PokemonListLocalDataSource
import com.example.pokedexcompose.list.data.dataSource.remote.PokemonListRemoteDataSource
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val pokemonListLocalDataSource: PokemonListLocalDataSource,
    private val pokemonListRemoteDataSource: PokemonListRemoteDataSource,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : RemoteMediator<Int, PokemonEntity>() {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val offset = getOffset(loadType, state)

            if (offset == null) {
                return MediatorResult.Success(endOfPaginationReached = false)
            }

            withContext(dispatcher) {
                val response = pokemonListRemoteDataSource.getListPokemon(
                    limit = state.config.pageSize,
                    offset = offset
                )
                val pokemonList = response.results
                val endOfPaginationReached = pokemonList.isEmpty()

                if (endOfPaginationReached.not()) {
                    val pokemonEntities = pokemonList.map {
                        PokemonEntity(
                            id = it.url.getUrlId,
                        )
                    }

                    val pokemonDetails = pokemonEntities.map { pokemonEntity ->
                        async {
                            findDetailsPokemon(pokemonEntity.id) to pokemonEntity
                        }
                    }.awaitAll()

                    val listPokemonRemoteKeyEntity = pokemonDetails.map { (_, pokemonEntity) ->
                        PokemonRemoteKeyEntity(
                            id = pokemonEntity.id,
                            prevOffset = getOffsetParameter(response.previous),
                            nextOffset = getOffsetParameter(response.next)
                        )
                    }
                    val listPokemonEntity =
                        pokemonDetails.map { (pokemonDetailsRemote, pokemonEntity) ->
                            updatePokemonEntity(pokemonEntity, pokemonDetailsRemote)
                            pokemonEntity
                        }

                    pokemonListLocalDataSource.saveAllRemoteKey(listPokemonRemoteKeyEntity)
                    pokemonListLocalDataSource.saveAllPokemon(listPokemonEntity)
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getOffset(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): Int? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getClosestRemoteKeyToCurrentPosition(state)
                remoteKey?.nextOffset?.minus(OFFSET) ?: 0
            }

            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                remoteKey?.prevOffset ?: return null
            }

            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                remoteKey?.nextOffset ?: return null
            }
        }
    }

    private suspend fun findDetailsPokemon(pokemonId: Int): PokemonDetailRemote {
        return pokemonListRemoteDataSource.getPokemonDetails(pokemonId)
    }

    private fun updatePokemonEntity(
        pokemonEntity: PokemonEntity,
        pokemonDetailsRemote: PokemonDetailRemote
    ) {
        pokemonEntity.name = pokemonDetailsRemote.name
        //pokemonEntity.imageUrl = pokemonDetailsRemote.sprites.other.officialArtwork.frontDefault
        pokemonEntity.weight = pokemonDetailsRemote.weight
        pokemonEntity.height = pokemonDetailsRemote.height
        pokemonEntity.types = pokemonDetailsRemote.types.map { it.type.name }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PokemonEntity>): PokemonRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { pokemonAndDetail ->
                pokemonListLocalDataSource.getPokemonRemoteKeyById(pokemonAndDetail.id)
            }
    }

    private suspend fun getClosestRemoteKeyToCurrentPosition(state: PagingState<Int, PokemonEntity>): PokemonRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { pokemonId ->
                pokemonListLocalDataSource.getPokemonRemoteKeyById(pokemonId)
            }
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PokemonEntity>): PokemonRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { pokemonAndDetail ->
                pokemonListLocalDataSource.getPokemonRemoteKeyById(pokemonAndDetail.id)
            }
    }

    fun getOffsetParameter(url: String?): Int? {
        return url?.let {
            Uri.parse(it).getQueryParameter("offset")?.toIntOrNull()
        }
    }

    companion object {
        const val OFFSET = 100
    }
}