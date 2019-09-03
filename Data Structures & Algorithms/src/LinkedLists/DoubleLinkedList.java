package LinkedLists;

/**
 * Doubly Linked List
 * @author Osama
 *
 * @param <T>
 */
public class DoubleLinkedList<T> {
	
	private Node head = null;
	
	/**
	 * Node class in our LinkedList class. 
	 * @author Osama
	 *
	 */
	private class Node {
		private Node next, prev;
		private T data;
		
		public Node(T data) {
			this.data = data;
			
			prev = null;
			next = null; 
		}
	}
	
	public void add(T data) {
		Node newNode = new Node(data);
		
		if (head == null) {
			head = newNode;
		} else {
			//temp variable to not change head
			Node n = head;
			
			//gets last node O(n)
			while (n.next != null) {
				n = n.next;
			}
			
			//n is now last node
			n.next = newNode;
			n.next.prev = n;
		}
	}
	
	public void printList() {
		//temp variable to not change head
		Node n = head;
		
		//traverses through entire list O(n)
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}
	
	public void printListBackwards() {
		//temp variable to not change head
		Node n = head;
		
		//gets the last node
		while (n.next != null) n = n.next;
		
		//traverses backwards from last node
		while (n != null) {
			System.out.println(n.data);
			n = n.prev;
		}
	}
	
	/**
	 * O(n) search
	 * @param data
	 * @return
	 */
	public boolean search(T data) {
		Node n = head;
		while (n != null) {
			if (n.data == data) return true;
			n = n.next;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		DoubleLinkedList<String> stringList = new DoubleLinkedList<String>();
		String[] stringArr = {"Hello", "This", "Is", "Doubly", "Linked"};
		
		for (int i = 0; i < stringArr.length; i++) {
			stringList.add(stringArr[i]);
		}
		
		stringList.printList();
		stringList.printListBackwards();
		

		if (stringList.search("This")) System.out.println("'This' is found"); 
		else System.out.println("'This' is not found");
	}
}
