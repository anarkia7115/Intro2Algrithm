package SortAlgrithm;
import java.util.Random;

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

	static int[] randomArray(int len) {
		Random r = new Random();

		int[] integers = new int[len];

		for (int i = 0; i < integers.length; i++) {
			integers[i] = r.nextInt();
		}
		
		return integers;
	}
//	public static void main(String[] args){
//		
//		int[] A = randomArray(100000);
//		
//		System.out.println(Arrays.toString(A));
//		
//		final long startTime = System.currentTimeMillis();
//		
//		quickSort(A, 0, A.length - 1);		
//		
//		final long endTime = System.currentTimeMillis();
//
//		System.out.println("Total execution time: " 
//				+ (endTime - startTime) 
//				+ " Sec;");
//		
//		System.out.println(Integer.toString(A[0]) 
//				+ ", " + Integer.toString(A[A.length - 1]));
//	}
}
