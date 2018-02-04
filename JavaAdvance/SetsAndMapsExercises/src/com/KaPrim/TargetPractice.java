package com.KaPrim;

import java.util.Scanner;

public class TargetPractice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        char[] snake = scanner.nextLine().toCharArray();
        String[] shotParams = scanner.nextLine().split(" ");
        int impactRow = Integer.parseInt(shotParams[0]);
        int impactCol = Integer.parseInt(shotParams[1]);
        int blastRadius = Integer.parseInt(shotParams[2]);

        char[][] matrix = new char[rows][cols];

        int indexLetter = 0;
        boolean changeSide = true;
        for (int row = rows-1; row >= 0; row--) {
            if(changeSide) {
                for (int col = cols-1; col >= 0 ; col--) {
                    matrix[row][col] = snake[indexLetter];
                    indexLetter = (indexLetter+1) % snake.length;
                }
            } else {
                for (int col = 0; col < cols; col++) {
                    matrix[row][col] = snake[indexLetter];
                    indexLetter = (indexLetter+1) % snake.length;
                }
            }
            changeSide = !changeSide;
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (Math.pow(impactRow - row, 2) + Math.pow(impactCol - col, 2) <= Math.pow(blastRadius, 2)) {
                    matrix[row][col] = ' ';
                }
            }
        }

        for (int col = 0; col < cols; col++) {
            for (int row = rows-1; row >= 0; row--) {
                if(matrix[row][col] == ' ') {
                    int cellRowNotEmpty = row - 1;
                    while (cellRowNotEmpty >= 0 && matrix[row][col] == ' ') {
                        matrix[row][col] = matrix[cellRowNotEmpty][col];
                        matrix[cellRowNotEmpty][col] = ' ';
                        cellRowNotEmpty--;
                    }
                }
            }
        }

        for (char[] rowss : matrix) {
            for (char row : rowss) {
                System.out.print(row);
            }
            System.out.println();
        }
    }
}
