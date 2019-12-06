package Assignment2;
import static org.junit.Assert.*;

import java.util.HashSet;

/**
 * Osama Hanhan
 * 014245114
 * Using Floyd's Cycle Detection algorithm with one slow point and one fast point to find whether a 
 * cycle exists in a linked list. 
 * @author Osama
 *
 */
public class CycleLinkedList {
	/*
	 * This function returns true if given linked list has a cycle, else returns
	 * false.
	 * 
	 */
	public static boolean hasCycle(Node head) {
		
		Node slow = head, fast = head;
		
		//double pointers, one iterating at ++, the other +2 
		//if they meet where slow = fast, cycle found
		// else no cycle.
		while(fast != null && slow != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			
			//cycle
			if (slow == fast) return true;
		}
		//no cycle
		return false;
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
// TODO Auto-generated method stub
		
		System.out.println("Test 1: One node");
		Node test = new Node ("s");
		if (!hasCycle(test)) System.out.println("No Cycle!");
		else System.out.println("Cycle!");

		System.out.println("Test 2: One node pointing to itself");
		test.next = test;
		if (!hasCycle(test)) System.out.println("No Cycle!");
		else System.out.println("Cycle!");

		System.out.println("Test 3: One node pointing to another node pointing to null");
		test.next = new Node("b");
		test.next.next = null;
		if (!hasCycle(test)) System.out.println("No Cycle!");
		else System.out.println("Cycle!");

		
		Node head = new Node("start");
		Node prev = head;
		for (int i = 0; i < 4; i++) {
			Node temp = new Node(Integer.toString(i));
			prev.setNext(temp);
			prev = temp;
		}
		boolean b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, false);
		System.out.println("Success!");
		prev.setNext(head.getNext());
		b = hasCycle(head);
		System.out.println("Testing...");
		assertEquals(b, true);
		System.out.println("Success!");
	}
}

class Node {
	Node next;
	String val;
	
	public Node(String s) {
		val = s;
	}
	
	public void setNext(Node n) {
		next = n;
	}
	public Node getNext() {
		return next;
	}
}