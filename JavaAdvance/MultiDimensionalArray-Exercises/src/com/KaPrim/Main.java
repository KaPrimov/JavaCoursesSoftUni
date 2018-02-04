package com.KaPrim;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] sizes = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(sizes[0]);
        int cols = Integer.parseInt(sizes[1]);
        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < matrix.length; i++) {
            String[] values = scanner.nextLine().split(" ");
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = values[j];
            }
        }

        int seqLength = 0;
        int startRow = -1;
        int startCol = -1;
        String type = null;


        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int diagLength = 1;
                for (int i = 1; i < Math.min(rows - row, cols - col); i++) {
                    if (matrix[row][col].equals(matrix[row + i][col + i])) {
                        diagLength++;
                    }
                }
                if (diagLength >= seqLength) {
                    startRow = row;
                    startCol = col;
                    type = "diagonal";
                    seqLength = diagLength;
                }

                int colLength = 1;
                for (int i = 1; i < rows - row; i++) {
                    if (matrix[row][col].equals(matrix[row + i][col])) {
                        colLength++;
                    }
                }
                if (colLength >= seqLength) {
                    startRow = row;
                    startCol = col;
                    type = "column";
                    seqLength = colLength;
                }

                int rowLength = 1;
                for (int i = 1; i < cols - col; i++) {
                    if (matrix[row][col].equals(matrix[row][col + i])) {
                        rowLength++;
                    }
                }
                if (rowLength >= seqLength) {
                    startRow = row;
                    startCol = col;
                    type = "row";
                    seqLength = rowLength;
                }
            }


        }
        if (seqLength > 1) {
            for (int i = 0; i < seqLength; i++) {
                System.out.print(matrix[startRow][startCol] + ", ");
            }
        }
    }
}
