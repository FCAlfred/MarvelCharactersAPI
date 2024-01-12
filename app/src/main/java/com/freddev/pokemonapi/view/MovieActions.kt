package com.freddev.pokemonapi.view

import com.freddev.pokemonapi.model.network.data.MarvelCharacter

interface MovieActions {
    fun onClickedChar(selectedChar: MarvelCharacter)
}