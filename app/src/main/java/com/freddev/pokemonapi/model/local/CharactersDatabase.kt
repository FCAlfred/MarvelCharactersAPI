package com.freddev.pokemonapi.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalCharactersEntity::class], version = 2)
abstract class CharactersDatabase : RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDatabase? = null

        fun getInstance(context: Context): CharactersDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    "characters_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}