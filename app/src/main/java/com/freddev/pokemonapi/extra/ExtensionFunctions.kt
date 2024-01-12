package com.freddev.pokemonapi.extra

import com.freddev.pokemonapi.model.local.LocalCharactersEntity
import com.freddev.pokemonapi.model.network.data.MarvelCharacter

fun MarvelCharacter.toLocalCharactersEntity(): LocalCharactersEntity {
    return LocalCharactersEntity(
        id = id,
        description = description,
        name = name,
        thumbnailPath = thumbnail.path,
        thumbnailExtension = thumbnail.extension
    )
}