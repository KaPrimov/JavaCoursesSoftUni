package com.KaPrim;

import java.util.ArrayList;
import java.util.Scanner;

public class PlusRemove {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        ArrayList<Point> points = new ArrayList<>();

        String[] command = scanner.nextLine().split("");

        for (int row = 0; !command[0].equals("E") && !command[1].equals("N") && !command[2].equals("D"); row++) {
            matrix.add(row, new ArrayList<>());
            for (String s : command) {
                matrix.get(row).add(s);
            }
            command = scanner.nextLine().split("");
        }

        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {

                try {
                    String currentElement = matrix.get(row).get(col).toLowerCase();
                    if (
                            !"".equals(currentElement) &&
                            currentElement.equals(matrix.get(row + 1).get(col).toLowerCase()) &&
                            currentElement.equals(matrix.get(row + 2).get(col).toLowerCase()) &&
                            currentElement.equals(matrix.get(row + 1).get(col+1).toLowerCase()) &&
                            currentElement.equals(matrix.get(row + 1).get(col-1).toLowerCase())
                        ) {
                        points.add(new Point(row, col));
                        points.add(new Point(row + 1, col));
                        points.add(new Point(row + 2, col));
                        points.add(new Point(row + 1, col + 1));
                        points.add(new Point(row+1, col-1));


                    }
                } catch (Exception e) {
                }
            }
        }

        for (int row = 0; row < matrix.size(); row++) {
            for (int col = 0; col < matrix.get(row).size(); col++) {
                if (!points.contains(new Point(row, col))) {
                    System.out.print(matrix.get(row).get(col));
                }
            }
            System.out.println();
        }
    }
    private static class Point{
        private long row;
        private long col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (row != point.row) return false;
            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = (int) row;
            result = 31 * result + (int)col;
            return result;
        }
    }
}
