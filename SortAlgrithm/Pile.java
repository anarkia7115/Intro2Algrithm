package SortAlgrithm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import SortAlgrithm.QuickSort;

public class Pile {
	
	static int heapSize = 0;
	
	static int insertFlag = 0;
	
	static int parent(int i) {
		return (i + 1) / 2 - 1;
	}

	static int left(int i) {
		return 2 * (i + 1) - 1;
	}

	static int right(int i) {
		return 2 * (i + 1);
	}

	static void swap(int[] A, int a, int b) {
		int temp = A[a];

		A[a] = A[b];

		A[b] = temp;
	}

	static void max_heapify(int[] A, int i) {

		int l = left(i);

		int r = right(i);

		int largest = 0;

		if (l <= heapSize - 1 && A[l] > A[i]) {
			largest = l;
		}
		else {
			largest = i;
		}

		if (r <= heapSize - 1 && A[r] > A[largest]) {

			largest = r;
		}

		if (largest != i) {
			swap(A, i, largest);

			max_heapify(A, largest);
		}
	}
	
	static void build_max_heap(int[] A) {
		
		heapSize = A.length;
		
		for (int i = A.length / 2 - 1; i >= 0; i--)	{
			
			max_heapify(A, i);
		}
	}

	static void changeArray(int[] A){
		A[0] = A[A.length - 1];
	}
	
	static int[] dropLastElement(int[] A) {
		int lenA = A.length;
		int lenB = lenA - 1;
		
		int[] B = new int[lenB];
		for (int i = 0; i <= lenB - 1; i++) {
			B[i] = A[i];
		}
		
		return B;
	}
	
	static void heapSort(int[] A) {
		
		build_max_heap(A);
		
		for (int i = A.length - 1; i >= 1; i--) {
			
			swap(A, 0, i);
			
			heapSize-- ;

			max_heapify(A, 0);
		}
	}

	static int heapExtractMax(int[] A) {
		if (heapSize < 1) {

			System.out.println("heap underflow");
			System.exit(-1);
		}

		int max = A[0];
		A[0] = A[heapSize - 1];
		heapSize = heapSize - 1;

		max_heapify(A, 0);
		return max;
	}
	
	static void printArray(int[] A) {
		for(int i = 0; i <= heapSize - 2; i++) {
			
			System.out.print(A[i] + ",");
		}
		
		// print last
		System.out.println(A[heapSize - 1]);
	}

	static void heapIncreaseKey(int[] A, int i, int key) {
		if (key < A[i]) {

			System.out.println("new key is smaller than current key");
			System.exit(-1);
		}

		A[i] = key;

		while(i > 1 && A[parent(i)] < A[i]) {

			swap(A, i, parent(i));

			i = parent(i);
		}


	}
	
	static void heapPopMax(int[] A) {
		
		while(heapSize > 1 ){
			
			System.out.println(heapExtractMax(A));
			
			if (heapSize == 5 && insertFlag == 0) {
				
				System.out.println("5 inserted!!");
				
				maxHeapInsert(A, 5);
				
				insertFlag = 1;
			}
			
			printArray(A);
			
			System.out.println("\n");
		}
	}
	
	static void maxHeapInsert(int[] A, int key) {
		
		heapSize = heapSize + 1;
		A[heapSize - 1] = -999;
		
		heapIncreaseKey(A, heapSize - 1, key);
	}
	
	static int[] randomArray(int len) {
		Random r = new Random();

		int[] integers = new int[len];

		for (int i = 0; i < integers.length; i++) {
			integers[i] = r.nextInt();
		}
		
		return integers;
	}
	
	static long testHeapSort(int[] A) {
		// start
		long startTime = System.currentTimeMillis();
		
		//System.out.println(Arrays.toString(A));
		
		heapSort(A);
		
		// end
		long endTime = System.currentTimeMillis();
		
		System.out.println(Integer.toString(A[0]) 
				+ ", " + Integer.toString(A[A.length - 1]));

		// print execution time
		System.out.println("heapSort Total execution time: " 
				+ (endTime - startTime) 
				+ " MillSec;");
		
		return (endTime - startTime);
	}
	
	static long testQuickSort(int[] A) {
		// start
		long startTime = System.currentTimeMillis();
		
		//System.out.println(Arrays.toString(A));
		
		QuickSort.quickSort(A, 0, A.length - 1);
		
		// end
		long endTime = System.currentTimeMillis();
		
		System.out.println(Integer.toString(A[0]) 
				+ ", " + Integer.toString(A[A.length - 1]));

		// print execution time
		System.out.println("QuickSort Total execution time: " 
				+ (endTime - startTime) 
				+ " MillSec;");
		
		return (endTime - startTime);
	}
	
	static double heapQuickRatio(int i) {
		
		int[] A = randomArray(i);		
		//System.out.println(Arrays.toString(A));
		
		int[] A1 = new int[A.length];
		int[] A2 = new int[A.length];
		
		System.arraycopy(A, 0, A1, 0, A.length);
		System.arraycopy(A, 0, A2, 0, A.length);
		
		long ht = testHeapSort(A1);
		long qt = testQuickSort(A2);
		
		return (ht * 1.0 / qt);
	}
	
	static double[] ratioSeq(int i){
		
		double[] dr = new double[i];
		
		for (int j = 0; j < i; j++) {
			
			dr[j] = heapQuickRatio(j);
		}
		
		return dr;
	}
	
	static void writeToFile(String s, String path) throws IOException {
		
		File f = new File(path);
		
		f.createNewFile();

		FileWriter fw = new FileWriter(f.getAbsoluteFile());

		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(s);
		
		bw.close();
		
		System.out.println("Done");
	} 
	
	static void genSortRatio(int numOfRows, String path) {
		
		double[] rs = new double[numOfRows];
		
		rs = ratioSeq(numOfRows);
		
		for (int i = 0; i <= rs.length; i++) {
			
			
		}
	}

	public static void main(String[] args) {
		
		String s = "test2";
		
		String path = "D:\\data\\sortRatio";
		
		try {
			writeToFile(s, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



