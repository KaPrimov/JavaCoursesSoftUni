package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUnit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, LinkedHashMap<String, HashSet<String>>> map = new LinkedHashMap<>();
        Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");

        String line  = reader.readLine();

        while (!line.equals("It's testing time!")) {
            Matcher matcher = pattern.matcher(line);

            if(matcher.find()) {
                String className = matcher.group(1);
                String methodName = matcher.group(2);
                String unitTestName = matcher.group(3);

                if(!map.containsKey(className)) {
                    map.put(className, new LinkedHashMap<>());
                }

                if(!map.get(className).containsKey(methodName)) {
                    map.get(className).put(methodName, new HashSet<>());
                }

                map.get(className).get(methodName).add(unitTestName);

            }
            line = reader.readLine();
        }

        map
                .entrySet()
                .stream()
                .sorted((a, b) -> {
                    Integer sizeA = getUnitTestSize(a.getValue().values());
                    Integer sizeB = getUnitTestSize(b.getValue().values());
                    int index = Integer.compare(sizeB, sizeA);

                    if(index == 0) {
                        index = Integer.compare(a.getValue().size(), b.getValue().size());
                    }

                    if(index == 0) {
                        index = a.getKey().compareTo(b.getKey());
                    }

                    return index;

                })
                .forEach(c -> {
                    System.out.println(c.getKey() + ":");
                    c.getValue()
                            .entrySet()
                            .stream()
                            .sorted((m1, m2) -> {
                                int index = Integer.compare(m2.getValue().size(), m1.getValue().size());
                                if(index == 0) {
                                    index = m1.getKey().compareTo(m2.getKey());
                                }

                                return index;
                            })
                            .forEach(m -> {
                                System.out.printf("##%s%n", m.getKey());
                                m.getValue()
                                        .stream()
                                        .sorted((u1, u2) -> {
                                            int index = Integer.compare(u1.length(), u2.length());
                                            if(index == 0) {
                                                index = u1.compareTo(u2);
                                            }
                                            return index;
                                        })
                                        .forEach(u -> System.out.printf("####%s%n", u));
                            });
                });

    }

    private static int getUnitTestSize(Collection<HashSet<String>> values) {
        int size = 0;
        for (HashSet<String> value : values) {
            for (String s : value) {
                size++;
            }
        }
        return size;
    }
}
