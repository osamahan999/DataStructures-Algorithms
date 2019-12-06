	package Assignment1;

/**
 * Osama Hanhan
 * 014245114
 * 09/16/2019
 * 
 * Recursive algorithm that calculates n choose k
 */

import static org.junit.Assert.*;

public class Q3 {
	
	//recursively cuts up our n choose k problem into smaller n choose k problems
	//and returns 1 when k == 0 or n. This is then all summed up and returns the value of n choose k
	public static int C(int n, int k) {
		if (k == 0 || k == n) return 1;
		return C(n - 1, k - 1) + C(n - 1, k);
	}
	

	public static void main(String[] args) {

		
		System.out.println("Testing...");
		assertEquals(C(14,3), 364);
		assertEquals(C(14,11), 364);
		assertEquals(C(18,8), 43758);
		System.out.println("Success!");
	}
}