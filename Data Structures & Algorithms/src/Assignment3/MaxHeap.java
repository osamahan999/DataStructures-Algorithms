package Assignment3;

import static org.junit.Assert.*;


/**
 * 
 *
 */
public class MaxHeap {

    private int[] HeapArray; 
    
    public int[] getHeapArray() {
		return HeapArray;
	}

	private int size; 
    private int maxsize; 
  
    private static final int FRONT = 1; 
  
    public MaxHeap(int maxsize) 
    { 
        this.maxsize = maxsize; 
        this.size = maxsize;
        HeapArray = new int[maxsize]; 
        //initialize heap array to a set of numbers, rearranged a little
        for (int i = FRONT; i < HeapArray.length; i++) {
        	HeapArray[i] = maxsize-i;
        }
    } 
  
    // Return the index of the parent of the node currently at pos 
    private int parent(int pos) 
    { 
        return (pos / 2); 
    } 
  
    // Return the index of the leftchild of the node currently at pos 
    private int leftChild(int pos) 
    { 
        return (2 * pos); 
    } 
  
    // Return the index of the rightchild of the node currently at pos 
    private int rightChild(int pos) 
    { 
        return (2 * pos) + 1; 
    } 


    //Function to heapify the node at position i, up to the position n 
	public void maxHeapify(int i, int heapsize) {
		int l = leftChild(i);
		int r = rightChild(i);

		int largest;
		if (l < heapsize && HeapArray[l] > HeapArray[i]) {
			largest = l;
		} else largest = i;
		
		if (r < heapsize && HeapArray[r] > HeapArray[largest]) {
			largest = r;
		} 
		if (largest != i) {
			swap(i, largest);
			
			maxHeapify(largest, heapsize);
		}
	}

    
    public void buildMaxHeap() {
		int heapsize = HeapArray.length;

		for (int i = (int) Math.floor(heapsize / 2); i > 0; i--) {
			maxHeapify(i, heapsize);
		}
    }

    public void sort() {
        buildMaxHeap();
        for (int i = HeapArray.length; i > 1; i--) {
        	swap(1, i);
        	maxHeapify(1, i - 1);
        }
    }

    
    
    // Swap two nodes of the heap at index first second
    private void swap(int first, int seconds) 
    { 
        int tmp; 
        tmp = HeapArray[first]; 
        HeapArray[first] = HeapArray[seconds]; 
        HeapArray[seconds] = tmp; 
    } 
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int SIZE = 30;
		MaxHeap heap = new MaxHeap(SIZE);
		heap.sort();
		int[] heapArr = heap.getHeapArray();
		assertEquals(heapArr[0], 0);
		assertEquals(heapArr[1], 1);
		assertEquals(heapArr[2], 2);
		assertEquals(heapArr[SIZE-1], SIZE-1);
		assertEquals(heapArr.length, SIZE);
	}

}
