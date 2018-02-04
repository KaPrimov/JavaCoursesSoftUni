package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class CubicAssault {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, TreeMap<String, Long>> regions = new TreeMap<>();

        while (true) {
            String input = reader.readLine();
            if ("Count em all".equals(input)) {
                break;
            }

            String[] tokens = input.split(" -> ");
            String region = tokens[0];
            String typeOfMeteor = tokens[1];
            long quantity = Long.parseLong(tokens[2]);
            if (!regions.containsKey(region)) {
                regions.put(region, new TreeMap<>());
                regions.get(region).put("Red", 0L);
                regions.get(region).put("Black", 0L);
                regions.get(region).put("Green", 0L);
            }

            regions.get(region).put(typeOfMeteor, regions.get(region).get(typeOfMeteor) + quantity);

            if("Green".equals(typeOfMeteor)) {
                long quantityGreen = regions.get(region).get(typeOfMeteor);
                if(quantity >= 1_000_000) {
                    regions.get(region).put("Green", quantityGreen % 1_000_000);
                    regions.get(region).put("Red", regions.get(region).get("Red") + (quantityGreen / 1_000_000));
                }
            }

            long quantityRed = regions.get(region).get("Red");
            if ( quantityRed >= 1_000_000) {
                regions.get(region).put("Red", quantityRed % 1_000_000);
                regions.get(region).put("Black", regions.get(region).get("Black") + (quantityRed / 1_000_000));
            }
        }

        regions.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int index = Long.compare(
                            b.getValue().get("Black"),
                            a.getValue().get("Black"));
                    if(index == 0) {
                        index = Integer.compare(
                                a.getKey().length(),
                                b.getKey().length());
                    }
                    if(index == 0) {
                        index = a.getKey().compareTo(b.getKey());
                    }
                    return index;
                })
                .forEach(pair -> {
                    System.out.println(pair.getKey());
                    pair.getValue()
                            .entrySet()
                            .stream()
                            .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                            .forEach(meteor -> {
                                System.out.printf("-> %s : %d%n", meteor.getKey(), meteor.getValue());
                            });
                });
    }
}
