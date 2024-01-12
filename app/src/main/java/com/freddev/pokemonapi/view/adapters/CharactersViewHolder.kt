package com.freddev.pokemonapi.view.adapters

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.freddev.pokemonapi.databinding.ItemCharBinding
import com.freddev.pokemonapi.model.network.data.MarvelCharacter

class CharactersViewHolder(private var binding: ItemCharBinding) : ViewHolder(binding.root) {

    fun onBindView(character: MarvelCharacter) {
        binding.textViewTitle.text = character.name
        val url = "${character.thumbnail.path}.${character.thumbnail.extension}"
        val width = 250 // Establecer el ancho deseado en píxeles
        val height = 250 // Establecer la altura deseada en píxeles

        Glide.with(binding.root.context)
            .load(url)
            .override(width, height)
            .into(binding.imageView)
    }
}