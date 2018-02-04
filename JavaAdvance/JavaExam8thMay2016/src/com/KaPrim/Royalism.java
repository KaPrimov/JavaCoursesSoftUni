package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Royalism {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] religiousDudes = reader.readLine().split(" ");

        List<String> royalists = new LinkedList<>();
        List<String> nonRoyalists = new LinkedList<>();

        for (String religiousDude : religiousDudes) {
            long sum = 0L;
            for (int i = 0; i < religiousDude.length(); i++) {
                sum += (long) religiousDude.charAt(i);
            }

            if(sum%26 == 18) {
                royalists.add(religiousDude);
            } else {
                nonRoyalists.add(religiousDude);
            }
        }

        if(royalists.size() >= nonRoyalists.size()) {
            System.out.println("Royalists - " + royalists.size());
            for (int i = 0; i < royalists.size(); i++) {
                System.out.println(royalists.get(i));
            }
            System.out.println("All hail Royal!");
        } else {
            for (int i = 0; i < nonRoyalists.size(); i++) {
                System.out.println(nonRoyalists.get(i));
            }
            System.out.println("Royalism, Declined!");
        }
    }
}
