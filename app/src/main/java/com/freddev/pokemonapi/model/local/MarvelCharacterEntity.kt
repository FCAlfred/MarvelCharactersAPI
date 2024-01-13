package com.freddev.pokemonapi.model.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.freddev.pokemonapi.model.network.data.Stories
import com.freddev.pokemonapi.model.network.data.Thumbnail

@Entity(tableName = "heroes")
data class MarvelCharacterEntity(
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val modified: String,
    val name: String,
    val resourceURI: String,
    @Embedded(prefix = "thumbnail_")
    val thumbnail: Thumbnail,
    @Embedded(prefix = "stories_")
    val stories: Stories
)
