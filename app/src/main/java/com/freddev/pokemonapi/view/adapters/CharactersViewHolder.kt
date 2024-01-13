package com.freddev.pokemonapi.view.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.freddev.pokemonapi.databinding.ItemCharBinding
import com.freddev.pokemonapi.model.local.MarvelCharacterEntity

class CharactersViewHolder(private var binding: ItemCharBinding) : ViewHolder(binding.root) {

    fun onBindView(character: MarvelCharacterEntity) {
        binding.customItem.apply {
            setTitleName(character.name)
            setDescription(character.description)
            setImage(character.thumbnail.path + "." + character.thumbnail.extension)
        }
    }
}