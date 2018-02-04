package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Monopoly {
    private static String[][] matrix;
    private static int rows;
    private static int cols;
    private static int hotelsCount = 0;
    private static int turns = 0;
    private static long money;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        money = 50;
        rows = dimensions[0];
        cols = dimensions[1];

        matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            matrix[row] = reader.readLine().split("");
        }

        for (int row = 0; row < rows; row++) {
           if(row % 2 == 0) {
               for (int col = 0; col < cols; col++) {
                    performOperation(row, col);
                    money += (10 * hotelsCount);
               }
           } else {
               for (int col = cols-1; col >= 0; col--) {
                   performOperation(row, col);
                   money += (10 * hotelsCount);
               }
           }
        }
        System.out.println("Turns " + turns);
        System.out.println("Money " + money);
    }

    private static void performOperation(int row, int col) {
        switch (matrix[row][col]) {
            case "H":
                hotelsCount++;
                System.out.printf("Bought a hotel for %d. Total hotels: %d.%n", money, hotelsCount);
                money = 0;
                turns++;
                break;
            case "F":
                turns++;
                break;
            case "S":
                turns ++;
                int moneyToPay = (row + 1) * (col+1);
                if (moneyToPay <= money) {
                    money -= moneyToPay;
                    System.out.printf("Spent %d money at the shop.%n", moneyToPay);
                } else {
                    System.out.printf("Spent %d money at the shop.%n", money);
                    money = 0;
                }
                break;
            case "J":
                System.out.printf("Gone to jail at turn %d.%n", turns);
                money += 2*(10*hotelsCount);
                turns += 3;
                break;
            default:
                break;
        }
    }
}
