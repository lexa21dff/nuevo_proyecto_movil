package com.example.nuevo_proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nuevo_proyecto.Api.PokeApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private EditText name, number;
    private ImageView url;
    private RecyclerView recyclerView ;
    private  ListaPokemonAdapter listaPokemonAdapter;

    Retrofit retrofit;

    private  int offset;

    private static final String TAG = "pokeapi";

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llamado del recyclevie
        recyclerView = findViewById((R.id.card_recycler_view));
        name = findViewById(R.id.nombreTextView);
        number = findViewById(R.id.numberTextView);
        url = findViewById(R.id.fotoImageView);

        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);


        retrofit = new Retrofit.Builder() //crea una nueva instancia de Retrofit.Builder, que se utiliza para construir la instancia final de Retrofit.
                .baseUrl("https://pokeapi.co/api/v2/") // establece la URL base para todas las solicitudes a la AP
                .addConverterFactory(GsonConverterFactory.create()) // agrega un convertidor de fábrica a Retrofit. En este caso, se utiliza GsonConverterFactory para convertir las respuestas de la API en objetos Java utilizando la biblioteca Gson. Esto permite analizar automáticamente las respuestas JSON de la API y convertirlas en objetos utilizables en Java.
                .build();//construye la instancia final de Retrofit con la configuración proporcionada.

        obtenerDatos();
        offset=0;


    }

    private void obtenerDatos() {
        PokeApiService service  = retrofit.create(PokeApiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon();
        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                //bien
                if(response.isSuccessful()){
                    PokemonRespuesta pokemonRespuesta = response.body();
                    List<Pokemon>  listaPokemon = PokemonRespuesta.getResult();
                    for(int i=0;i <listaPokemon.size(); i++ ){
                        Pokemon p = listaPokemon.get(i);
                        Log.e(TAG,"pokemon: " + p.getName());

                    }
                    listaPokemonAdapter.add((ArrayList<Pokemon>) listaPokemon);
                }
                else{
                    Log.e(TAG,"onResponse: " + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                //error
            }
        });
    }
}
