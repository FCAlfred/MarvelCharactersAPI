package com.freddev.pokemonapi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.freddev.pokemonapi.model.network.MarvelRepository
import com.freddev.pokemonapi.model.network.data.MarvelCharacter

class MarvelViewModel : ViewModel() {

    private var _charactersList = MutableLiveData<List<MarvelCharacter>>()
    val charactersList: LiveData<List<MarvelCharacter>> get() = _charactersList

    init {
        getCharacters()
    }

    private fun getCharacters() {
        MarvelRepository.getMarvelCharacters(
            onSuccess = { characters ->
                _charactersList.postValue(characters)
            },
            onError = {
                //LLamar a ROOM DB
                Log.i("NETWORK ERROR", it.toString())
            }
        )
    }
}
