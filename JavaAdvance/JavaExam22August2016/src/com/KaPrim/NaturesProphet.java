package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NaturesProphet {
    private static int rows;
    private static  int cols;
    private static int[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        rows = dimensions[0];
        cols = dimensions[1];

        matrix = new int[rows][cols];

        String[] line = reader.readLine().split(" ");
        while (!"Bloom".equals(line[0])) {
            int[] bloomCoordinates =  Arrays.stream(line).mapToInt(Integer::parseInt).toArray();

            int bloomRow = bloomCoordinates[0];
            int bloomCol = bloomCoordinates[1];

            bloomFlowers(bloomRow, bloomCol);
            line = reader.readLine().split(" ");
        }

        for (int[] ints : matrix) {
            for (int flower : ints) {
                System.out.print(flower + " ");
            }
            System.out.println();
        }

    }

    private static void bloomFlowers(int bloomRow, int bloomCol) {
        for (int row = 0; row < rows; row++) {
            matrix[row][bloomCol]++;
        }

        for (int col = 0; col <cols ; col++) {
            matrix[bloomRow][col]++;
        }

        matrix[bloomRow][bloomCol]--;
    }
}
