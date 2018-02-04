package com.KaPrim;

import java.util.Scanner;

public class Character_Multiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().trim().split("\\s");

        String wordA;
        String wordB;
        if (strings[0].length() > strings[1].length()) {
            wordA = strings[0];
            wordB = strings[1];
        } else {
            wordB = strings[0];
            wordA = strings[1];
        }

        long charSum = calculateCharSum(wordA, wordB);
        System.out.println(charSum);

    }

    private static long calculateCharSum(String wordA, String wordB) {
        long charSum = 0;
        for (int i = 0; i < wordA.length(); i++) {
            if (i < wordB.length()) {
                charSum += (wordA.charAt(i) * wordB.charAt(i));
            } else {
                charSum += wordA.charAt(i);
            }
        }

        return charSum;
    }
}