package com.KaPrim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Crossfire {
    private static List<List<Integer>> matrix = new ArrayList<>();
    private static int rows;
    private static int cols;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] dimensions = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rows = dimensions[0];
        cols = dimensions[1];

        matrix = new ArrayList<>();
        int counter = 1;
        for (int row = 0; row < rows; row++) {
            matrix.add(new ArrayList<>());
            for (int col = 0; col < cols; col++) {
                matrix.get(row).add(counter);
                counter++;
            }
        }

        while (true) {
            String[] line = reader.readLine().split(" ");
            if(line[0].equals("Nuke")) {
                break;
            }

            int blowRow = Integer.parseInt(line[0]);
            int blowCol = Integer.parseInt(line[1]);
            int blowRadius = Integer.parseInt(line[2]);

            for (int currentRow = blowRow - blowRadius; currentRow <= blowRow+blowRadius ; currentRow++) {
                if(isIMatrix(currentRow, blowCol)) {
                    matrix.get(currentRow).set(blowCol, -1);
                }
            }

            for (int currentCol = blowCol - blowRadius; currentCol <= blowCol+blowRadius ; currentCol++) {
                if(isIMatrix(blowRow, currentCol)) {
                    matrix.get(blowRow).set(currentCol, -1);
                }
            }

            rearrangeMatrix();
        }
        for (List<Integer> integers : matrix) {
            for (Integer integer : integers) {
                System.out.printf("%d ", integer);
            }
            System.out.println();
        }

    }

    private static void rearrangeMatrix() {
        for (int row = 0; row < matrix.size(); row++) {
            matrix.get(row).removeAll(Arrays.asList((new Integer[] {-1})));
        }
        matrix.removeAll(Arrays.asList(new ArrayList<Integer>()));
    }

    private static boolean isIMatrix(int row, int col) {
        return row >= 0 && row < matrix.size() && col >= 0 && col < matrix.get(row).size();
    }
}
