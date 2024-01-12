package com.freddev.pokemonapi.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.freddev.pokemonapi.R
import com.freddev.pokemonapi.databinding.FragmentCharacterDetailsBinding
import com.freddev.pokemonapi.databinding.FragmentCharactersListBinding
import com.freddev.pokemonapi.model.local.CharactersDatabase
import com.freddev.pokemonapi.viewmodel.MarvelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailsFragmentArgs by navArgs()
    private val viewModel: MarvelViewModel by viewModels()
    private lateinit var charactersDatabase: CharactersDatabase

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
        Toast.makeText(requireContext(), "${args.charId}", Toast.LENGTH_SHORT).show()
        var id = ""
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                id = charactersDatabase.getCharacterDao().getCharacters()[0].name
                binding.textView.text = id
            }
        }
    }

}