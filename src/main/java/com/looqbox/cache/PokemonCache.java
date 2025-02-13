package com.looqbox.cache;

import com.looqbox.model.PokemonResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Component
public class PokemonCache {

    private final ConcurrentHashMap<String, PokemonResponse> cache = new ConcurrentHashMap<>();
    private final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonCache.class);

    public PokemonCache(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostConstruct
    public void initCache() {
        final long start = System.nanoTime();

        fetchPokemonData()
                .doOnError(error -> LOGGER.error("Error loading API data: {}", error))
                .subscribe(response -> {
                    if (response != null) {
                        final long end = System.nanoTime();
                        LOGGER.info("Cache -> Time taken to loading API: {}", end - start);
                        cache.put("pokemon_data", response);
                    }
                });
    }

    public void put(String key, PokemonResponse value) {
        cache.put(key, value);
    }

    public PokemonResponse get(String key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }

    private Mono<PokemonResponse> fetchPokemonData() {
        return webClient.get()
                .retrieve()
                .bodyToMono(PokemonResponse.class);
    }

    public PokemonResponse getCachedPokemonData() {
        return cache.get("pokemon_data");
    }
}
