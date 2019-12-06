package Assignment1;

/**
 * Osama Hanhan
 * 014245114
 * 09/15/2019
 * 
 * Algorithm that parses a file into an arraylist, and then computes all possible sums of numbers
 * in said file. It then stores all the sums into a new arraylist and returns that. 
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class PossibleSums {
	
	//store all nums from file in numArr, and all sums in returnArray
	private static ArrayList<Integer> numArr, returnArray;
	private static String file = "C:\\Users\\Osama\\git\\DataStructures&Algorithms\\Data Structures & Algorithms\\src\\Assignment1\\PossibleSums.txt";
	//store temporary sums in output. didnt know what scope to put it in, but this one works.
	private static int output = 0;
	

	
	//initializes array list with numbers from file
	//returns error if file corrupt or IOException
	private static void initializeArr() {
		BufferedReader reader;
		numArr = new ArrayList<Integer>();
		returnArray = new ArrayList<Integer>();
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			String newLine = reader.readLine();
			while (newLine != null) {
				numArr.add(Integer.parseInt(newLine));
				newLine = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException f) {
			System.out.println("------------------------------------");
			System.out.println("Corrupt input, check file!!        |");
			System.out.println("------------------------------------");
		}
	}
	
	
	private static ArrayList<Integer> allSums(ArrayList<Integer> numArr) {
		//adds 0 as an answer, since that is always the case
		returnArray.add(0);
		//initial call of recursive function with startIndex = 0
		getSum(0, numArr);
		return returnArray;
		
	}

	//recursive function literally exactly the same as from my Substring.java algorithm
	private static void getSum(int startIndex, ArrayList<Integer> numArr) {
		for (int i = startIndex; i < numArr.size(); i++) {
			output += numArr.get(i);
			
			//removes duplicates
			if (!returnArray.contains(output)) returnArray.add(output);

			if (i < numArr.size()) getSum(i + 1, numArr);
			//removes output after adding to arraylist to get other combinations
			output -= numArr.get(i);
		}
		
	}
	
	//sorts and prints array
	private static void printArr() {
//		returnArray.sort(null);
		for (int i = 0; i < returnArray.size(); i++) System.out.println(returnArray.get(i));
	}
	
	
	
	public static void main (String[] args) {
		
	initializeArr();
	ArrayList<Integer> sums = allSums(numArr);
	System.out.println("Testing...");
	assertEquals(sums.size(), 8);
	assertEquals(sums.contains(0), true);
	assertEquals(sums.contains(1), true);
	assertEquals(sums.contains(2), true);
	assertEquals(sums.contains(4), true);
	assertEquals(sums.contains(3), true);
	assertEquals(sums.contains(5), true);
	assertEquals(sums.contains(6), true);
	assertEquals(sums.contains(7), true);
	System.out.println("Success!");	

	printArr();
	}
}
