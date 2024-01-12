package com.freddev.pokemonapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.freddev.pokemonapi.databinding.ActivityMainBinding
import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.view.MovieActions
import com.freddev.pokemonapi.view.adapters.CharactersAdapter
import com.freddev.pokemonapi.viewmodel.MarvelViewModel

class MainActivity : AppCompatActivity()/*, MovieActions*/ {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    //    private lateinit var charactersAdapter: CharactersAdapter
    private lateinit var viewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MarvelViewModel::class.java]
        setContentView(binding.root)
        /*        charactersAdapter = CharactersAdapter(emptyList(), this)
                binding.recyclerHeroes.layoutManager = GridLayoutManager(this, 3)
                binding.recyclerHeroes.adapter = charactersAdapter

                viewModel.charactersList.observe(this) { marvelList ->
                    charactersAdapter.updateList(marvelList)
                    Toast.makeText(this, "elements:${marvelList.size}", Toast.LENGTH_SHORT).show()
                }*/
    }

/*    override fun onClickedChar(selectedChar: MarvelCharacter) {
        Toast.makeText(this, "Clicked ${selectedChar.name}", Toast.LENGTH_SHORT).show()
    }*/
}