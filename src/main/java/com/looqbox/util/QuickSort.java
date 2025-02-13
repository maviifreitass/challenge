package com.looqbox.util;

import com.looqbox.model.Pokemon;
import java.util.ArrayList;
import java.util.List;

public final class QuickSort {

    public static List<Pokemon> sortAlphabetically(List<Pokemon> pokemonList) {
        if (pokemonList.size() <= 1) return pokemonList; 
        Pokemon pivotPokemon = pokemonList.get(pokemonList.size() / 2);
        String pivot = pivotPokemon.getName();

        List<Pokemon> less = new ArrayList<>(), equal = new ArrayList<>(), greater = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            int comparison = pokemon.getName().compareTo(pivot);
            if (comparison < 0) {
                less.add(pokemon);
            } else if (comparison > 0) {
                greater.add(pokemon);
            } else {
                equal.add(pokemon);
            }
        }

        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.addAll(sortAlphabetically(less));
        sortedList.addAll(equal);
        sortedList.addAll(sortAlphabetically(greater));

        return sortedList;
    }

    public static List<Pokemon> sortByLength(List<Pokemon> pokemonList) {
        if (pokemonList.size() <= 1) return pokemonList; 
        Pokemon pivotPokemon = pokemonList.get(pokemonList.size() / 2);
        int pivotLength = pivotPokemon.getName().length();

        List<Pokemon> less = new ArrayList<>(), equal = new ArrayList<>(), greater = new ArrayList<>();

        for (Pokemon pokemon : pokemonList) {
            int nameLength = pokemon.getName().length();
            if (nameLength < pivotLength) {
                less.add(pokemon);
            } else if (nameLength > pivotLength) {
                greater.add(pokemon);
            } else {
                equal.add(pokemon);
            }
        }

        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.addAll(sortByLength(less));
        sortedList.addAll(equal);
        sortedList.addAll(sortByLength(greater));

        return sortedList;
    }

}
