package com.KaPrim;

import java.util.Arrays;
import java.util.Scanner;

public class RadioActiveBunnies {

    private static char[][] lair;
    private static int rows;
    private static int cols;
    private static int playerRow = 0;
    private static int playerCol = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        rows = dimensions[0];
        cols = dimensions[1];
        lair = new char[rows][cols];

        for (int row = 0; row < rows; row++) {
            lair[row] = scanner.nextLine().toCharArray();
        }

        String commands = scanner.nextLine();

        for (int i = 0; i < commands.length(); i++) {
            char direction = commands.charAt(i);
            switch (direction) {
                case 'U':
                    break;
                case 'D':
                    break;
                case 'L':
                    break;
                case 'R':
                    break;
            }
        }
    }

    public static void findPlayer() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(lair[row][col] == 'P') {
                    playerRow = row;
                    playerCol = col;
                    lair[row][col] = '.';
                }
            }
        }
    }
}
