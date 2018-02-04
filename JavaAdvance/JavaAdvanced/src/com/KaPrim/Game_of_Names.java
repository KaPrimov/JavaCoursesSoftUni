package com.KaPrim;

import java.util.Scanner;

public class Game_of_Names {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());
        long bestScore = Long.MIN_VALUE;
        String bestPlayer = "";

        for(int i=0; i<n; i++) {
            String name = scanner.nextLine();
            long score = Long.parseLong(scanner.nextLine());
            for (int j=0; j<name.length(); j++) {
                long points = name.charAt(j);
                if (points % 2 == 1) {
                    score -= points;
                } else {
                    score += points;
                }
            }

            if (score > bestScore) {
                bestPlayer = name;
                bestScore = score;
            }
        }
        System.out.printf("The winner is %s - %d points", bestPlayer, bestScore);
    }
}
