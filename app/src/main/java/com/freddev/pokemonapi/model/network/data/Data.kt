package com.freddev.pokemonapi.model.network.data

import com.freddev.pokemonapi.model.local.MarvelCharacterEntity


data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MarvelCharacterEntity>,
    val total: Int
)