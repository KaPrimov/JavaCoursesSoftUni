package com.KaPrim;

import java.util.Scanner;

public class CollectCoins {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] jagged = new String[4][];

        for (int i = 0; i < jagged.length; i++) {
            String[] input = scanner.nextLine().split("");
            jagged[i] = new String[input.length];

            for (int j = 0; j < jagged[i].length; j++) {
                jagged[i][j] = input[j];
            }
        }
        int currentCol=0;
        int currentRow=0;
        long money=0L;
        long wallHits=0;

        if (jagged[currentRow][currentCol].equals("$")) {
            money++;
        }

        String[] directions = scanner.next().split("");
        for (String direction : directions) {
            switch (direction) {
                case "V":
                    currentRow++;
                    if (currentRow > 3 || currentCol > jagged[currentRow].length-1 ) {
                        wallHits++;
                        currentRow--;
                    } else {
                        if(jagged[currentRow][currentCol].equals("$")) {
                            money++;
                        }
                    }
                    break;
                case ">":
                    currentCol++;
                    if (currentCol > jagged[currentRow].length - 1) {
                        wallHits++;
                        currentCol--;
                    } else {
                        if (jagged[currentRow][currentCol].equals("$")) {
                            money++;
                        }
                    }
                    break;
                case "<":
                    currentCol--;
                    if (currentCol<0) {
                        currentCol=0;
                        wallHits++;
                    } else {
                        if (jagged[currentRow][currentCol].equals("$")) {
                            money++;
                        }
                    }
                    break;
                case "^":
                    currentRow--;
                    if (currentRow<0 || currentCol > jagged[currentRow].length-1) {
                        currentRow=0;
                        wallHits++;
                    } else {
                        if (jagged[currentRow][currentCol].equals("$")) {
                            money++;
                        }
                    }
                    break;
            }
        }

        System.out.println("Coins = " + money);
        System.out.println("Walls = " + wallHits);

    }
}
