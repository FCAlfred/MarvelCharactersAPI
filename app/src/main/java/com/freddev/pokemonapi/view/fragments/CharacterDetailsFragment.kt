package com.freddev.pokemonapi.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.freddev.pokemonapi.databinding.FragmentCharacterDetailsBinding
import com.freddev.pokemonapi.model.local.CharactersDatabase
import com.freddev.pokemonapi.model.local.MarvelCharacterEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailsFragmentArgs by navArgs()

    private lateinit var charactersDatabase: CharactersDatabase
    private lateinit var selectedCharacter: MarvelCharacterEntity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        charactersDatabase = CharactersDatabase.getInstance(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                selectedCharacter =
                    charactersDatabase.getCharacterDao().getSpecificCharacter(args.charId)
                setComponents()
            }
        }
    }

    private fun setComponents() {
        binding.apply {
            textView.text = selectedCharacter.name
        }
    }

}