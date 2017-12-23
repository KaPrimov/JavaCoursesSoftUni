package a_RecursionLab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(reader.readLine());
		
		System.out.println(factorial(n));

	}

	private static long factorial(long n) {
		if (n < 2) {
			return 1;
		}
		return n * factorial(n - 1);
	}

}
