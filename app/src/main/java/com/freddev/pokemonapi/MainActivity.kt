package com.freddev.pokemonapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.freddev.pokemonapi.databinding.ActivityMainBinding
import com.freddev.pokemonapi.viewmodel.MarvelViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MarvelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MarvelViewModel::class.java]
        setContentView(binding.root)
    }
}