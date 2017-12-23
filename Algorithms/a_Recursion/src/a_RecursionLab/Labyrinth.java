package a_RecursionLab;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Labyrinth {
	
	static List<Character> path = new LinkedList<>();
	
	static char[][] lab = 
		{
		    {'-', '-', '-', '*', '-', '-', '-'},
		    {'*', '*', '-', '*', '-', '*', '-'},
		    {'-', '-', '-', '-', '-', '-', '-'},
		    {'-', '*', '*', '*', '*', '*', '-'},
		    {'-', '-', '-', '-', '-', '-', 'e'},
		};

	public static void main(String[] args) {
		findPaths(0, 0, 'S');
	}

	private static void findPaths(int row, int col, char direction) {
		if (!isValidCell(row, col)) {
			return;
		}
		
		path.add(direction);
		
		if (lab[row][col] == 'e') {
			printPath();
		} else if (lab[row][col] != 'v') {
			lab[row][col] = 'v';
			findPaths(row, col + 1, 'R');
			findPaths(row + 1, col, 'D');
			findPaths(row, col - 1, 'L');
			findPaths(row - 1, col, 'U');
			lab[row][col] = '-';
		}
		path.remove(path.size() - 1);
		
	}

	private static void printPath() {
		System.out.println(path.stream().map(Object::toString).collect(Collectors.joining("")));
	}

	private static boolean isValidCell(int row, int col) {
			return row >= 0 && row < lab.length && 
					col >= 0 && col < lab[row].length &&
					lab[row][col] != '*';
	}
}
