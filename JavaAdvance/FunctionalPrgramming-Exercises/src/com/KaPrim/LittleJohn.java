package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LittleJohn {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern patternArrow = Pattern.compile("(?<small>>----->)|(?<medium>>>----->)|(?<large>>>>----->>)");
        int small = 0;
        int medium = 0;
        int large = 0;

        for (int i = 0; i < 4; i++) {
            String input = reader.readLine();
            Matcher matcher = patternArrow.matcher(input);
            while (matcher.find()) {
                if(matcher.group("large") != null) {
                    large++;
                } else if (matcher.group("medium") != null) {
                    medium++;
                } else {
                    small++;
                }
            }
        }
        String num = "" + small + medium + large;
        String firstBinary = Integer.toBinaryString(Integer.parseInt(num));
        String reversedBinary = new StringBuilder(firstBinary).reverse().toString();
        System.out.println(Integer.parseInt(firstBinary + reversedBinary, 2));
    }
}
