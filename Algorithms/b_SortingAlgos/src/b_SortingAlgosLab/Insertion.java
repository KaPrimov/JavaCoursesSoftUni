package b_SortingAlgosLab;

public class Insertion {
	public static <T extends Comparable<T>> void sort(T[] array) {
		for (int i = 0; i < array.length; i++) {
			int previous = i - 1;
			int current = i;
			
			while(true) {
				if (previous < 0 || Helpers.isLess(array[previous], array[current]) ) {
					break;
				}
				
				Helpers.swap(array, current, previous);
				previous--;
				current--;
			}
		}
	}
}
