package Assignment1;

/**
 * Osama Hanhan
 * 014245114
 * 09/14/2019
 * 
 * Substring class which has functions that build an arraylist full of combinations of the input string
 * EX: Input string "abc"
 * 	   Output ArrayList: "", "a", "b", "c", "ab", "ac", "abc"
 * @author Osama
 */

import java.util.ArrayList;
import static org.junit.Assert.*;


public class Substring {

	private static StringBuilder output;
	private static ArrayList<String> arr;
	
	public Substring(String s) {
	}
	

	
	//preliminary function that checks if string is null or empty, and adds empty string combination to list
	private static ArrayList<String> allSubstrings(String s) {
		//returns null if input string is new String(), null, or ""
		if (s != null && !s.isEmpty()) {
			
			//initialize arraylist and output variable
			arr = new ArrayList<String>();
			output = new StringBuilder();
				
			//add empty string combination
			arr.add("");
			//initial call of recursive function
			combine(s, 0);

			
			//prints entire arrList
			for (String x: arr) {
				System.out.println(x);
			}
			
			return arr;
			
		} else {
			System.out.println("Check your input.");
			return null;
		}
	}
	
	//recursive function that adds combinations of input string into an arraylist arr
	private static void combine(String inputString, int startIndex) {
		for (int i = startIndex; i < inputString.length(); i++) {
			//appends the letter to the stringbuilder object
			output.append(inputString.charAt(i));
			
			//adds stringbuilder string to arraylist
			arr.add(output.toString());
			
			//recurse
			if (i < inputString.length()) combine(inputString, i + 1);
			
			//set length to string length minus 1, because if not we would never get
			// our combinations such as 'ac' from 'abc'. 
			//If we were to remove this line, input string 'ab' would return a, ab, abb
			output.setLength(output.length() - 1);
		}
	}
	
		
	public static void main(String[] args) {
	
		
		ArrayList<String> s = allSubstrings("abcde");
		System.out.println("Testing...");
		assertEquals(s.size(), 32);
		/**
		 * These are all the 32 substrings that should be contained in Q1.
		 */
		assertEquals(s.contains(""), true);
		assertEquals(s.contains("a"), true);
		assertEquals(s.contains("b"), true);
		assertEquals(s.contains("c"), true);
		assertEquals(s.contains("d"), true);
		assertEquals(s.contains("e"), true);
		assertEquals(s.contains("ab"), true);
		assertEquals(s.contains("ac"), true);
		assertEquals(s.contains("ad"), true);
		assertEquals(s.contains("ae"), true);
		assertEquals(s.contains("bc"), true);
		assertEquals(s.contains("bd"), true);
		assertEquals(s.contains("be"), true);
		assertEquals(s.contains("cd"), true);
		assertEquals(s.contains("ce"), true);
		assertEquals(s.contains("de"), true);
		assertEquals(s.contains("abc"), true);
		assertEquals(s.contains("abd"), true);
		assertEquals(s.contains("abe"), true);
		assertEquals(s.contains("acd"), true);
		assertEquals(s.contains("ace"), true);
		assertEquals(s.contains("ade"), true);
		assertEquals(s.contains("bcd"), true);
		assertEquals(s.contains("bce"), true);
		assertEquals(s.contains("bde"), true);
		assertEquals(s.contains("cde"), true);
		assertEquals(s.contains("abcd"), true);
		assertEquals(s.contains("abce"), true);
		assertEquals(s.contains("acde"), true);
		assertEquals(s.contains("abde"), true);
		assertEquals(s.contains("bcde"), true);
		assertEquals(s.contains("abcde"), true);
		System.out.println("Success!");
	}
	
}


