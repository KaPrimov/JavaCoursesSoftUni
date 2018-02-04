package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NMS {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String word = reader.readLine();
        StringBuilder words = new StringBuilder();
        while (!"---NMS SEND---".equals(word)) {
            words.append(word);
            word = reader.readLine();
        }
        String deli = reader.readLine();

        StringBuilder answer = new StringBuilder();
        StringBuilder tempWord = new StringBuilder();
        for (int i = 1; i < words.length(); i++) {
            if ((int) Character.toLowerCase(words.charAt(i-1)) <= (int) Character.toLowerCase(words.charAt(i))) {
                tempWord.append(words.charAt(i-1));
            } else {
                tempWord.append(words.charAt(i-1));
                answer.append(tempWord);
                answer.append(deli);
                tempWord.delete(0, tempWord.length());
            }
        }
        tempWord.append(words.charAt(words.length()-1));
        answer.append(tempWord);

        System.out.println(answer.toString());
    }
}
