package com.KaPrim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LegoBlocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> firstMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] numbers = scanner.nextLine().trim().split("\\s+");
            firstMatrix.add(new ArrayList<>());
            for (String number : numbers) {
                firstMatrix.get(i).add(Integer.parseInt(number));
            }
        }
        List<List<Integer>> secondMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] numbers = scanner.nextLine().trim().split("\\s+");
            secondMatrix.add(new ArrayList<>());
            for (int j = numbers.length - 1; j >= 0; j--) {
                secondMatrix.get(i).add(Integer.parseInt(numbers[j]));
            }
        }

        int length = firstMatrix.get(0).size() + secondMatrix.get(0).size();
        boolean areEqual = true;
        int count = length;
        for (int i = 1; i < n; i++) {
            if (length != firstMatrix.get(i).size() + secondMatrix.get(i).size()) {
                areEqual = false;
            }
            count += firstMatrix.get(i).size() + secondMatrix.get(i).size();
        }


        if(areEqual) {
            int[][] answer = new int[n][length];
            for (int i = 0; i < n; i++) {
                for (int colFirstMatrix = 0; colFirstMatrix < firstMatrix.get(i).size(); colFirstMatrix++) {
                    answer[i][colFirstMatrix] = firstMatrix.get(i).get(colFirstMatrix);
                }

                for (int colSecondMatrix = 0; colSecondMatrix < secondMatrix.get(i).size(); colSecondMatrix++) {
                    answer[i][firstMatrix.get(i).size() + colSecondMatrix] = secondMatrix.get(i).get(colSecondMatrix);
                }
            }


            StringBuilder sb = new StringBuilder();
            for (int[] ints : answer) {
                sb.append("[");
                for (int anInt : ints) {
                    sb.append(String.format("%d, ", anInt));
                }
                sb.delete(sb.length()-2, sb.length());
                sb.append("]\n");
            }

            System.out.println(sb.toString());


        } else {
            System.out.println("The total number of cells is: " + count);
        }

    }
}
