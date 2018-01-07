package b_SortingAlgosLab;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Integer[] numbers = new Integer[] {12, 15, 3, 5, 90, 23, 1};
		Integer[] numbersOrdered = new Integer[] {1, 2, 3, 4, 5, 6, 7};
		//Selection.sort(numbers);
		//Bubble.sort(numbers);
//		Insertion.sort(numbers);
//		System.out.println(Arrays.toString(numbers));
//		System.out.println(Helpers.isSorted(numbers));
//		FisherYates.shuffle(numbersOrdered);
		
//		MergeSort<Integer> mergeSorter = new MergeSort<>();
//		mergeSorter.sort(numbers);
		
		QuickSort<Integer> quickSort = new QuickSort<>();
		quickSort.sort(numbers);
		System.out.println(Arrays.toString(numbers));
	}

}
