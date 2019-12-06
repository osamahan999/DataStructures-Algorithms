package Assignment2;

import static org.junit.Assert.*;

/**
 * Name: Osama Hanhan
 * Student ID: 014245114
 * Description of solution: This applies the Strassen Algorithm to multiple two NxN matrices
 * where N % 2 == 0. O(n^2)
 * 
 * 	The case of masters theorem that applies is a > b^d since a= 7, b = 2, and d = 2
 *  This is the first case from the CLRS textbook. This is because log2(7) requires epsilon to be 3 to hold true.
 *  Upper bound of O(n^2)
 *  This is faster than the naive method because the naive method has 8 recursive calls, making log2(8) = 3, so N^3
 * 
 * @author Osama Hanhan
 *
 */
public class MatrixMult {
	/**
	 * Function to multiply matrices Assumes A and B are square and have the same
	 * size!
	 * 
	 * @param A - matrix A to multiply (first operand)
	 * @param B - matrix B to multiply (second operand)
	 ***/
	public static int[][] multiply(int[][] A, int[][] B) {
		int n = A.length;
		int[][] R = new int[n][n];
		
		//check nxn and n % 2 == 0 when > 1 and A dimensions == B dimensions
		if ((n != A[0].length) || 
			(n % 2 != 0 && n > 1)  || 
			(n != B.length)		   || 
			(A[0].length != B[B.length - 1].length)) {
			System.out.println("Check dimensions! Matrices must be nxn, and n % 2 must equal 0.\nAlso, A and B must have same dimensions");
			return A;
		}
		
		
		/** base case **/
		if (n == 1)
			R[0][0] = A[0][0] * B[0][0];
		else {
			//first, we split our matrices A and B into four N/2 quadrants
			int[][] A11 = new int[n/2][n/2];
			int[][] A12 = new int[n/2][n/2];
			int[][] A21 = new int[n/2][n/2];
			int[][] A22 = new int[n/2][n/2];
			int[][] B11 = new int[n/2][n/2];
			int[][] B12 = new int[n/2][n/2];
			int[][] B21 = new int[n/2][n/2];
			int[][] B22 = new int[n/2][n/2];
			
			//Should look like A = A11 A12
			//					   A21 A22
			//				   B = B11 B12
			//					   B21 B22
			split(A, A11, 0, 0); //where iB and jB= A11 which is the top left so [0][0]
			split(A, A12, 0, n/2); //where iB and jB = A12 which is top right so [0][n/2]
			split(A, A21, n/2, 0); // bottom left so [N/2][0]
			split(A, A22, n/2, n/2); //bottom right [n/2][n/2]
			
			split(B, B11, 0, 0); // top left
			split(B, B12, 0, n/2); //top right
			split(B, B21, n/2, 0); //bottom left
			split(B, B22, n/2, n/2); //bottom right
			
			//now we create our seven recursive calls which should give us less than cubic time
			int[][] M1 = multiply(add(A11, A22), add(B11, B22)); //M1 = (A11 + A22)(B11 + B22)
			int[][] M2 = multiply(add(A21, A22), B11); // M2 = (A21+A22)B21
			int[][] M3 = multiply(A11, sub(B12, B22)); // M3 = A11(B12 - B22);
			int[][] M4 = multiply(A22, sub(B21, B11)); // M4 = A22(B21 - B11);
			int[][] M5 = multiply(add(A11, A12), B22); // M5 = (A11 + A12)B22;
			int[][] M6 = multiply(sub(A21, A11), add(B11, B12)); // M6 = (A21 - A11)(B11 + B12);
			int[][] M7 = multiply(sub(A12, A22), add(B21, B22)); // M6 = (A21 - A11)(B11 + B12);

			//C11 = M1 + M4 - M5 + M7
			//Rewritten as (M1 + M4) + (M7 - M5)
			int[][] C11 = add(add(M1, M4), sub(M7, M5));
			int[][] C12 = add(M3, M5); //C12 = M3 + M5
			int[][] C21 = add(M2, M4); //C21 = M2 + M1
			//C22 = M1 - M2 + M3 + M6
			///Rewritten as (M1 - M2) + (M3 + M6)
			int[][] C22 = add(sub(M1, M2), add(M3, M6));

			//Now, we join together C11, C12, C21, and C22 into one matrix in R
			join(C11, R, 0, 0); //top left so 0,0
			join(C12, R, 0, n/2); //top right so 0, n/2
			join(C21, R, n/2, 0); //Bottom left so n/2,0
			join(C22, R, n/2, n/2); //bottom right so n/2, n/2
			
			
			
		}
		/** return result **/
		return R;
	}

	/**
	 * Function to subtract two matrices A and B
	 ***/
	public static int[][] sub(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}

	/**
	 * Function to add two matrices A and B
	 ***/
	public static int[][] add(int[][] A, int[][] B) {
		int n = A.length;
		int[][] C = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}

	/**
	 * Function to split parent matrix into child matrices. Assumes C is
	 * initialized.
	 * 
	 * @param P  - parent matrix
	 * @param C  - A child matrix that will get the corresponding indices of the
	 *           parent
	 * @param iB - start row in parent
	 * @param jB - start column in parent
	 ***/
	public static void split(int[][] P, int[][] C, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				C[i1][j1] = P[i2][j2];
	}

	/**
	 * Function to join child matrices into a parent matrix Assumes C is
	 * initialized.
	 * 
	 * @param P  - parent matrix
	 * @param C  - A child matrix that will provide the corresponding indices of the
	 *           parent
	 * @param iB - start row in parent
	 * @param jB - start column in parent
	 ***/
	public static void join(int[][] C, int[][] P, int iB, int jB) {
		for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
			for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
				P[i2][j2] = C[i1][j1];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
// Ensure that multiplying the two matrices gives the correct result!
		System.out.println("Test Case 1: 3x2 x 3x2");
		int[][] D = { { 1, 2}, { 3, 4}, {3, 3} };
		int[][] E = { { 5, 6}, { 7, 8}, {3, 3} };
		multiply(D, E);
		
		System.out.println("Test Case 2: 3x3 x 3x3");
		int[][] G = { { 1, 2, 3}, { 3, 4, 3}, {3, 3, 3} };
		int[][] H = { { 5, 6, 3}, { 7, 8, 3}, {3, 3, 3} };
		multiply(G, H);
		
		System.out.println("Test Case 3: 3x3 x 3x2");
		int[][] X = { { 1, 2, 3}, { 3, 4, 3}, {3, 3, 3} };
		int[][] Y = { { 5, 6}, { 7, 8}, {3, 3} };
		multiply(X, Y);
		
		System.out.println("Test Case 4: 4x4 x 4x4");
		int[][] N = { { 1, 2, 3, 4}, { 3, 4, 3, 4}, {3, 3, 3, 4}, {3, 3, 3, 4} };
		int[][] M = { { 5, 6, 3, 4}, { 7, 8, 3, 4}, {3, 3, 3, 4}, {3, 3, 3, 4} };
		System.out.println(multiply(N, M).toString());
		
		
		
		System.out.println("Test Case 5, Professor-provided test: 2x2 x 2x2");

		int[][] A = { { 1, 2 }, { 3, 4} };
		int[][] B = { { 5, 6 }, { 7, 8} };
		int[][] C = multiply(A, B);
		System.out.println(C.toString());
		System.out.println("Testing...");
		assertEquals(C[0][0], 19);
		assertEquals(C[0][1], 22);
		assertEquals(C[1][0], 43);
		assertEquals(C[1][1], 50);
		System.out.println("Success!");
	}
}
