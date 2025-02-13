package com.looqbox.service;

import com.looqbox.enums.SortingCriteria;
import com.looqbox.model.Pokemon;
import java.util.List;

public interface PokemonService {

    List<Pokemon> getByNamePart(String query, SortingCriteria sort);

    List<Pokemon> getAllPokemons(SortingCriteria sort);
    
    List<Pokemon> getAllHighlight(List<Pokemon> listPokemon, String query);
}
