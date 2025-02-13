package com.looqbox.tests;

import com.looqbox.cache.PokemonCache;
import com.looqbox.model.Pokemon;
import com.looqbox.model.PokemonResponse;
import com.looqbox.util.QuickSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

final class TestApplication {

    private static List<Pokemon> MOCK_LIST;
    private static final Logger LOGGER = LoggerFactory.getLogger(TestApplication.class);

    @BeforeAll
    static void start() {
        MOCK_LIST = new ArrayList<>(0);

        final Pokemon pikachu = new Pokemon("Pikachu", "");
        final Pokemon chapi = new Pokemon("Chapi", "");
        final Pokemon charizard = new Pokemon("Charizard", "");
        final Pokemon bulbasaur = new Pokemon("Bulbasaur", "");
        final Pokemon eevee = new Pokemon("Eevee", "");

        MOCK_LIST.addAll(Arrays.asList(pikachu, chapi, charizard, bulbasaur, eevee));
    }

    @Test
    void alphabeticalTimeTest() {
        final List<Pokemon> pokemonList = new ArrayList<>(MOCK_LIST);

        final long start = System.nanoTime();
        QuickSort.sortAlphabetically(pokemonList);
        final long end = System.nanoTime();

        LOGGER.info("Alphabetical -> Time taken: {}", end - start);
    }

    @Test
    void lengthTimeTest() {
        final List<Pokemon> pokemonList = new ArrayList<>(MOCK_LIST);

        final long start = System.nanoTime();
        QuickSort.sortByLength(pokemonList);
        final long end = System.nanoTime();

        LOGGER.info("Length -> Time taken: {}", end - start);
    }

    @Test
    void testSorting() {
        final List<Pokemon> pokemonList = new ArrayList<>(MOCK_LIST);
        LOGGER.info("Normal List: {}", toString(pokemonList));

        LOGGER.info("Length List: {}", toString(QuickSort.sortByLength(pokemonList)));

        LOGGER.info("Alphabetical List: {}", toString(QuickSort.sortAlphabetically(pokemonList)));
    }

    @Test
    public void testInitCachePerfomance() {
        WebClient webClient = WebClient.create("https://pokeapi.co/api/v2/pokemon?limit=11000");
        PokemonCache pokemonCache = new PokemonCache(webClient);

        final long start = System.nanoTime();
        pokemonCache.initCache();
        final long end = System.nanoTime();
        LOGGER.info("Cache -> Time taken to loading API: {}", end - start);
    }

    private String toString(List<Pokemon> pokemonList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < pokemonList.size(); i++) {
            builder.append(pokemonList.get(i).getName());
            if (i < pokemonList.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
