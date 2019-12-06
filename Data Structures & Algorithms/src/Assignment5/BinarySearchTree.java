package Assignment5;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is an implementation of a Binary Search Tree (BST).
 * @author andreopo
 *
 */
public class BinarySearchTree {

	/**
	 * Class containing key (name) for a node and right and left children
	 */
	class Node {
		
		private String key;
		
		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}


		private Node left;
		
		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		
		private Node right;
		
		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		Node(String key) {
			this.key = key;
			right = null;
			left = null;
		}
	}
	
	/**
	 * The root of the binary search tree (BST)
	 */
	Node root;

	// Constructor 
	BinarySearchTree() {  
		root = null;  
	} 

	// Search for a given key in BST
	public Node search(String key) 
	{ 
		Node node = null;
		node = searchRecursive(root, key);
		return node;
	}

	// Implement the search recursively in this helper function
	Node searchRecursive(Node root_node, String key) 
	{ 
		if (root_node != null) {
			int compared = key.compareTo(root_node.getKey());
			
			if (compared == 0) return root_node;
			else if (compared > 0) return searchRecursive(root_node.getRight(), key);
			else return searchRecursive(root_node.getLeft(), key);
		}
		return null;
	}

	// Insert a new Node with a key and value in BST
	public void insert(String key) {
		root = insertRecursive(root, key); 
	}

	// Implement the insert recursively in this helper function
	Node insertRecursive(Node root_node, String key) { 
		if (root_node != null) {
			int compared = key.compareTo(root_node.getKey());
			
			if (compared > 0) root_node.right = insertRecursive(root_node.getRight(), key);
			else if (compared <= 0) root_node.left = insertRecursive(root_node.getLeft(), key);
		} else {
			root_node = new Node(key);
		}
		return root_node;
	} 


	// A recursive inorder traversal of the BST 
	void inorder(Node root, ArrayList<String> strList) { 
		if (root != null) { 
			inorder(root.getLeft(), strList); 
//			System.out.println(root.getKey()); 
			strList.add(root.getKey());
			inorder(root.getRight(), strList); 
		}
	}

	/**
	 * Just has the main stuff with input for a file so that I can run it on all three files
	 * @param file
	 */
	static void testerFunction(String file) {
		//For runtime computations
		long startTime, endTime;
		double duration = 0;

		BufferedReader reader;
		startTime = System.currentTimeMillis();
		BinarySearchTree bst = new BinarySearchTree(); 

		try {
			reader = new BufferedReader(new FileReader(file));
			
			String newLine = reader.readLine();
			while (newLine != null) {
				bst.insert(newLine);
				newLine = reader.readLine();
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		endTime = System.currentTimeMillis();
		duration += ((double) (endTime - startTime));
		System.out.println("BST insertion runtime is " + duration);

		

		/**
		 * So an inorder traversal of the tree, ensure the result is 
		 * order lexicographically
		 */
		ArrayList<String> strList = new ArrayList<String>();
		bst.inorder(bst.root, strList);
		//Ensure the inorder traversal gives a 
		//lexicographic ordering of the names
		for (int i = 1; i < strList.size(); i++) {
			assertTrue(strList.get(i-1).compareTo(strList.get(i)) <= 0  );
		}

		/**
		 * Verify that search returns the correct result
		 */
		startTime = System.currentTimeMillis();
		Node n = bst.search("aaaa");
		assertEquals(n, null);
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for aaaa is " + duration);


		startTime = System.currentTimeMillis();
		n = bst.search("alice");
		assertEquals(n.getKey(), "alice");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for alice is " + duration);
		
		
		startTime = System.currentTimeMillis();
		n = bst.search("zoe");
		assertEquals(n.getKey(), "zoe");
		endTime = System.currentTimeMillis();
		duration = ((double) (endTime - startTime));
		System.out.println("BST search runtime for zoe is " + duration);

	}


	public static void main(String[] args) { 


		String file1 = "C:\\Users\\Osama\\git\\DataStructures&Algorithms\\Data Structures & Algorithms\\src\\Assignment5\\names.txt";
		String file2 = "C:\\Users\\Osama\\git\\DataStructures&Algorithms\\Data Structures & Algorithms\\src\\Assignment5\\names.shuffled.unique.lowercase.txt";
		String file3 = "C:\\Users\\Osama\\git\\DataStructures&Algorithms\\Data Structures & Algorithms\\src\\Assignment5\\names.sorted.unique.lowercase.txt";

		testerFunction(file1);
		testerFunction(file2);
		testerFunction(file3);

		

	}
}
