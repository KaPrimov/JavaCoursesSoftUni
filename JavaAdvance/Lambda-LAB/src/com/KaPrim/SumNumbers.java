package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class SumNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(", ");
        Function<String, Integer> parser = x -> Integer.parseInt(x);

        int sum = 0;
        for (String num : input) {
            sum += parser.apply(num);
        }

        System.out.println("Count = " + input.length);
        System.out.println("Sum = " + sum);
    }
}
