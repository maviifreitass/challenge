package com.looqbox.service;

import com.looqbox.model.Pokemon;
import java.util.List;

public interface PokemonService {

    List<Pokemon> getByNamePart(String query);

    List<Pokemon> getAllPokemons();
}
