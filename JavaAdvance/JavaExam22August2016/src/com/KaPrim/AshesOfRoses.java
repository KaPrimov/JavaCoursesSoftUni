package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AshesOfRoses {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Map<String, Long>> regionsMap = new TreeMap<>();
        Map<String, Long> regionsQuantity = new HashMap<>();
        Pattern pattern = Pattern.compile("^Grow <([A-Z][a-z]+)> <([A-Za-z0-9]+)> (\\d+)$");

        while (true) {
            String line = reader.readLine();
            if (line.equals("Icarus, Ignite!")) {
                break;
            }
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String region = matcher.group(1);
                String color = matcher.group(2);
                long amount = Long.parseLong(matcher.group(3));

                if (!regionsMap.containsKey(region)) {
                    regionsMap.put(region, new TreeMap<>());
                    regionsQuantity.put(region, 0L);
                }

                if (!regionsMap.get(region).containsKey(color)) {
                    regionsMap.get(region).put(color, 0L);
                }

                regionsMap.get(region).put(color, regionsMap.get(region).get(color) + amount);
                regionsQuantity.put(region, regionsQuantity.get(region) + amount);
            }
        }

       regionsMap.entrySet().stream()
               .sorted((kv1, kv2) -> regionsQuantity.get(kv2.getKey()).compareTo(regionsQuantity.get(kv1.getKey())))
                .forEach(r -> {
                    System.out.println(r.getKey());
                    r.getValue().entrySet().stream()
                            .sorted(Comparator.comparing(Map.Entry::getValue))
                            .forEach(c -> System.out.printf("*--%s | %d%n", c.getKey(), c.getValue()));
                });
    }
}
