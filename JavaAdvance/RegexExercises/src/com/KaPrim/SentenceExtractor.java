package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceExtractor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String keyWord = reader.readLine();
        String regex = "\\b[A-Z][^!?.]*?\\b" + keyWord +"\\b[^!.?]*?[.?!]";
        Pattern pattern = Pattern.compile(regex);
        String text = reader.readLine();

        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
