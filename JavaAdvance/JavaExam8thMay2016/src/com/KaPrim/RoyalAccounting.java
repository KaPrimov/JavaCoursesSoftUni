package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RoyalAccounting {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("^([a-zA-Z]+)\\;(\\-?\\d+)\\;(\\-?\\d+\\.?\\d*)\\;([a-zA-Z]+)$");

        List<String> employees = new ArrayList<>();

        Map<String, LinkedHashMap<String, Double[]>> employeeByTeamData = new LinkedHashMap<>();

        while (true) {
            String line = reader.readLine();
            if (line.equals("Pishi kuf i da si hodim")) {
                break;
            }

            Matcher matcher = pattern.matcher(line);

            if(matcher.find()) {
                String name = matcher.group(1);
                double workHours = Double.parseDouble(matcher.group(2));
                double dailyPayment = Double.parseDouble(matcher.group(3));
                String team = matcher.group(4);

                if(!employees.contains(name)) {
                    if(!employeeByTeamData.containsKey(team)) {
                        employeeByTeamData.put(team, new LinkedHashMap<>());
                    }
                    employeeByTeamData.get(team).put(name, new Double[]{workHours, dailyPayment});
                    employees.add(name);
                }
            }
        }

        employeeByTeamData
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    Double first = a
                            .getValue()
                            .values()
                            .stream()
                            .collect(Collectors.summingDouble(e -> ((e[0] * e[1])/24)*30));
                    Double second = b
                            .getValue()
                            .values()
                            .stream()
                            .collect(Collectors.summingDouble(e -> ((e[0] * e[1])/24)*30));

                    return second.compareTo(first);
                })
                .forEach(t -> {
                    System.out.println("Team - " + t.getKey());

                    t.getValue()
                            .entrySet()
                            .stream()
                            .sorted((a, b) -> {

                                int index = b.getValue()[0].compareTo(a.getValue()[0]);

                                if(index == 0) {
                                    Double firstSum = (a.getValue()[0] * a.getValue()[1]) / 24;
                                    Double secondtSum = (b.getValue()[0] * b.getValue()[1]) / 24;
                                    index = secondtSum.compareTo(firstSum);
                                }

                                if(index == 0) {
                                    index = a.getKey().compareTo(b.getKey());
                                }

                               return index;
                            })
                            .forEach(e -> {
                                System.out.printf("$$$%s - %.0f - %.6f%n", e.getKey(), e.getValue()[0], ((e.getValue()[0] * e.getValue()[1]) / 24));
                            });
                });
    }
}
