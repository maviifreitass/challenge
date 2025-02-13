package com.looqbox.controller;

import com.looqbox.enums.SortingCriteria;
import com.looqbox.model.Pokemon;
import com.looqbox.model.dto.PokemonDTO;
import com.looqbox.service.PokemonService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<ResponseEntity<PokemonDTO>> getPokemons(
            @RequestParam(required = false, defaultValue = "") String query,
            @RequestParam(required = false, defaultValue = "ALPHABETICAL") SortingCriteria sort) {
        
        List<Pokemon> pokemonResults = !"".equals(query) ? service.getByNamePart(query) : service.getAllPokemons();

        return Mono.just(ResponseEntity.ok(new PokemonDTO(pokemonResults)));
    }

}
