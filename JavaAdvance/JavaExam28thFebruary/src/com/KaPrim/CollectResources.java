package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CollectResources {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(reader.readLine());
        Long maxResource = 0L;
        for (int i = 0; i < n; i++) {
            String[] coordinates = reader.readLine().split(" ");
            int start = Integer.parseInt(coordinates[0]);
            int step = Integer.parseInt(coordinates[1]);
            String[] workingLine = input.clone();
            long tempCount = 0L;
            for (int j = start; ; j= (j+step) % workingLine.length) {
                String[] resource = workingLine[j].split("_");
                if(workingLine[j].contains("!")) {
                    break;
                }

                if (resource[0].equals("stone") || resource[0].equals("gold") || resource[0].equals("wood") || resource[0].equals("food") ) {
                    if(resource.length == 1) {
                        workingLine[j] = "!";
                        tempCount++;
                    } else if(resource.length == 2) {
                        int quantity = Integer.parseInt(resource[1]);
                        tempCount+= quantity;
                        workingLine[j] = "!";
                    }
                }

            }
            if(tempCount > maxResource) {
                maxResource = tempCount;
            }
        }
        System.out.println(maxResource);
    }
}
