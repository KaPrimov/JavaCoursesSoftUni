package b_SortingAlgosLab;

public class Helpers {
	public static <T> void swap(T[] array, int firstIndex, int secondIndex) {
		T temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}
	
	public static <T extends Comparable<T>> boolean isLess(T first, T second) {
		return first.compareTo(second) < 0;
	}
	
	public static <T extends Comparable<T>> boolean isSorted(T[] array) {
		
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1].compareTo(array[i]) > 0) {
				return false;
			}
		}
		
		return true;
	}
}
