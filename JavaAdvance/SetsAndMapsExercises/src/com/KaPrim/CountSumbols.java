package com.KaPrim;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSumbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] sentence = scanner.nextLine().toCharArray();
        Map<Character, Long> lettersCount = new TreeMap<>();

        for (char letter : sentence) {
            if (lettersCount.containsKey(letter)) {
                lettersCount.put(letter, lettersCount.get(letter) + 1);
            } else {
                lettersCount.put(letter, 1L);
            }
        }

        for (Character character : lettersCount.keySet()) {
            System.out.println(character + ": " + lettersCount.get(character) + " time/s" );
        }

    }
}
