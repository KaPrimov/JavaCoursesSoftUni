package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ascent {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> codes = new HashMap<>();

        Pattern pattern = Pattern.compile("([,_])([A-Za-z]+)(\\d)");

        while (true) {
            String line = reader.readLine();

            if (line.equals("Ascend")) {
                break;
            }

            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                if(codes.containsKey(matcher.group())) {
                    String check = codes.get(matcher.group());
                    line = line.replaceAll(matcher.group(), check);
                    while (line.contains(check)) {
                        Matcher inside = pattern.matcher(line);
                        if(inside.find()) {
                            check = inside.group();
                            break;
                        }
                    }

                    String first = ""+check.charAt(0);
                    String num = ""+check.charAt(check.length()-1);
                    String word = check.substring(1, check.length()-1);
                    String decoded = decodeString(first, word, num);
                    line = line.replaceAll(check, decoded);
                } else {
                    String decodedString = decodeString(matcher.group(1), matcher.group(2), matcher.group(3));
                    codes.put(matcher.group(), decodedString);
                    line = line.replaceAll(matcher.group(), decodedString);
                }
            }
            System.out.println(line);


        }
    }

    private static String decodeString(String begining, String word, String num) {
        StringBuilder answer = new StringBuilder();
        if (begining.equals(",")) {
            for (int i = 0; i < word.length(); i++) {
                answer.append((char) (((int) word.charAt(i)) + Integer.parseInt(num)));
            }
        } else if (begining.equals("_")) {
            for (int i = 0; i < word.length(); i++) {
                answer.append((char) (((int) word.charAt(i)) - Integer.parseInt(num)));
            }
        }
        return answer.toString();
    }
}
