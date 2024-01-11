package com.freddev.pokemonapi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freddev.pokemonapi.viewmodel.MarvelViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MarvelViewModel

    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MarvelViewModel::class.java]
        val textView = findViewById<TextView>(R.id.text_view)

        viewModel.charactersList.observe(this, Observer {
            textView.text = it[0].name
        })

    }
}