package com.KaPrim;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PopulationCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, LinkedHashMap<String, Long>> citiesByCountry = new LinkedHashMap<>();
        Map<String, Long>  countriesPopulation = new LinkedHashMap<>();

        while (true) {
            String[] command = scanner.nextLine().split("\\|");
            if(command[0].equals("report")) {
                break;
            }
            String city = command[0];
            String country = command[1];
            long population = Long.parseLong(command[2]);

            if(!countriesPopulation.containsKey(country)) {
                    citiesByCountry.put(country, new LinkedHashMap<>());
                    countriesPopulation.put(country, 0L);
            }

            if(!citiesByCountry.get(country).containsKey(city)) {
                citiesByCountry.get(country).put(city, 0L);
            }

            countriesPopulation.put(country, countriesPopulation.get(country) + population);
            citiesByCountry.get(country).put(city, citiesByCountry.get(country).get(city) + population);
        }
        countriesPopulation.entrySet().stream().sorted((a, b) -> b.getValue().compareTo(a.getValue())).forEach(country -> {
            System.out.printf("%s (total population: %d)%n", country.getKey(), country.getValue());
            citiesByCountry.get(country.getKey()).entrySet().stream().sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
            .forEach(city -> {
                System.out.printf("=>%s: %d%n", city.getKey(), city.getValue());
            });
        });
    }
}
