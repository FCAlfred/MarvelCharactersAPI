package com.freddev.pokemonapi.model.network

import com.freddev.pokemonapi.model.network.data.MarvelCharacter
import com.freddev.pokemonapi.model.network.data.MarvelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest

object MarvelRepository {
    private var marvelApi: MarvelApi
    private const val apiKey = "fff6bd71711838411fc16b29a57802e0"
    private const val privateKey = "938c793dbb67d3c77d1cdddc5d43caa35b4ef671"
    private val timestamp = System.currentTimeMillis().toString()
    private val hash = getMarvelHash(timestamp, privateKey, apiKey)

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gateway.marvel.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        marvelApi = retrofit.create(MarvelApi::class.java)
    }

    fun getMarvelCharacters(
        onSuccess: (List<MarvelCharacter>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        marvelApi.getCharacters(apiKey, timestamp, hash)
            .enqueue(object : Callback<MarvelResponse<MarvelCharacter>> {
                override fun onResponse(
                    call: Call<MarvelResponse<MarvelCharacter>>,
                    response: Response<MarvelResponse<MarvelCharacter>>
                ) {
                    if (response.isSuccessful) {
                        val characters = response.body()?.data?.results
                        if (characters != null) {
                            onSuccess.invoke(characters)
                        } else {
                            onError.invoke(Throwable("No se pudo obtener la lista de personajes - lista vacía"))
                        }
                    } else {
                        println("Código de estado no exitoso: ${response.code()}")
                        onError.invoke(Throwable("Error en la respuesta de la API"))
                    }
                }

                override fun onFailure(call: Call<MarvelResponse<MarvelCharacter>>, t: Throwable) {
                    onError.invoke(t)
                }
            })
    }


    // Método para calcular el hash requerido por la API de Marvel
    private fun getMarvelHash(timestamp: String, privateKey: String, publicKey: String): String {
        val input = "$timestamp$privateKey$publicKey"
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}
