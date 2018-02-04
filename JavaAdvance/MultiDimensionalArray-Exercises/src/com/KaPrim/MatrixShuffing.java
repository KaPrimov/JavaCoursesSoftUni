package com.KaPrim;

import java.util.Scanner;

public class MatrixShuffing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            String[] currentRow = scanner.nextLine().split(" ");
            for (int col = 0; col < cols ; col++) {
                matrix[row][col] = currentRow[col];
            }
        }

        while(true) {
            String[] command = scanner.nextLine().split(" ");

            if(command[0].equals("END")) {
                break;
            }

            if(!command[0].equals("swap") || command.length != 5) {
                System.out.println("Invalid input!");
            } else {
                int firstPointRow = Integer.parseInt(command[1]);
                int firstPointCol = Integer.parseInt(command[2]);
                int secondPointRow = Integer.parseInt(command[3]);
                int secondPointCol = Integer.parseInt(command[4]);
                try {
                    String temp = matrix[firstPointRow][firstPointCol];
                    matrix[firstPointRow][firstPointCol] = matrix[secondPointRow][secondPointCol];
                    matrix[secondPointRow][secondPointCol]=temp;
                    printMatrix(matrix);
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                }
            }
        }
    }
    private static void printMatrix(String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
