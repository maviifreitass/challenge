package com.looqbox.service.impl;

import com.looqbox.model.Pokemon;
import com.looqbox.model.PokemonResponse;
import com.looqbox.service.PokemonService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final Pokemon pokemon;
    private final WebClient webClient;

    public PokemonServiceImpl(Pokemon pokemon, WebClient webClient) {
        this.pokemon = pokemon;
        this.webClient = webClient;
    }

    @Override
    public List<Pokemon> getByNamePart(String query) {
        PokemonResponse pokemonResp = webClient.get()
                .uri("/pokemon/")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block(); // REDUNDANCY TO CHANGE

        return pokemonResp.getResults().stream()
                .filter(pokemon -> pokemon.getName().contains(query)) 
                .collect(Collectors.toList());
        
        // SORT
    }

    @Override
    public List<Pokemon> getAllPokemons() {
        PokemonResponse pokemonResp = webClient.get()
                .uri("/pokemon/")
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .block(); // REDUNDANCY TO CHANGE

        return pokemonResp.getResults();

        // SORT
    }

}
