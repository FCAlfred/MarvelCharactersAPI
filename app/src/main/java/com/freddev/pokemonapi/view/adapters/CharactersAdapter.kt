package com.freddev.pokemonapi.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.freddev.pokemonapi.databinding.ItemCharBinding
import com.freddev.pokemonapi.model.local.MarvelCharacterEntity
import com.freddev.pokemonapi.view.CharacterActions

class CharactersAdapter(
    private var characterList: List<MarvelCharacterEntity>,
    private val callback: CharacterActions
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

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList: List<MarvelCharacterEntity>) {
        characterList = updatedList
        notifyDataSetChanged()
    }
}