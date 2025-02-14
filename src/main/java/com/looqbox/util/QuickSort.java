package com.looqbox.util;

import com.looqbox.model.Pokemon;
import java.util.ArrayList;
import java.util.List;

public final class QuickSort implements SortCompare {

    /**
     * Sorts a list of Pokemon alphabetically by their name using the QuickSort
     * algorithm.
     *
     * <p>
     * This method implements a recursive QuickSort approach:
     * <ul>
     * <li>Chooses a pivot (middle element of the list).</li>
     * <li>Partitions the list into three categories:
     * <ul>
     * <li>Pokemon with names alphabetically smaller than the pivot.</li>
     * <li>Pokemon with names equal to the pivot.</li>
     * <li>Pokemon with names alphabetically greater than the pivot.</li>
     * </ul>
     * </li>
     * <li>Recursively sorts the smaller and greater partitions.</li>
     * <li>Merges the sorted sublists into a single sorted list.</li>
     * </ul>
     * The average time complexity of this algorithm is <b>O(n log n)</b>
     *
     * @param pokemonList The list of Pokemon to be sorted.
     * @return A new list of Pokemon sorted in alphabetical order.
     */
    public static List<Pokemon> sortAlphabetically(List<Pokemon> pokemonList) {
        if (pokemonList.size() <= 1) {
            return pokemonList; // Base case: already sorted.
        }
        // Selecting the pivot (middle element).
        Pokemon pivotPokemon = pokemonList.get(pokemonList.size() / 2);
        String pivot = pivotPokemon.getName();

        // Partitioning lists.
        List<Pokemon> less = new ArrayList<>();
        List<Pokemon> equal = new ArrayList<>();
        List<Pokemon> greater = new ArrayList<>();

        // Categorizing each Pokemon based on its name compared to the pivot.
        SortCompare sorter = new QuickSort();
        for (Pokemon pokemon : pokemonList) {
            int comparison = sorter.stringCompare(pokemon.getName(), pivot);

            if (comparison < 0) {
                less.add(pokemon);
            } else if (comparison > 0) {
                greater.add(pokemon);
            } else {
                equal.add(pokemon);
            }
        }

        // Recursive sorting and merging.
        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.addAll(sortAlphabetically(less));
        sortedList.addAll(equal);
        sortedList.addAll(sortAlphabetically(greater));

        return sortedList;
    }

    /**
     * Sorts a list of Pokemon by the length of their names using the QuickSort
     * algorithm.
     *
     * <p>
     * This method follows the same recursive QuickSort approach but uses the
     * <b>length</b> of the name instead of alphabetical order:
     * <ul>
     * <li>Chooses a pivot (middle element of the list).</li>
     * <li>Partitions the list into three categories:
     * <ul>
     * <li>Pokemon with names shorter than the pivot.</li>
     * <li>Pokemon with names of the same length as the pivot.</li>
     * <li>Pokemon with names longer than the pivot.</li>
     * </ul>
     * </li>
     * <li>Recursively sorts the shorter and longer partitions.</li>
     * <li>Merges the sorted sublists into a single sorted list.</li>
     * </ul>
     * The average time complexity of this algorithm is <b>O(n log n)</b>.
     *
     * @param pokemonList The list of Pokemon to be sorted.
     * @return A new list of Pokemon sorted by the length of their names.
     */
    public static List<Pokemon> sortByLength(List<Pokemon> pokemonList) {
        if (pokemonList.size() <= 1) {
            return pokemonList; // Base case: already sorted.
        }
        // Selecting the pivot (middle element).
        Pokemon pivotPokemon = pokemonList.get(pokemonList.size() / 2);
        int pivotLength = pivotPokemon.getName().length();

        // Partitioning lists.
        List<Pokemon> less = new ArrayList<>();
        List<Pokemon> equal = new ArrayList<>();
        List<Pokemon> greater = new ArrayList<>();

        // Categorizing each Pokemon based on the length of its name compared to the pivot.
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

        // Recursive sorting and merging.
        List<Pokemon> sortedList = new ArrayList<>();
        sortedList.addAll(sortByLength(less));
        sortedList.addAll(equal);
        sortedList.addAll(sortByLength(greater));

        return sortedList;
    }

    @Override
    public int stringCompare(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());

        for (int i = 0; i < minLength; i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);

            if (char1 < char2) {
                return -1;  
            } else if (char1 > char2) {
                return 1;   
            }
        }

        if (str1.length() < str2.length()) {
            return -1;  
        } else if (str1.length() > str2.length()) {
            return 1;   
        }

        return 0;
    }

}
