package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JediDreams {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("(?<method>[a-zA-Z]*[A-Z]+[A-Za-z]*)\\s*\\(");
        int n = Integer.parseInt(reader.readLine());

        HashMap<String, List<String>> quantities = new HashMap<>();
        String currentKey = "";
        for (int i = 0; i < n; i++) {
            String line = reader.readLine();
            Matcher matcher = pattern.matcher(line);

            if (line.trim().startsWith("static")) {
                if(matcher.find()) {
                    currentKey = matcher.group("method");
                    quantities.put(currentKey, new ArrayList<>());
                }
            }
            while (matcher.find()) {
                    quantities.get(currentKey).add(matcher.group("method"));
            }
        }

        quantities.entrySet().stream().sorted((a, b) -> {
            int index = Long.compare(b.getValue().size(), a.getValue().size());
            if (index == 0) {
                index = a.getKey().compareTo(b.getKey());
            }
            return index;
        }).forEach(m -> {
            if (m.getValue().isEmpty()) {
                System.out.printf("%s -> None%n", m.getKey());
            } else {
                System.out.printf("%s -> %d -> %s%n",
                        m.getKey(),
                        m.getValue().size(),
                        String.join(", ", m.getValue()
                                .stream()
                                .sorted(String::compareTo)
                                .collect(Collectors.toList())));
            }

        });
    }
}
