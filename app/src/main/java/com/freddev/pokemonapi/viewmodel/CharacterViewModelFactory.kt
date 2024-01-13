package com.freddev.pokemonapi.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CharacterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MarvelViewModel::class.java)) {
            return MarvelViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
