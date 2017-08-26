import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class g_Labyrinth {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int rowAndCols = Integer.parseInt(reader.readLine());

        String[][] matrix = new String[rowAndCols][rowAndCols];
        int[] startCoordinates = new int[2];

        for (int row = 0; row < rowAndCols; row++) {
            String[] line = reader.readLine().split("");
            for (int col = 0; col < rowAndCols; col++) {
                if ("*".equals(line[col])) {
                    startCoordinates[0] = row;
                    startCoordinates[1] = col;
                }
            }
            matrix[row] = line;
        }

        markStartRadius(matrix, startCoordinates[0], startCoordinates[1]);
        boolean madeChanges;
        do {
            madeChanges = false;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (!Objects.equals(matrix[i][j], "*") && !Objects.equals(matrix[i][j], "x") && !Objects.equals(matrix[i][j], "0")) {
                        boolean temp = checkNeighbouringCells(matrix, i, j, Integer.parseInt(matrix[i][j]));
                        if (!madeChanges) madeChanges = temp;
                    }
                }
            }
        } while (madeChanges);

        for (String[] line : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(Objects.equals(line[j], "0") ? "u" : line[j]);
            }
            System.out.println();
        }

    }

    private static void markStartRadius(String[][] matrix, int startRow, int startCol) {
        for (int row = -1; row <= 1; row++) {
            for (int col = -1; col <= 1; col++) {
                if (isInside(matrix, startRow + row, startCol + col)) {
                    if ("0".equals(matrix[startRow + row][startCol + col])) {
                        if (Math.abs(row) != Math.abs(col)) {
                            matrix[startRow + row][startCol + col] = "1";
                        }
                    }
                }
            }
        }
    }

    private static boolean isInside(String[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static boolean checkNeighbouringCells(String[][] matrix, int x, int y, int num) {
        boolean madeChanges = false;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (Math.abs(i) == Math.abs(j)) continue;
                if (isInside(matrix, x + i, y + j) && !Objects.equals(matrix[x + i][y + j], "*") && !Objects.equals(matrix[x + i][y + j], "x")) {
                    if (Integer.parseInt(matrix[x + i][y + j]) == 0 || Integer.parseInt(matrix[x + i][y + j]) > num + 1 || num == 0) {
                        matrix[x + i][y + j] = String.valueOf(num + 1);
                        madeChanges = true;
                    }
                }

            }
        }
        return madeChanges;
    }


}
