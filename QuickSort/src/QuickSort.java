import java.util.Arrays;

public class QuickSort {
	
	static void quickSort(int[] A, int p, int r) {
		if(p < r) {

			int q = partition(A, p, r);

			quickSort(A, p, q - 1);

			quickSort(A, q + 1, r);
		}
	}

	static void swap(int[] A, int a, int b) {
		
		int temp = A[a];

		A[a] = A[b];

		A[b] = temp;
	}
	
	static int partition(int[] A, int p, int r) {

		int x = A[r];

		int i = p - 1;

		for (int j = p; j <= r - 1; j++) {

			if (A[j] <= x) {

				i = i + 1;

				swap(A, i, j);
			}
		}

		swap(A, i + 1, r);

		return i + 1;
	}

	public static void main(String[] args){
		
		int[] A = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
		
		System.out.println(Arrays.toString(A));
		quickSort(A, 0, 8);
		
		System.out.println(Arrays.toString(A));
	}
}
