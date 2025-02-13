package com.looqbox.service.impl;

import com.looqbox.cache.PokemonCache;
import com.looqbox.enums.SortingCriteria;
import com.looqbox.model.Pokemon;
import com.looqbox.service.PokemonService;
import com.looqbox.util.QuickSort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonCache cache;

    public PokemonServiceImpl(Pokemon pokemon, PokemonCache cache) {
        this.cache = cache; 
    }

    private List<Pokemon> sortPokemons(List<Pokemon> pokemons, SortingCriteria sort) {
        return sort.equals(SortingCriteria.ALPHABETICAL)
                ? QuickSort.sortAlphabetically(pokemons)
                : QuickSort.sortByLength(pokemons);
    }

    @Override
    public List<Pokemon> getByNamePart(String query, SortingCriteria sort) {
        List<Pokemon> filteredPokemons = cache.getCachedPokemonData().getResults().stream()
                .filter(pokemon -> pokemon.getName().contains(query))
                .collect(Collectors.toList());

        return sortPokemons(filteredPokemons, sort);
    }

    @Override
    public List<Pokemon> getAllPokemons(SortingCriteria sort) {
        return sortPokemons(cache.getCachedPokemonData().getResults(), sort);
    }

    @Override
    public List<Pokemon> getAllHighlight(List<Pokemon> listPokemon, String query) {
        final String highlightedQuery = "<pre>" + query + "</pre>";
        return listPokemon.stream()
                .map(pokemon -> {
                    pokemon.setHighlight(pokemon.getName().replace(query, highlightedQuery));
                    return pokemon;
                })
                .collect(Collectors.toList());
    }
}
