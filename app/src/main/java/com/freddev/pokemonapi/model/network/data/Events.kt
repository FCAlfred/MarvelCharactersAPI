package com.freddev.pokemonapi.model.network.data


data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)