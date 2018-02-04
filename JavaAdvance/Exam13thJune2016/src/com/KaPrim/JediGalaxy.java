package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JediGalaxy {
    private static long[][] matrix;
    private static int rows;
    private static int cols;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] dimensions = reader.readLine().split(" ");
        rows = Integer.parseInt(dimensions[0]);
        cols = Integer.parseInt(dimensions[1]);
        int count = 0;
        matrix = new long[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = count;
                count++;
            }
        }
        long sum = 0;
        while (true) {
            String ivoInput = reader.readLine();
            if("Let the Force be with you".equals(ivoInput)) {
                break;
            }
            int[] evilCoordinates = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int evilRow = evilCoordinates[0];
            int evilCol = evilCoordinates[1];

            int[] ivoCoordinates = Arrays.stream(ivoInput.split(" ")).mapToInt(Integer::parseInt).toArray();
            int goodRow = ivoCoordinates[0];
            int goodCol = ivoCoordinates[1];

//            if (goodCol < 0 || goodRow > rows) {
//                int offset = Math.abs(goodCol);
//                goodRow -= offset;
//                goodCol += offset;
//            }

            while (goodRow >= rows || goodCol < 0) {
                goodCol++;
                goodRow--;
            }

            while (evilRow >= rows || evilCol >= cols) {
                evilRow--;
                evilCol--;
            }
            while (evilCol >= 0 && evilRow >= 0) {
                matrix[evilRow][evilCol] = 0;
                evilRow--;
                evilCol--;
            }

            while (goodCol < cols && goodRow >= 0) {
                sum += matrix[goodRow][goodCol];
                goodRow--;
                goodCol++;
            }

        }

        System.out.println(sum);

    }
    private static boolean isInMatrix(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
