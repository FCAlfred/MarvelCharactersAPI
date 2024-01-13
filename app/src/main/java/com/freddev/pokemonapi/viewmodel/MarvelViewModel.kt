package com.freddev.pokemonapi.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freddev.pokemonapi.model.local.CharactersDatabase
import com.freddev.pokemonapi.model.network.MarvelRepository
import com.freddev.pokemonapi.model.local.MarvelCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarvelViewModel(context: Context) : ViewModel() {

    private var _charactersList = MutableLiveData<List<MarvelCharacterEntity>>()
    val charactersList: LiveData<List<MarvelCharacterEntity>> get() = _charactersList

    init {
        validateROOMData(context)
    }

    private fun validateROOMData(context: Context) {
        val localDb = CharactersDatabase.getInstance(context)
        var data: List<MarvelCharacterEntity>
        viewModelScope.launch(Dispatchers.IO) {
            data = localDb.getCharacterDao().getCharacters()
            if (data.isEmpty()) {
                getCharacters(localDb)
            } else {
                _charactersList.postValue(data)
            }
        }
    }

    private fun getCharacters(localDb: CharactersDatabase) {
        val emptyList: List<MarvelCharacterEntity> = emptyList()
        MarvelRepository.getMarvelCharacters(
            onSuccess = { characters ->
                _charactersList.postValue(characters)
                viewModelScope.launch(Dispatchers.IO) {
                    localDb.getCharacterDao().upsertCharacters(characters)
                }
            },
            onError = {
                Log.i("NETWORK ERROR", it.toString())
                _charactersList.postValue(emptyList)
            }
        )
    }
}
///TODO #1: Diseñar xml para detalles del personaje por medio de un custom Component y mejorar el diseño del item usado en el adapter del recycler

///TODO #2: Mejorar la forma en mostrar al usuario que no hay conexión, a la par de implementar shimerSkeleton

///TODO #3: Implementar inyección de dependencias con Hilt-Dagger
