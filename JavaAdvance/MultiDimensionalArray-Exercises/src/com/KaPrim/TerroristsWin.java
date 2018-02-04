package com.KaPrim;

import java.util.Scanner;

public class TerroristsWin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder map = new StringBuilder(scanner.nextLine());
        int currentIndex=0;
        int bombStart = 0;
        int bombEnd = 0;

        while ((bombStart = map.indexOf("|", currentIndex)) != -1) {
            bombEnd = map.indexOf("|", bombStart+1);
            String bombContent = map.substring(bombStart + 1, bombEnd);
            int bombPower = getBombPower(bombContent);
            int startIndex = Math.max(0, bombStart-bombPower);
            int endIndex = Math.min(map.length() - 1, bombEnd + bombPower);
            while (startIndex <= endIndex) {
                map.setCharAt(startIndex, '.');
                startIndex++;
            }

            currentIndex = bombEnd + 1;
        }
        System.out.println(map);
    }
    private static int getBombPower(String bombContent) {
        int bombPower = 0;
        for (char letter : bombContent.toCharArray() ) {
            bombPower+=letter;
        }
        return bombPower%10;
    }
}
