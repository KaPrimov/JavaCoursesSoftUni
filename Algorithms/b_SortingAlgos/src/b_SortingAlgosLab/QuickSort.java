 package b_SortingAlgosLab;

public class QuickSort<T extends Comparable<T>> {
	
	public void sort(T[] arr) {
		FisherYates.shuffle(arr);
		sort(arr, 0, arr.length - 1);		
	}
	
	private void sort(T[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		
		int p = partition(arr, left, right);
		sort(arr, left, p - 1);
		sort(arr, p + 1, right);
	}

	private int partition(T[] arr, int left, int right) {
		if (left >= right) {
			return left;
		}
		
		int i = left;
		int j = right + 1;
		while(true) {
			while(Helpers.isLess(arr[++i], arr[left])) {
				if (i == right) break;
			}
			
			while(Helpers.isLess(arr[left], arr[--j])) {
				if (j == left) break;
			}
			
			if (i >= j) break;
			Helpers.swap(arr, i, j);
		}
		Helpers.swap(arr, left, j);
		return j;
	}
}
