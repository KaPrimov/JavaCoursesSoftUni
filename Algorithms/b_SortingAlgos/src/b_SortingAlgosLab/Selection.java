package b_SortingAlgosLab;

public class Selection {
	public static <T extends Comparable<T>> void sort(T[] array) {
		for (int index = 0; index < array.length; index++) {
			int min = index;
			for (int current = index + 1; current < array.length; current++) {
				if (Helpers.isLess(array[current], array[min])) {
					min = current;
				}
			}
			Helpers.swap(array, min, index);
		}
	}
}
