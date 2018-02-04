package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kalin on 2/17/2017.
 */
public class BasicMarkupLanguage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern patternFirst = Pattern.compile("<\\s*(\\w+)\\s+content\\s*=\\s*\"(.+)\"\\s*/>");
        Pattern patternSecond = Pattern.compile("<\\s*(\\w+)\\s+value\\s*=\\s*\"(\\d+)\"\\s+content\\s*=\\s*\"(.+)\"\\s*/>");
        int count = 1;
        while (true) {
            String line = reader.readLine().trim();
            if("<stop/>".equals(line)) {
                break;
            }

            Matcher matcher = patternFirst.matcher(line);
            Matcher repeatMatcher = patternSecond.matcher(line);
            if(matcher.find()) {
                if(matcher.group(1).trim().equals("inverse")) {
                    StringBuilder sb = new StringBuilder();
                    for (int ch = 0; ch < matcher.group(2).length(); ch++) {
                        char letter = matcher.group(2).charAt(ch);
                        if(Character.isLowerCase(letter)) {
                            sb.append(Character.toUpperCase(letter));
                        } else {
                            sb.append(Character.toLowerCase(letter));
                        }
                    }
                    System.out.printf("%d. %s%n", count, sb);
                    count++;
                } else if(matcher.group(1).trim().equals("reverse")) {
                    StringBuilder sb = new StringBuilder();
                    for (int ch = matcher.group(2).length()-1; ch >= 0 ; ch--) {
                        sb.append(matcher.group(2).charAt(ch));
                    }
                    System.out.printf("%d. %s%n", count, sb);
                    count++;
                }
            } else if(repeatMatcher.find()) {
                if(repeatMatcher.group(1).trim().equals("repeat")) {
                    int n = Integer.parseInt(repeatMatcher.group(2));
                    String word = repeatMatcher.group(3);
                    for (int i = 0; i < n; i++) {
                        System.out.printf("%d. %s%n", count, word);
                        count++;
                    }
                }
            }

        }

    }
}
