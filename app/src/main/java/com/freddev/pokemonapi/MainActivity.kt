package com.freddev.pokemonapi

import com.freddev.pokemonapi.model.network.MarvelRepository
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view)

        MarvelRepository.getMarvelCharacters(
            onSuccess = { character ->
                textView.text = character[0].name
            },
            onError = {
                textView.text = it.toString()
            }
        )


    }
}