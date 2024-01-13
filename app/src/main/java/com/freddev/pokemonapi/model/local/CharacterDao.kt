package com.freddev.pokemonapi.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {
    @Query("SELECT * FROM heroes")
    suspend fun getCharacters(): List<MarvelCharacterEntity>

    @Query("SELECT * FROM heroes WHERE id=:charId")
    suspend fun getSpecificCharacter(charId: Int): MarvelCharacterEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCharacters(chars: List<MarvelCharacterEntity>)
}