package a_RecursinExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseArray {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	
		System.out.println(Arrays.stream(reverseArray(numbers, 0))
					.mapToObj(Integer::toString).collect(Collectors.joining(" ")));
	}

	private static int[] reverseArray(int[] array, int index) {
		if (index == array.length / 2) {
			return array;
		}
		
		int temp = array[index];
		array[index] = array[array.length - 1 - index];
		array[array.length - 1 - index] = temp;
				
		return reverseArray(array, index + 1);
	}

}
