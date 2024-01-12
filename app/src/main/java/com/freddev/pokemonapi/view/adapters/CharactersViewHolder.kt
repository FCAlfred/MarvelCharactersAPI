package com.freddev.pokemonapi.view.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.freddev.pokemonapi.databinding.ItemCharBinding
import com.freddev.pokemonapi.model.network.data.MarvelCharacter

class CharactersViewHolder(private var binding: ItemCharBinding) : ViewHolder(binding.root) {

    fun onBindView(character: MarvelCharacter) {
        binding.textViewTitle.text = character.name
    }
}