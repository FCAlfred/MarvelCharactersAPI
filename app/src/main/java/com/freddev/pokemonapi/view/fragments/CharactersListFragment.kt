package com.freddev.pokemonapi.view.fragments

import com.freddev.pokemonapi.model.local.LocalCharactersEntity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.freddev.pokemonapi.MainActivity
import com.freddev.pokemonapi.databinding.FragmentCharactersListBinding
import com.freddev.pokemonapi.extra.toLocalCharactersEntity
import com.freddev.pokemonapi.model.local.CharactersDatabase
import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.view.CharacterActions
import com.freddev.pokemonapi.view.adapters.CharactersAdapter
import com.freddev.pokemonapi.viewmodel.MarvelViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersListFragment : Fragment(), CharacterActions {

    private lateinit var charactersAdapter: CharactersAdapter
    private var _binding: FragmentCharactersListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MarvelViewModel by viewModels()
    private lateinit var charactersDatabase: CharactersDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersListBinding.inflate(inflater, container, false)
        charactersDatabase = CharactersDatabase.getInstance(requireContext())
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
            charactersAdapter.updateList(marvelList)
            val selectedCharInfo: List<LocalCharactersEntity> =
                marvelList.map { it.toLocalCharactersEntity() }
            Toast.makeText(
                requireContext(),
                "Lista Nueva:$selectedCharInfo",
                Toast.LENGTH_SHORT
            ).show()
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    charactersDatabase.getCharacterDao().upsertCharacters(selectedCharInfo)
                }
            }
        }
    }
}