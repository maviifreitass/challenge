package com.looqbox.model.dto;

import java.util.List;

public class PokemonDTO<T> {

    private final List<T> result;

    public PokemonDTO(List<T> pokemonResults) {
        this.result = pokemonResults;
    }

    public List<T> getResult() {
        return result;
    } 

}
