package com.freddev.pokemonapi.view

import com.freddev.pokemonapi.model.network.data.MarvelCharacter

interface CharacterActions {
    fun onClickedChar(selectedChar: MarvelCharacter)
}