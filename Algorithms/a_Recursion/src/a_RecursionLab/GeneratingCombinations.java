package a_RecursionLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GeneratingCombinations {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int[] set = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int n = Integer.parseInt(reader.readLine());
		int[] vector = new int[n];
		generateVectors(set, vector, 0, 0);
	}

	private static void generateVectors(int[] set, int[] vector, int index, int border) {
		if (index > vector.length - 1) {
			printVector(vector);
		} else {
			for (int i = border; i < set.length; i++) {
				vector[index] = set[i];
				generateVectors(set, vector, index + 1, i + 1);
			}
		}
	}
	private static void printVector(int[] vector) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < vector.length; i++) {
			sb.append(vector[i]);
		}
		System.out.println(sb);
	}
}
