package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Shockwave {
        private static int[][] matrix;
        private  static int rows;
        private  static int cols;

    public static void main(String[] args) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    int[] dimensions = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        rows = dimensions[0];
        cols = dimensions[1];
        matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = 0;
            }
        }

        while (true) {
            String[] input = reader.readLine().split("\\s+");

            if("Here".equals(input[0])) {
                break;
            }

            int x1 = Integer.parseInt(input[0]);
            int y1 = Integer.parseInt(input[1]);
            int x2 = Integer.parseInt(input[2]);
            int y2 = Integer.parseInt(input[3]);

            for (int row = x1; row <= x2; row++) {
                for (int col = y1; col <= y2 ; col++) {
                    if(isInMatrix(row, col)) {
                        matrix[row][col]++;
                    }
                }
            }

        }

        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%d ", anInt);
            }
            System.out.println();
        }
    }

    private static boolean isInMatrix(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
