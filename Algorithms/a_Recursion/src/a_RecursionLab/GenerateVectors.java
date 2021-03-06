package a_RecursionLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GenerateVectors {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[] vector = new int[n];
		generateVectors(0, vector);
	}

	private static void generateVectors(int index, int[] vector) {
		if (index > vector.length - 1) {
			printVector(vector);
		} else {
			vector[index] = 0;
			generateVectors(index + 1, vector);

			vector[index] = 1;
			generateVectors(index + 1, vector);
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
