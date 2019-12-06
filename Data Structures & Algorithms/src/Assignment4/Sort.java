
package Assignment4;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.*;
/**
 * Template code for assignment 4, CS146
 * @author andreopo
 *
 */
public class Sort {
	/**
	 * Build a random array
	 * @param rand a Random object
	 * @param LENGTH The range of the integers in the array 
	 *             will be from 0 to LENGTH-1
	 * @return
	 */
	private static int[] build_random_array(Random rand, int LENGTH) {
		int[] array = new int[LENGTH];
		//set index 0 to 0 for consistency with CLRS, where sorting starts at index 1
		array[0] = 0; 
		for (int i = 1; i < LENGTH; i++) {
			// Generate random integers in range 0 to 999 
			int rand_int = rand.nextInt(LENGTH); 
			array[i] = rand_int;
		}
		return array;
	}
	/**
	 * Assert the array is sorted correctly
	 * @param array_unsorted The original unsorted array
	 * @param array_sorted The sorted array
	 */
	public static void assert_array_sorted(int[] array_unsorted, int[] 
			array_sorted) {
		int a_sum = array_unsorted[0];
		int b_sum = array_sorted[0];
		for (int i = 1; i < array_unsorted.length; i++) {
			a_sum += array_unsorted[i];
		}
		for (int i = 1; i < array_sorted.length; i++) {
			b_sum += array_sorted[i];
			assertTrue(array_sorted[i-1] <= array_sorted[i]);
		}
		assertEquals(a_sum, b_sum);
	}
	public static void insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int j = i - 1;
			int temp;
			
			while ((array[j] > array[i]) && (j >= 0)) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				
				i = j;
				j--;
			}
			
		}
		
	}
	
	//simple selection sort
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int min = i;
			
			for (int j = i + 1; j < array.length; j++) {
				if (array[j] < array[min]) min = j;
 			}
			
			int temp = array[i];
			array[i] = array[min]; 
			array[min] = temp;
			
		}
		
	}
	
  
	
	public static int getLeft(int i) {
		return (2*i) + 1;
	}
	public static int getRight(int i) {
		return (2*i) + 2;
	}
	

    //Function to heapify the node at position i, up to the position n 
	public static void maxHeapify(int i, int heapsize, int[] array) {
		int l = getLeft(i);
		int r = getRight(i);

		int largest = i;
		if (l < heapsize && array[l] > array[largest]) {
			largest = l;
		}
		
		if (r < heapsize && array[r] > array[largest]) {
			largest = r;
		} 
		
		if (largest != i) {
			int temp = array[i];
			array[i] = array[largest];
			array[largest] = temp;
			
			maxHeapify(largest, heapsize, array);
		}
	}

    // from assgn 3
    public static void buildMaxHeap(int[] array) {
    	int heapsize = array.length;
		
		for (int i = (heapsize / 2) - 1; i >= 0; i--) {
			maxHeapify(i, heapsize, array);
		}
    }
 // from assgn 3
	public static void heapSort(int[] array) {
		int heapsize = array.length;
		for (int i = heapsize - 1; i >= 0; i--) {
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;	
		
			maxHeapify(0, i, array);
		}
		
		
	}
	
	//base call
	public static void mergeSort(int[] array) {
		
	sortMerge(array, 0, array.length - 1);	
		
	}
	
	//divides
	public static void sortMerge(int [] array, int p, int r) {
		if (p < r) {
			int m = (p + r) / 2;
			sortMerge(array, p, m);
			sortMerge(array, m + 1, r);
			merge(array, p, m, r);
		}
	}
	
	// merges
	public static void merge(int[] array, int p, int q, int r) {
		int i = 0;
		int j = 0;
		int k = 1;
		
		int n1 = q - p + 1;
		int n2 = r - q;
		
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for (i = 0; i < n1; i++) {
			L[i] = array[p + i];
		}
		for (j = 0; j < n2; j++) {
			R[j] = array[q + 1 + j];
		}
		
		i = 0;
		j = 0;
		k = p;
		
		
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				i++;
			} else {
				array[k] = R[j];
				j++;
			}
			
			k++;
		}
		
		while (i < n1) {
			array[k] = L[i];
			i++;
			k++;
		}
		
		while (j < n2) {
			array[k] = R[j];
			j++;
			k++;
		}
		
	}
	
	//initial base call
	public static void quickSort(int[] array) {
		
		sortQuick(array, 0, array.length - 1);
		
	}
	
	//recursive calls 
	public static void sortQuick(int[] array, int low, int high) {
		if (low < high) {
			int n = quickPartition(array, low, high);
			
			sortQuick(array, low, n - 1);
			sortQuick(array, n + 1, high);
		}
	}
	
	//partitions and does our divide
	public static int quickPartition(int array[], int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		
		for (int j = low; j < high; j++) {
			if (array[j] < pivot) {
				i++;
				
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}
		}
		
		int temp = array[i + 1];
		array[i + 1] = array[high];
		array[high] = temp;
		
		return i + 1;
	}
	
	//CLRS implementation
	public static void bucketSort(int[] array) {
		int bucketCount = array.length/2;
		int minIntValue = 0;
		int maxIntValue = array.length - 1;
		// Create bucket array
		List<Integer>[] buckets = new List[bucketCount];
		// Associate a list with each index in the bucket array         
		for(int i = 0; i < bucketCount; i++){
			buckets[i] = new LinkedList<>();
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) max = array[i];
		}
				
		
		for (int i = 0; i < maxIntValue + 1; i++) {
			int bi = Math.abs((int)Math.floor((bucketCount * array[i]) / (max + 1)));
			buckets[bi].add(array[i]);
			
		}
		// sort buckets
		for(List<Integer> bucket : buckets){
			Collections.sort(bucket);
		}
		int i = 0;
		// Merge buckets to get sorted array
		for(List<Integer> bucket : buckets){
			for(int num : bucket){
				array[i++] = num;
			}
		}
	}
	public static void main(String[] args) {
		int NUM_RUNS = 3;
		// create instance of Random class 
		Random rand = new Random(); 
		/////////////////////////////////////////
		int LENGTH=100;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100 = build_random_array(rand, LENGTH);
		//For runtime computations
		long startTime, endTime;
		double duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			buildMaxHeap(array_100_c);
			heapSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100_c = array_100.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
			assert_array_sorted(array_100, array_100_c);
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		/////////////////////////////////////////
		LENGTH=1000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_1000 = build_random_array(rand, LENGTH);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_1000_c = array_1000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_1000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		/////////////////////////////////////////
		LENGTH=10000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_10000 = build_random_array(rand, LENGTH);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_10000_c = array_10000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_10000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		/////////////////////////////////////////
		LENGTH=100000;
		System.out.println("_____________INPUT "+LENGTH+"_____________");
		int[] array_100000 = build_random_array(rand, LENGTH);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			insertionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("InsertionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			selectionSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("SelectionSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			heapSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("HeapSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			mergeSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("MergeSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			quickSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("QuickSort mean runtime over " + NUM_RUNS + " runs is " + duration);
		duration = 0;
		for (int t = 0 ; t < NUM_RUNS ; t++) {
			int[] array_100000_c = array_100000.clone();
			startTime = System.currentTimeMillis();
			bucketSort(array_100000_c);
			endTime = System.currentTimeMillis();
			duration += ((double) (endTime - startTime));
		}
		duration = duration / (double) NUM_RUNS;
		System.out.println("BucketSort mean runtime over " + NUM_RUNS + " runs is " + duration);
	}
}
