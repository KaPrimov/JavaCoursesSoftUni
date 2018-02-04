package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CubicsMessage {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("^(?<before>\\d+)(?<message>[a-zA-Z]+)(?<after>[^a-zA-Z]*)$");
        while (true) {
            String message = reader.readLine();

            Matcher matcher = pattern.matcher(message);
            if("Over!".equals(message)) {
                break;
            }
            int length = Integer.parseInt(reader.readLine());
            if(matcher.find()) {
                if(matcher.group("message").length() == length) {
                    String digitsAfter = matcher.group("after").replaceAll("\\D", "");
                    StringBuilder answer = new StringBuilder();
                    answer.append(matcher.group("message")).append(" == ");
                    for (int i = 0; i < matcher.group("before").length(); i++) {
                        int index = Character.getNumericValue(matcher.group("before").charAt(i));
                        if (index < length) {
                            answer.append(matcher.group("message").charAt(index));
                        } else {
                            answer.append(" ");
                        }
                    }

                    for (int i = 0; i < digitsAfter.length(); i++) {
                        int index = Character.getNumericValue(digitsAfter.charAt(i));
                        if (index < length) {
                            answer.append(matcher.group("message").charAt(index));
                        } else {
                            answer.append(" ");
                        }
                    }
                    System.out.println(answer);
                }
            }
        }
    }
}
