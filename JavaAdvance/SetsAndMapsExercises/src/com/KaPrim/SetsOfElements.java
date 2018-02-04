package com.KaPrim;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class SetsOfElements {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        HashSet<Long> num1 = new LinkedHashSet<>();
        HashSet<Long> num2 = new LinkedHashSet<>();

        String[] params = scanner.nextLine().split("\\s+");

        int firstLoop = Integer.parseInt(params[0]);
        int secondLoop = Integer.parseInt(params[1]);

        for (int i = 0; i < firstLoop; i++) {
            long num = Long.parseLong(scanner.nextLine());
            num1.add(num);
        }

        for (int i = 0; i < secondLoop; i++) {
            long num = Long.parseLong(scanner.nextLine());
            num2.add(num);
        }

        num1.retainAll(num2);

        for (Long num : num1) {
            System.out.print(num + " ");
        }
    }
}
