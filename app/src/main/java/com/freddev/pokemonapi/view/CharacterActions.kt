package com.freddev.pokemonapi.view

import com.freddev.pokemonapi.model.local.MarvelCharacterEntity

interface CharacterActions {
    fun onClickedChar(selectedChar: MarvelCharacterEntity)
}