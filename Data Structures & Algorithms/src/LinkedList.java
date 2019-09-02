/**
 * Simple linked list implementation
 * @author Osama
 *
 * @param <T>
 */
public class LinkedList<T> {
	//head
	private Node head = null;
		
	
	class Node{
		private T data;
		private Node next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}
	}
	
	public void add(T data) {

		Node newNode = new Node(data);
		if (head == null) {
			head = newNode;
			
		} else {
			
			Node n = head;
			while (n.next != null) {
				n = n.next;
			}
			
			n.next = newNode;
		}
	}
	
	public void printList() {
		Node n = head;
		
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}
	
	
	
	public static void main(String[] args) {
		
		//string arr test
		LinkedList<String> stringList = new LinkedList<String>();
		String[] stringArr = {"Hello", "I", "am", "Osama"};
		
		for (int i = 0; i < stringArr.length; i++) {
			stringList.add(stringArr[i]);
		}
		stringList.printList();
		
		
		//int arr test
		LinkedList<Integer> intList = new LinkedList<Integer>();
		int[] intArr = {2, 6, 5, 8 , 1, 5, 6, 7, 7, 8, 9};
		
		for (int i = 0; i < intArr.length; i++) {
			intList.add(intArr[i]);
		}
		
		intList.printList();
	}
}
