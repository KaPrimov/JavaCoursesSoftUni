package b_SortingAlgosLab;

public class Bubble {
	public static <T extends Comparable<T>> void sort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (!Helpers.isLess(array[j], array[j + 1])) {
					Helpers.swap(array, j, j + 1);
				}	
			}
		}
	}
}
