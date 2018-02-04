package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class JediMeditation {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        List<String> masters = new LinkedList<>();
        List<String> knights = new LinkedList<>();
        List<String> cutInLine = new LinkedList<>();
        List<String> padawans = new LinkedList<>();

        boolean isYodaPresent = false;

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                if (line[j].charAt(0) == 'm') {
                    masters.add(line[j]);
                } else if (line[j].charAt(0) == 'k') {
                    knights.add(line[j]);
                } else if (line[j].charAt(0) == 'p') {
                    padawans.add(line[j]);
                } else if (line[j].charAt(0) == 's' || line[j].charAt(0) == 't') {
                    cutInLine.add(line[j]);
                } else if (line[j].charAt(0) == 'y') {
                    isYodaPresent = true;
                }
            }
        }
        if (isYodaPresent) {
            System.out.println(("" + masters + knights + cutInLine + padawans).replaceAll("]", " ").replace("[", "").replace(", ", " "));
        } else {
            System.out.println(("" + cutInLine + masters + knights + padawans).replaceAll("]", " ").replace("[", "").replace(", ", " "));
        }

    }
}
