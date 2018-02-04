package com.KaPrim;

import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) {
        HashMap<Character, Integer> powers = new HashMap<>();
        powers.put('2', 2);
        powers.put('3', 3);
        powers.put('4', 4);
        powers.put('5', 5);
        powers.put('6', 6);
        powers.put('7', 7);
        powers.put('8', 8);
        powers.put('9', 9);
        powers.put('T', 10);
        powers.put('J', 11);
        powers.put('Q', 12);
        powers.put('K', 13);
        powers.put('A', 14);

        powers.put('S', 4);
        powers.put('H', 3);
        powers.put('D', 2);
        powers.put('C', 1);

        Scanner scanner = new Scanner(System.in);
        Map<String, HashSet<String>> playersHands = new LinkedHashMap<>();
        while (true) {
            String[] input = scanner.nextLine().split(": ");
            if (input[0].equals("JOKER")) {
                break;
            }
            String name = input[0];
            String[] cards = input[1].replaceAll("10", "T").trim().split(", ");
            if (!playersHands.containsKey(name)) {
                playersHands.put(name, new HashSet<>());
            }

            for (String card : cards) {
                playersHands.get(name).add(card);
            }

        }

        for (Map.Entry<String, HashSet<String>> entry : playersHands.entrySet()) {
            long value = 0;
            for(String card: entry.getValue()) {
                long powerValue = powers.get(card.charAt(0));
                long suitValue = powers.get(card.charAt(1));
                value += (powerValue * suitValue);
            }
            System.out.printf("%s: %d%n", entry.getKey(), value);
        }
    }

}
