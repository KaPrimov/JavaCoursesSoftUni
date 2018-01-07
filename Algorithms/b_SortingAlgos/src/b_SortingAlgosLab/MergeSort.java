package b_SortingAlgosLab;

public class MergeSort<T extends Comparable<T>> {
	
	private T[] aux;
	
	public void sort(T[] arr) {
		this.aux = (T[])(new Comparable[arr.length]);
		this.sort(arr, 0, arr.length - 1);
	}
	
	private void merge(T[] arr, int left, int mid, int right) {
		if (Helpers.isLess(arr[mid], arr[mid + 1])) {
			return;
		}
		
		for (int index = left; index < right + 1; index++) {
			this.aux[index] = arr[index];
		}
		
		int i = left;
		int j = mid + 1;
		
		for (int k = left; k <= right; k++) {
			if (i > mid) {
				arr[k] = this.aux[j++];
			} else if (j > right) {
				arr[k] = this.aux[i++];
			} else if (Helpers.isLess(aux[i], aux[j])) {
				arr[k] = aux[i++];
			} else {
				arr[k] = aux[j++];
			}
		}
	}
	
	private void sort(T[] arr, int left, int right) {
		if (left >= right) {
			return;
		}
		int middle = left + (right - left) / 2;
		sort(arr, left, middle);
		sort(arr, middle + 1, right);
		merge(arr, left, middle, right);
	}
}
