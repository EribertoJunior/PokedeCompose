package com.example.pokedexcompose.comum.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexcompose.comum.data.local.typeconverters.ListStringConverter
import com.example.pokedexcompose.details.data.room.dao.EvolutionChainDao
import com.example.pokedexcompose.details.data.room.dao.PokemonDetailDao
import com.example.pokedexcompose.details.data.room.dao.PokemonSpeciesDao
import com.example.pokedexcompose.details.data.room.entities.EvolutionChainEntity
import com.example.pokedexcompose.details.data.room.entities.PokemonDetail
import com.example.pokedexcompose.details.data.room.entities.PokemonSpecies
import com.example.pokedexcompose.details.data.room.typeconverters.ConverterPokemonDetailStats
import com.example.pokedexcompose.details.data.room.typeconverters.ConverterSpecieToEvolution
import com.example.pokedexcompose.details.data.room.typeconverters.ConverterTypeColoursEnum
import com.example.pokedexcompose.list.data.room.dao.PokemonDao
import com.example.pokedexcompose.list.data.room.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.list.data.room.entities.PokemonEntity
import com.example.pokedexcompose.list.data.room.entities.PokemonRemoteKeyEntity

@Database(
    entities = [PokemonEntity::class, EvolutionChainEntity::class, PokemonDetail::class, PokemonRemoteKeyEntity::class, PokemonSpecies::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    ConverterTypeColoursEnum::class,
    ConverterPokemonDetailStats::class,
    ConverterSpecieToEvolution::class,
    ListStringConverter::class
)
abstract class RoomConfig : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun pokemonRemoteKeyDao(): PokemonRemoteKeyDao
    abstract fun pokemonDetailDao(): PokemonDetailDao
    abstract fun pokemonSpeciesDao(): PokemonSpeciesDao
    abstract fun pokemonEvolutionChainDao(): EvolutionChainDao

    companion object {
        @Volatile
        private var INSTANCE: RoomConfig? = null

        fun getDataBase(context: Context): RoomConfig {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomConfig::class.java,
                    "pokemon_database"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}