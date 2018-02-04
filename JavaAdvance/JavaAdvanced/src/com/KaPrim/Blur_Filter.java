package com.KaPrim;

import java.util.Scanner;

public class Blur_Filter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long blur = Long.parseLong(scanner.nextLine());
        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        long[][] matrix = new long[rows][cols];

        readMatrix(scanner, rows, cols, matrix);
        scanner.nextLine();
        String[] blurPoints = scanner.nextLine().split(" ");
        int rowBlur = Integer.parseInt(blurPoints[0]);
        int colBlur = Integer.parseInt(blurPoints[1]);

        try
        {
            matrix[rowBlur][colBlur] += blur;
        }
        catch (Exception e) {}
        try
        {
            matrix[rowBlur - 1][colBlur] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur - 1][colBlur + 1] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur][colBlur + 1] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur + 1][colBlur + 1] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur + 1][colBlur] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur + 1][colBlur - 1] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur][colBlur - 1] += blur;
        }
        catch (Exception e) { }
        try
        {
            matrix[rowBlur - 1][colBlur - 1] += blur;
        }
        catch (Exception e) { }

        printMatrix(rows, cols, matrix);

    }
    public static void readMatrix(Scanner scanner, int row, int col, long[][] matrix) {
        for (int rows=0; rows<row; rows++) {
            for (int cols=0; cols<col; cols++ ) {
                matrix[rows][cols] = scanner.nextLong();
            }
        }
    }

    public static void printMatrix(int row, int col, long[][] matrix) {
        for (int rows=0; rows<row; rows++) {
            for (int cols=0; cols<col-1; cols++ ) {
                System.out.print(matrix[rows][cols] + " ");
            }
            System.out.println(matrix[rows][col-1]);
        }
    }
}
