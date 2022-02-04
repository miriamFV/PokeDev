package com.example.data.remote

import PokemonModel
import PokemonSpeciesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonSpeciesAPI {

    @GET("pokemon-species/{idPokemon}")
    suspend fun getPokemonSpecies(@Path("idPokemon") id: String):PokemonSpeciesModel

}