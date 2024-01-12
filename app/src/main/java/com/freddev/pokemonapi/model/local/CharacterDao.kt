package com.freddev.pokemonapi.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM heroes")
    suspend fun getCharacters(): List<LocalCharactersEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCharacters(chars: List<LocalCharactersEntity>)
}