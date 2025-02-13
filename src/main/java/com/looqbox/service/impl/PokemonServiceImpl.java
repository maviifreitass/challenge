package com.looqbox.service.impl;

import com.looqbox.enums.SortingCriteria;
import com.looqbox.model.Pokemon;
import com.looqbox.model.PokemonResponse;
import com.looqbox.service.PokemonService;
import com.looqbox.util.QuickSort;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final WebClient webClient;

    public PokemonServiceImpl(Pokemon pokemon, WebClient webClient) {
        this.webClient = webClient;
    }

    private PokemonResponse fetchPokemonData() {
        return webClient.get()
                .uri("/pokemon/")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block();
    }

    private List<Pokemon> sortPokemons(List<Pokemon> pokemons, SortingCriteria sort) {
        return sort.equals(SortingCriteria.ALPHABETICAL)
                ? QuickSort.sortAlphabetically(pokemons)
                : QuickSort.sortByLength(pokemons);
    }

    @Override
    public List<Pokemon> getByNamePart(String query, SortingCriteria sort) {
        PokemonResponse pokemonResp = fetchPokemonData();

        List<Pokemon> filteredPokemons = pokemonResp.getResults().stream()
                .filter(pokemon -> pokemon.getName().contains(query))
                .collect(Collectors.toList());

        return sortPokemons(filteredPokemons, sort);
    }

    @Override
    public List<Pokemon> getAllPokemons(SortingCriteria sort) {
        PokemonResponse pokemonResp = fetchPokemonData();
        return sortPokemons(pokemonResp.getResults(), sort);
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
