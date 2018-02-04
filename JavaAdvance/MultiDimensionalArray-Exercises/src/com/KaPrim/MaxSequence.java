package com.KaPrim;

import java.util.Scanner;

public class MaxSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        String[][] matrix = new String[rows][cols];
        String bestString = "";
        int bestCount = 1;
        int currentLength = 1;

        for (int row = 0; row < rows; row++) {
            String[] currentRow = scanner.nextLine().split(" ");
            for (int col = 0; col < cols ; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols ; col++) {
                currentLength = checkDiagonal(matrix, row, col, matrix[row][col]);
                if (currentLength > bestCount)
                {
                    bestCount = currentLength;
                    bestString = matrix[row][col];
                }
                currentLength = checkVertical(matrix, row, col, matrix[row][col]);
                if (currentLength > bestCount)
                {
                    bestCount = currentLength;
                    bestString = matrix[row][col];
                }
                currentLength = checkHorizontal(matrix, row, col, matrix[row][col]);
                if (currentLength > bestCount)
                {
                    bestCount = currentLength;
                    bestString = matrix[row][col];
                }

            }
        }

        for (int i = 0; i < bestCount; i++) {
            System.out.print(bestString + ", ");
        }

    }
    private static int checkDiagonal(String[][] matrix, int row, int col, String currentValue) {
        int currentLength = 1;
        for (int i = row, j = col; i < matrix.length && j < matrix[row].length; i++, j++)
        {
            if (i + 1 < matrix.length && j + 1 < matrix[i + 1].length && matrix[i + 1][j + 1].equals(currentValue))
            {
                currentLength++;
            } else {
                break;
            }
        }
        return currentLength;
    }

    private static int checkHorizontal(String[][] matrix, int row, int col, String currentValue) {
        int currentLength = 1;
        for (int i = col; i < matrix[row].length; i++)
        {
            if (i + 1 < matrix[row].length && matrix[row][i + 1].equals(currentValue))
            {
                currentLength++;
            } else {
                break;
            }
        }
        return currentLength;
    }

    private static int checkVertical(String[][] matrix, int row, int col, String currentValue) {
        int currentLength = 1;
        for (int i = row; i < matrix.length; i++)
        {
            if (i + 1 < matrix.length && matrix[i + 1][col].equals(currentValue))
            {
                currentLength++;
            } else {
                break;
            }
        }
        return currentLength;

    }
}
