package com.example.pokedexcompose.data.dataBase.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokedexcompose.data.dataBase.local.dao.EvolutionChainDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonDetailDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonRemoteKeyDao
import com.example.pokedexcompose.data.dataBase.local.dao.PokemonSpeciesDao
import com.example.pokedexcompose.data.dataBase.local.typeconverters.ConverterPokemonDetailStats
import com.example.pokedexcompose.data.dataBase.local.typeconverters.ConverterSpecieToEvolution
import com.example.pokedexcompose.data.dataBase.local.typeconverters.ConverterTypeColoursEnum
import com.example.pokedexcompose.data.dataBase.local.typeconverters.ListStringConverter
import com.example.pokedexcompose.data.dataBase.local.entities.EvolutionChainEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonEntity
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonDetail
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonRemoteKey
import com.example.pokedexcompose.data.dataBase.local.entities.PokemonSpecies

@Database(
    entities = [PokemonEntity::class, EvolutionChainEntity::class, PokemonDetail::class, PokemonRemoteKey::class, PokemonSpecies::class],
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