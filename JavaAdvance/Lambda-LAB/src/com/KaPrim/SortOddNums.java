package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortOddNums {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(", ");
        List<Integer> numbers = new ArrayList<>();

        for (String num : input) {
            numbers.add(Integer.parseInt(num));
        }

        StringBuilder answer = new StringBuilder();
        numbers.removeIf(a -> a % 2 != 0);
        for (Integer number : numbers) {
            answer.append(String.format("%d, ", number));
        }

        answer.delete(answer.length()-2, answer.length());
        System.out.println(answer);

        numbers.sort((a, b) -> a.compareTo(b));
        for (int i = 0; i < numbers.size(); i++) {
            if (i != numbers.size()-1) {
                System.out.print(numbers.get(i) + ", ");
            } else {
                System.out.println(numbers.get(i));
            }
        }
    }
}
