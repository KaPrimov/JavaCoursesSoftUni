package com.KaPrim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstOddOrEvenElements{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Long> numbers = new ArrayList<>();
        String[] inputNumbers = scanner.nextLine().trim().split("\\s+");
        for (int i=0; i<inputNumbers.length; i++) {
            numbers.add(Long.valueOf(inputNumbers[i]));
        }

        String[] command = scanner.nextLine().split("\\s+");
        try {
            long numOfSearched = Long.valueOf(command[1]);
            String typeSearched = command[2].trim();
            if (numOfSearched > 0 && command[0].equals("Get") && command.length == 3 ) {
                findResult(numbers, typeSearched, numOfSearched);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void findResult(List<Long> numbers, String type, long count) {
        int searchedResult = 0;
        String result = "";
        int check = 0;
        if (type.toLowerCase().equals("even")) {
            searchedResult = 0;
        } else if(type.toLowerCase().equals("odd")) {
            searchedResult = 1;
        } else {
            searchedResult = 3;
        }
        for (int i=0; i<numbers.size(); i++) {
            if (Math.abs(numbers.get(i)) % 2 == searchedResult) {
                check ++;
                if (check > count) {
                    break;
                }
                result += numbers.get(i) + " ";
            }
        }
        if (result.length() > 0) {
            System.out.print(result.trim());
        }
    }
}