package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class CubicsRube {
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(reader.readLine());
        long cubesTotal = n * n * n;
        BigInteger totalDamage = new BigInteger("0");
        while (true) {
            String[] input = reader.readLine().split(" ");
            if("Analyze".equals(input[0])) {
                break;
            }
            int[] coordinates = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
            boolean isInDimension = checkDimensions(coordinates[0], coordinates[1], coordinates[2]);
            if (isInDimension && coordinates[3] > 0) {
                totalDamage = totalDamage.add(BigInteger.valueOf(coordinates[3])) ;
                cubesTotal--;
            }
        }

        System.out.println(totalDamage);
        System.out.println(cubesTotal);
    }

    private static boolean checkDimensions(int coordinate, int coordinate1, int coordinate2) {
        return coordinate < n && coordinate1<n && coordinate2 < n && coordinate >= 0 && coordinate1 >= 0 && coordinate2 >= 0;
    }
}
