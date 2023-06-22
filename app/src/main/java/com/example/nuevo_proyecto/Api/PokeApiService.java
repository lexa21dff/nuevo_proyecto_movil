package com.example.nuevo_proyecto.Api;

import com.example.nuevo_proyecto.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiService {

    @GET("pokemon")
    Call <PokemonRespuesta> obtenerListaPokemon();
}
