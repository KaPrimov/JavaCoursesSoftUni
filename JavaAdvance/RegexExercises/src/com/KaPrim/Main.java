package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        // re = "/<a\s+.*href=["|']*([a-z:\/\/\.\?!=_\-&;]+)["|']*.*>(.*)<\/a>/i";
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();
        while (!line.equalsIgnoreCase("end")) {
            builder.append(line + " NL ");
            line = reader.readLine();
        }

        String regex = "(<a) .*?(>)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(builder.toString());

        String result = builder.toString();

        while (matcher.find()) {
            String openingTag = matcher.group(1);
            String closingBracket = matcher.group(2);

            int startIndex = matcher.start();
            int endIndex = matcher.end();

            String firstPart = result.substring(0, startIndex);
            String match = result.substring(startIndex, endIndex);
            String lastPart = result.substring(endIndex);

            match = match.replace(openingTag, "[URL");
            match = match.replace(closingBracket, "]");
            // match = match.replace(closingBracket, "]");

            result = firstPart + match + lastPart;
        }

        // String result = builder.toString().replaceAll("<a", "[URL");
        result = result.replace("</a>", "[URL]");
        result = result.replace(" NL ", "\n");
        System.out.println(result);
    }
}
