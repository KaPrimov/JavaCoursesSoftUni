package a_RecursinExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NestedLoops {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(reader.readLine());
	
		generateLoops(new int[n], 0);
	}

	private static void generateLoops(int[] combinations, int index) {
		if (index == combinations.length) {
			System.out.println(Arrays.stream(combinations).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			return;
		}
		for (int i = 1; i <= combinations.length; i++) {
			combinations[index] = i;
			generateLoops(combinations, index + 1);
		}
	}
}
