package com.KaPrim;

import java.util.Scanner;

public class BlurFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int blur = Integer.parseInt(scanner.nextLine());

        String[] dim =scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dim[0]);
        int cols= Integer.parseInt(dim[1]);


        long[][] matrix = new long[rows][cols];

        readMatrix(scanner, rows, cols, matrix);
        String[] blurPoints = scanner.nextLine().split(" ");
        int rowBlur = Integer.parseInt(blurPoints[0]);
        int colBlur = Integer.parseInt(blurPoints[1]);

        int left = Math.max(0, colBlur-1);
        int right = Math.min(colBlur+1, cols);
        int bottom = Math.min(rowBlur+1, rows);
        int top = Math.max(0, rowBlur-1);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if ((left <= col && col <= right) && (top <=row && row <= bottom)) {
                    System.out.printf("%d ", matrix[row][col] + blur);
                } else {
                    System.out.printf("%d ", matrix[row][col]);
                }
            }
            System.out.println();
        }

    }
    public static void readMatrix(Scanner scanner, int row, int col, long[][] matrix) {
        for (int rows=0; rows<row; rows++) {
            String[] inputData = scanner.nextLine().split(" ");
            for (int cols=0; cols<col; cols++ ) {
                matrix[rows][cols] = Long.parseLong(inputData[cols]);
            }
        }
    }
}
