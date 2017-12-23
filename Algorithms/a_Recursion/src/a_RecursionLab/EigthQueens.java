package a_RecursionLab;

import java.util.HashSet;
import java.util.Set;

public class EigthQueens {
	
	static boolean[][] board = new boolean[8][8];
	
	static Set<Integer> attackedRows = new HashSet<>();
	static Set<Integer> attackedCols = new HashSet<>();
	static Set<Integer> attackedLeftDiagonals = new HashSet<>();
	static Set<Integer> attackedRightDiagonals = new HashSet<>();
	
	
	public static void main(String[] args) {
		placeQueen(0);
	}

	private static void placeQueen(int row) {
		if (row == 8) {
			print();
		} else {
			for (int col = 0; col < 8; col++) {
				if (canPlaceQueen(row, col)) {
					mark(row, col);
					placeQueen(row + 1);
					unmark(row, col);
				}
			}
		}
		
	}

	private static void unmark(int row, int col) {
		board[row][col] = false;
		attackedRows.remove(row);
		attackedCols.remove(col);
		attackedLeftDiagonals.remove(row + col);
		attackedRightDiagonals.remove(row - col);
	
	}

	private static void mark(int row, int col) {
		board[row][col] = true;
		attackedRows.add(row);
		attackedCols.add(col);
		attackedLeftDiagonals.add(row + col);
		attackedRightDiagonals.add(row - col);
	}

	private static boolean canPlaceQueen(int row, int col) {
		return !attackedRows.contains(row) && !attackedCols.contains(col) &&
				!attackedLeftDiagonals.contains(row + col) && !attackedRightDiagonals.contains(row - col);
	}

	private static void print() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col]) {
					System.out.print("* ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
