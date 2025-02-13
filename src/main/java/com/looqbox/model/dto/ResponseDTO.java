
package com.looqbox.model.dto;

import com.looqbox.model.Pokemon;
import java.util.List;
import java.util.stream.Collectors;


public class ResponseDTO {

    public static PokemonDTO convertResponse(Boolean isHighlight, List<Pokemon> pokemonResults) {
        if (isHighlight) {
            PokemonDTO<Pokemon> pokemonDTO = new PokemonDTO<>(pokemonResults);
            return pokemonDTO;
        }

        List<String> pokemonNames = pokemonResults.stream()
                .map(Pokemon::getName)
                .collect(Collectors.toList());

        return new PokemonDTO<>(pokemonNames);
    }

}
