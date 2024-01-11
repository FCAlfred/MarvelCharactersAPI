package com.freddev.pokemonapi.model.network.data


data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<MarvelCharacter>,
    val total: Int
)