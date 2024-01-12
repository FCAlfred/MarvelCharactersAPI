package com.freddev.pokemonapi.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freddev.pokemonapi.databinding.ItemCharBinding
import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.view.MovieActions

class CharactersAdapter(
    private var characterList: List<MarvelCharacter>,
    private val callback: MovieActions
) : RecyclerView.Adapter<CharactersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val binding = ItemCharBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharactersViewHolder(binding)
    }

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.onBindView(characterList[position])
        holder.itemView.setOnClickListener {
            callback.onClickedChar(characterList[position])
        }
    }

    fun updateList(updatedList: List<MarvelCharacter>) {
        characterList = updatedList
        notifyDataSetChanged()
    }
}