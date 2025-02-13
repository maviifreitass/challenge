package com.looqbox.model.dto;

import com.looqbox.model.Pokemon;
import java.util.List;
import java.util.stream.Collectors;

public class PokemonDTO {

    private final List<String> result;

    public PokemonDTO(List<Pokemon> pokemonResults) {
        this.result = pokemonResults.stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList());
    }

    public List<String> getResult() {
        return result;
    }

}
