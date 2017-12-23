package a_RecursionLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArraySum {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	
		System.out.println(sum(numbers, 0));
	}	

	private static int sum(int[] numbers, int index) {
		
		if (index == numbers.length - 1) {
			return numbers[index];
		}
		
		return numbers[index] + sum(numbers, index + 1);
	}
}
