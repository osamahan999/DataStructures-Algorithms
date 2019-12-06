package TreesGraphs;
import static org.junit.Assert.*;

public class HeapSort {
	
	
	public static void main(String[] args) {
		
		int[] A = {2,4,13,9,11,10,6, 15, 20, 0, 5, 18, 14}; // minimum is 0+2
		HeapSort n = new HeapSort();
		n.maxHeap(A);
		assertEquals(A[0], 20);
		assertEquals(A[1], 15);
		assertEquals(A[2], 18);
		n.minSort(A);
		assertEquals(n.twoMin(A), 2);
		
		int[] B = {2,4,-3,50,11,10,-15, 15, 20, 0, 3, 18, -8}; //minimum is -8 + -15
		n.maxHeap(B);
		assertEquals(B[0], 50);
		assertEquals(B[1], 20);
		assertEquals(B[12], -8);
		n.minSort(B);
		assertEquals(n.twoMin(B), -23);
		
		int[] C = {1};
		n.maxHeap(C);
		assertEquals(C[0], 1);
		n.minSort(C);
		assertEquals(n.twoMin(C), 0); //0 becasue cannot add smallest two nums when arr doesnt have 2 nums
	}
	
	/**
	 * Gets sum of the smallest two
	 * @param A
	 * @return
	 */
	public int twoMin(int[] A) {
		if (!(A.length > 1)) return 0;
		
		System.out.println("The minimum sum is:");
		int sum = A[0] + A[1];
		System.out.println(A[0] + " + " + A[1] + " = " + sum);
		
		return sum;
	}
	
	public void maxHeap(int[] A) {
		int heapsize = A.length;
		
		for (int i = (heapsize / 2) - 1; i >= 0; i--) {
			maxHeapify(A, i, heapsize);
		}
		
		
	}
	
	/**
	 * Sorts maxHeapified arr in ascending order
	 * @param A
	 */
	public void minSort(int[] A) {
		int heapsize = A.length;
		for (int i = heapsize - 1; i >= 0; i--) {
			int temp = A[0];
			A[0] = A[i];
			A[i] = temp;	
		
			maxHeapify(A, 0, i);
		}
	}
	
	/**
	 * sorts array in maxHeap order
	 * @param A
	 * @param i
	 * @param heapsize
	 */
	public void maxHeapify(int[] A, int i, int heapsize) {
		int l = getLeft(i);
		int r = getRight(i);

		int largest = i;
		if (l < heapsize && A[l] > A[largest]) {
			largest = l;
		}
		
		if (r < heapsize && A[r] > A[largest]) {
			largest = r;
		} 
		
		if (largest != i) {
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			
			maxHeapify(A, largest, heapsize);
		}
	}
	
	public void print(int[] A) {
		for (int x: A) {
			System.out.print(x + " ");
		}
		System.out.println();

	}
	
	
	public int getLeft(int i) {
		return (2*i) + 1;
	}
	public int getRight(int i) {
		return (2*i) + 2;
	}
}
