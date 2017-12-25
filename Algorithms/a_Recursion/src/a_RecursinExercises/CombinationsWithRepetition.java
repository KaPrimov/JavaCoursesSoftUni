package a_RecursinExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CombinationsWithRepetition {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(reader.readLine());
		int k = Integer.parseInt(reader.readLine());
		
		generateLoops(new int[k], 0, n);
	}

	private static void generateLoops(int[] combinations, int index, int n) {
		if (index == combinations.length) {
			System.out.println(Arrays.stream(combinations).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
			return;
		}
		int i = index - 1 >= 0 ? combinations[index - 1] : 1;
		for (; i <= n; i++) {
			combinations[index] = i;
			generateLoops(combinations, index + 1, n);
		}
	}
}
