package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CriticalBreakpoint {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Long[]> lines = new LinkedList<>();

        while (true) {
            String[] input = reader.readLine().split(" ");
            if(input[0].equals("Break")) {
                break;
            }

            long x1 = Long.parseLong(input[0]);
            long y1 = Long.parseLong(input[1]);
            long x2 = Long.parseLong(input[2]);
            long y2 = Long.parseLong(input[3]);

            lines.add(new Long[] {x1, y1, x2, y2});
        }

        Long criticalRatio = 0L;
        boolean weHaveBreakpoint = true;
        for (int i = 0; i < lines.size(); i++) {
            Long tempRatio = Math.abs((lines.get(i)[3] + lines.get(i)[2]) - (lines.get(i)[0] + lines.get(i)[1]));

            if(criticalRatio == 0) {
                criticalRatio = tempRatio;
            }

            if(!tempRatio.equals(criticalRatio) && !tempRatio.equals(0L)) {
                weHaveBreakpoint = false;
                break;
            }
        }

        if(weHaveBreakpoint) {
            for (int i = 0; i < lines.size(); i++) {
                List<Long> list = Arrays.asList(lines.get(i));
                System.out.printf("Line: %s%n", list);
            }
            BigInteger answer = new BigInteger(criticalRatio.toString());
            answer = answer.pow(lines.size());
            answer = answer.remainder(new BigInteger(Integer.valueOf(lines.size()).toString()));

            System.out.printf("Critical Breakpoint: %d", answer);
        } else {
            System.out.println("Critical breakpoint does not exist.");
        }
    }
}
