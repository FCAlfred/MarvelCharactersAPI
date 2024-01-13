package com.freddev.pokemonapi.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.freddev.pokemonapi.databinding.FragmentCharactersListBinding
import com.freddev.pokemonapi.model.local.CharactersDatabase
import com.freddev.pokemonapi.model.local.MarvelCharacterEntity
import com.freddev.pokemonapi.view.CharacterActions
import com.freddev.pokemonapi.view.adapters.CharactersAdapter
import com.freddev.pokemonapi.viewmodel.CharacterViewModelFactory
import com.freddev.pokemonapi.viewmodel.MarvelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersListFragment : Fragment(), CharacterActions {

    private lateinit var charactersAdapter: CharactersAdapter
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MarvelViewModel
    private lateinit var charactersDatabase: CharactersDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        charactersDatabase = CharactersDatabase.getInstance(requireContext())
        viewModel =
            ViewModelProvider(
                this,
                CharacterViewModelFactory(requireContext())
            )[MarvelViewModel::class.java]
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

    override fun onClickedChar(selectedChar: MarvelCharacterEntity) {
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
        viewModel.charactersList.observe(viewLifecycleOwner) { marvelList ->
            if (marvelList.isNotEmpty()) {
                charactersAdapter.updateList(marvelList)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        charactersDatabase.getCharacterDao().upsertCharacters(marvelList)
                    }
                }
            } else {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}