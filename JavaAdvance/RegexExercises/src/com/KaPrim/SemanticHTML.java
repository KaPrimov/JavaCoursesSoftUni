package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SemanticHTML {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern openTagPattern = Pattern.compile("<(div).+((id|class)\\s*?=\\s*?\"(\\w+)\").*>");
        Pattern closingTagPattern = Pattern.compile("<\\/(div)>\\s*<!--\\s*([A-Za-z]*)\\s*-->");
        String[] tags = {"main", "header", "nav", "article", "section", "aside", "footer"};

        String line = reader.readLine();
        while (!line.equals("END")) {

            Matcher openTagMatcher = openTagPattern.matcher(line);
            Matcher closeTagMatcher = closingTagPattern.matcher(line);

            if (openTagMatcher.find() && Arrays.asList(tags).contains(openTagMatcher.group(4))) {
                String result = openTagMatcher.group()
                        .replace(openTagMatcher.group(1), openTagMatcher.group(4))
                        .replace(openTagMatcher.group(2), "");
                System.out.println(result.replaceAll("\\s+>", ">").replaceAll("\\s+", " "));
            } else if (closeTagMatcher.find() && Arrays.asList(tags).contains(closeTagMatcher.group(2))) {
                String text = "</" + closeTagMatcher.group(2) + ">";
                System.out.println(text);
            } else {
                System.out.println(line);

            }
            line = reader.readLine();
        }

    }
}
