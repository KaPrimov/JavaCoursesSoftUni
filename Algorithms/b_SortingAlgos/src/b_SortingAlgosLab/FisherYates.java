package b_SortingAlgosLab;

import java.util.concurrent.ThreadLocalRandom;

public class FisherYates {
	public static <T> void shuffle(T[] arr) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			int randIndex = i + ThreadLocalRandom.current().nextInt(0, arr.length - i);
			Helpers.swap(arr, i, randIndex);
		}
	}
}
