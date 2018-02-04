package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftuniDefenceSystem {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Pattern pattern = Pattern.compile("([A-Z][a-z]+).*?([A-Z][a-z]*?[A-Z]).*?([1-9][0-9]*)L");

        BigDecimal totalLiters = BigDecimal.ZERO;

        while (true) {
            String line = reader.readLine();

            if("OK KoftiShans".equals(line)) {
                break;
            }

            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String name = matcher.group(1);
                String boose = matcher.group(2);
                String liters = matcher.group(3);

                System.out.printf("%s brought %s liters of %s!%n", name, liters, boose.toLowerCase());
                totalLiters =  totalLiters.add(new BigDecimal(liters));
            }
        }
        totalLiters = totalLiters.multiply(new BigDecimal("0.001"));
        totalLiters = totalLiters.setScale(3, BigDecimal.ROUND_HALF_EVEN);
        System.out.println(totalLiters +  " softuni liters");

    }
}
