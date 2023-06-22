package com.example.nuevo_proyecto;

import java.util.ArrayList;
import java.util.List;

public class PokemonRespuesta {
    private static List<Pokemon> result = new ArrayList<Pokemon> () ;
    public static List<Pokemon> getResult() {
        return result;
    }

    public void setResult(List<Pokemon> result) {
        this.result = result;
    }

}
