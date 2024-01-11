package com.freddev.pokemonapi.model.network

import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.model.network.data.MarvelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters")
    fun getCharacters(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Call<MarvelResponse<MarvelCharacter>>
}
