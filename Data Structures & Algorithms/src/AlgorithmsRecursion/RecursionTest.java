package AlgorithmsRecursion;

public class RecursionTest {

	
	
	/**
	 * Alright how the fuck does this work
	 */
	
	 
	public void recurse(int num, String[] arr) {
		if (num != 0) {
			recurse(num - 1, arr);
			System.out.println(arr[num - 1]);	
		}
	}
	
	public static void main(String[] args) {
		String[] arr = {"first", "second", "third"};
		
		RecursionTest test = new RecursionTest();
		test.recurse(arr.length, arr);
	}
	
	
	
	
	
	
	
	
}
