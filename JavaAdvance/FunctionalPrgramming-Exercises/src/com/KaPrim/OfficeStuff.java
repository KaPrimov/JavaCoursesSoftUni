package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class OfficeStuff {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<String, LinkedHashMap<String, Long>> companies = new TreeMap<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" - ");
            String company = tokens[0].substring(1, tokens[0].length());
            Long amount = Long.parseLong(tokens[1]);
            String product = tokens[2].substring(0, tokens[2].length()-1);
            if(!companies.containsKey(company)) {
                companies.put(company, new LinkedHashMap<>());
            }
            if(!companies.get(company).containsKey(product)) {
                companies.get(company).put(product, 0L);
            }

            companies.get(company).put(product, companies.get(company).get(product) + amount);
        }


        companies.entrySet().forEach(kv -> {
            StringBuilder answer = new StringBuilder();
            answer.append(String.format("%s: ", kv.getKey()));
            kv.getValue().entrySet().forEach(p -> {
                answer.append(String.format("%s-%d, ", p.getKey(), p.getValue()));
            });
            System.out.println(answer.substring(0, answer.length()-2));
        });
    }
}
