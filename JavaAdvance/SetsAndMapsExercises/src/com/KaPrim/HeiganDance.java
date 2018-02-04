package com.KaPrim;

import java.util.Scanner;

public class HeiganDance {
    private static int playerCurrentRow = 7;
    private static int playerCurrentCol = 7;
    private static String[][] matrix = new String[15][15];
    private static int playerHP = 18500;
    private static String spell = "Eruption";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
             matrix[i][j] = "E";
            }
        }
        Double damagePlayer = Double.parseDouble(scanner.nextLine());
        double heiganHP = 3_000_000.00;

        while (true) {
            if (playerHP <= 0 || heiganHP <= 0) {
                break;
            }
            String[] command = scanner.nextLine().split(" ");

            String typeOfAttack = command[0];
            int attackRow = Integer.parseInt(command[1]);
            int attackCol = Integer.parseInt(command[2]);

            for (int row = attackRow-1; row <= attackRow+1; row++) {
                for (int col = attackCol-1; col <= attackCol+1; col++) {
                    if(isInMatrix(row, col)) {
//                        if(matrix[row][col].indexOf("c") > -1) {
//                            matrix[row][col] = "C";
//                        }
                        if (typeOfAttack.equals("Cloud")) {
                            if (matrix[row][col].indexOf("C") > -1) {
                                matrix[row][col] += "c";
                            } else {
                                matrix[row][col] = "c";
                            }
                        } else if (typeOfAttack.equals("Eruption")) {
                            if (matrix[row][col].indexOf("c") > -1 || matrix[row][col].indexOf("C") > -1) {
                                matrix[row][col] += "R";
                            } else {
                                matrix[row][col] = "R";
                            }
                        }
                    }
                }
            }

            movePlayer();
            calcDmgToPlayer();
            modifyMatrix();
            heiganHP -= damagePlayer;
        }

        if(matrix[playerCurrentRow][playerCurrentCol].indexOf("C") > -1 || matrix[playerCurrentRow][playerCurrentCol].indexOf("c") > -1) {
            playerHP -= 3500;
        }

        if(heiganHP <= 0) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f\n", heiganHP);
        }

        if(playerHP <= 0) {
            System.out.println("Player: Killed by " + spell );
        } else {
            System.out.printf("Player: %d\n" , playerHP);
        }

        System.out.printf("Final position: %d, %d", playerCurrentRow, playerCurrentCol);

    }

    private static void modifyMatrix() {
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                String currentRow = matrix[row][col];
                if(currentRow.indexOf("c") > -1) {
                    matrix[row][col] = "C";
                } else if (currentRow.indexOf("C") > -1 || currentRow.indexOf("R") > -1) {
                    matrix[row][col] = "E";
                }

            }
        }
    }

    private static void movePlayer() {
        if(!matrix[playerCurrentRow][playerCurrentCol].equals("E")) {
            int currentLength = matrix[playerCurrentRow][playerCurrentCol].length();
            if ((isInMatrix(playerCurrentRow - 1, playerCurrentCol)) && (matrix[playerCurrentRow-1][playerCurrentCol].equals("E") || matrix[playerCurrentRow - 1][playerCurrentCol].length() < currentLength)) {
                playerCurrentRow = playerCurrentRow -1;
            } else if ((isInMatrix(playerCurrentRow, playerCurrentCol+1)) && (matrix[playerCurrentRow][playerCurrentCol+1].equals("E") || matrix[playerCurrentRow][playerCurrentCol + 1].length() < currentLength)) {
                playerCurrentCol = playerCurrentCol + 1;
            } else if ((isInMatrix(playerCurrentRow + 1, playerCurrentCol)) && (matrix[playerCurrentRow+1][playerCurrentCol].equals("E") || matrix[playerCurrentRow + 1][playerCurrentCol].length() < currentLength)) {
                playerCurrentRow  = playerCurrentRow + 1;
            } else if ((isInMatrix(playerCurrentRow, playerCurrentCol-1)) && (matrix[playerCurrentRow][playerCurrentCol-1].equals("E") || matrix[playerCurrentRow][playerCurrentCol -1].length() < currentLength)) {
                playerCurrentCol= playerCurrentCol - 1;
            }
        }
    }

    private static void calcDmgToPlayer() {
        String command = matrix[playerCurrentRow][playerCurrentCol];

        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) == 'c') {
                playerHP -= 3500;
                if (playerHP <= 0) {
                    spell = "Plague Cloud";
                    return;
                }
            } else if (command.charAt(i) == 'C') {
                playerHP -= 3500;
                if (playerHP <= 0) {
                    spell = "Plague Cloud";
                    return;
                }
            } else if (command.charAt(i) == 'R') {
                playerHP-= 6000;
                spell = "Eruption";
            }
        }
    }

    private static boolean isInMatrix( int attackRow, int attackCol) {
        return attackRow >= 0 && attackRow < 15 && attackCol >= 0 && attackCol < 15;
    }
}
