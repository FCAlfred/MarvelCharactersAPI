package com.freddev.pokemonapi.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.freddev.pokemonapi.databinding.FragmentCharactersListBinding
import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.view.CharacterActions
import com.freddev.pokemonapi.view.adapters.CharactersAdapter
import com.freddev.pokemonapi.viewmodel.MarvelViewModel

class CharactersListFragment : Fragment(), CharacterActions {

    private lateinit var charactersAdapter: CharactersAdapter
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarvelViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUiElements()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickedChar(selectedChar: MarvelCharacter) {
        Toast.makeText(requireContext(), selectedChar.name, Toast.LENGTH_SHORT).show()
        val action =
            CharactersListFragmentDirections.actionCharactersListFragmentToCharacterDetailsFragment(
                selectedChar.id
            )
        findNavController().navigate(action)
    }

    private fun setUiElements() {
        charactersAdapter = CharactersAdapter(emptyList(), this)
        binding.recyclerHeroes.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerHeroes.adapter = charactersAdapter
        viewModel.charactersList.observe(viewLifecycleOwner) {
            charactersAdapter.updateList(it)
        }
    }
}