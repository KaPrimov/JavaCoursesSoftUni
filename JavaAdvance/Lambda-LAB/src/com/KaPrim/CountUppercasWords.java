package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CountUppercasWords {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> wordsInput = Arrays.asList(reader.readLine().split(" "));

        Predicate<String> checkUpper = s -> s.charAt(0) == s.toUpperCase().charAt(0);

        List<String> result = new ArrayList<>();

        for (int i = 0; i < wordsInput.size(); i++) {
            if (checkUpper.test(wordsInput.get(i))) {
                result.add(wordsInput.get(i));
            }
        }

        System.out.println(result.size());
        for (String s : result) {
            System.out.println(s);
        }
    }
}
