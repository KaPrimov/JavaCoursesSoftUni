package com.KaPrim;

import java.util.Scanner;

public class RubiksMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][cols];

        int assignValues = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = assignValues;
                assignValues++;
            }
        }

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");
            int rowToMove = Integer.parseInt(command[0]);
            String direction = command[1];
            int times = Integer.parseInt(command[2]);

            switch (direction) {
                case "right":
                    for (int j = 0; j < times; j++) {
                        shiftRight(rowToMove, matrix);
                    }
                    break;
                case "left":
                    for (int j = 0; j < times; j++) {
                        shiftLeft(rowToMove, matrix);
                    }
                    break;
                case "down":
                    for (int j = 0; j < times; j++) {
                        shiftDown(rowToMove, matrix);
                    }
                    break;
                case "up":
                    for (int j = 0; j < times; j++) {
                        shiftUp(rowToMove, matrix);
                    }
                    break;

            }
        }

        int checker = 1;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean isTraversed = false;
                if(matrix[row][col] != checker) {
                    for (int tempRow = 0; tempRow < rows; tempRow++) {
                        for (int tempCol = 0; tempCol < cols; tempCol++) {
                            if(matrix[tempRow][tempCol] == checker) {
                                int temp = matrix[tempRow][tempCol];
                                matrix[tempRow][tempCol] = matrix[row][col];
                                matrix[row][col] = temp;
                                isTraversed=true;
                                System.out.printf("Swap (%d, %d) with (%d, %d)\n", row, col, tempRow, tempCol);
                                break;
                            }
                        }
                        if(isTraversed) {
                            break;
                        }
                    }
                } else {
                    System.out.println("No swap required");
                }
                checker++;
            }
        }
    }

    public static void shiftRight(int rowToShift, int[][] matrix) {
        int m = matrix[rowToShift].length;
        int temp = matrix[rowToShift][m-1];
        for (int k=m-1; k>=1; k--){
            matrix[rowToShift][k] = matrix[rowToShift][k-1];
        }
        matrix[rowToShift][0] = temp;
    }

    public static void shiftLeft(int rowToShift, int[][] matrix) {
        int m = matrix[rowToShift].length;
        int temp = matrix[rowToShift][0];
        for (int k=0; k<m-1; k++){
            matrix[rowToShift][k] = matrix[rowToShift][k+1];
        }
        matrix[rowToShift][m-1] = temp;
    }
    public static void shiftDown(int colToShift, int[][] matrix) {
        int m = matrix.length;
        int temp = matrix[m-1][colToShift];
        for (int k=m-1; k>=1; k--){
            matrix[k][colToShift] = matrix[k-1][colToShift];
        }
        matrix[0][colToShift] = temp;
    }

    public static void shiftUp(int colToShift, int[][] matrix) {
        int m = matrix.length;
        int temp = matrix[0][colToShift];
        for (int k=0; k<m-1; k++){
            matrix[k][colToShift] = matrix[k+1][colToShift];
        }
        matrix[m-1][colToShift] = temp;
    }

}
