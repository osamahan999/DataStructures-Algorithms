package StacksQueues;
/**
 * First in, first out. Basically a linked list
 * @author Osama
 *
 * @param <T>
 */
public class MyQueue<T> {
	private QueueNode head;
	private QueueNode tail;
	
	
	private class QueueNode {
		private T data;
		private QueueNode next;
		private QueueNode prev;
		
		public QueueNode(T data) {
			this.data = data;
			next = null;
			prev = null;
		}
		
	}
	
	/**
	 * O(1) insertion
	 * @param data
	 */
	public void queue(T data) {
		QueueNode newNode = new QueueNode(data);
		
		if (head == null) {
			head = newNode;
			head.next = tail;
		} else {
			if (tail == null) {
				tail = newNode;
				tail.prev = head;
				head.next = tail;
			}
			else {
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			}
		}	
	}
	
	/**
	 * O(1) deletion
	 * @return
	 */
	public T dequeue() {
		head.next.prev = null;
		T data = head.data;
		head = head.next;
		
		return data;
	}
	
	/**
	 * O(n) traversal
	 */
	public void printListForward() {
		QueueNode n = head;
		
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}
	
	public void printListBackward() {
		QueueNode n = tail;
		
		while (n != null) {
			System.out.println(n.data);
			n = n.prev;
		}
	}
	
	/**
	 * O(n) though why would you search a queue??
	 * @param data
	 * @return
	 */
	public boolean search(T data) {
		QueueNode n = head;
		
		while(n != null) {
			if (n.data == data) return true;
			n = n.next;
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		MyQueue<String> stringQueue = new MyQueue<String>();
		String stringArr[] = {"a", "b", "c"};
		
		for (int i = 0; i < stringArr.length; i++) {
			stringQueue.queue(stringArr[i]);
		}
		
		stringQueue.printListForward();
		stringQueue.printListBackward();
		
		if (stringQueue.search("d")) System.out.println("d is found"); 
		else System.out.println("d is not found");
		

		if (stringQueue.search("c")) System.out.println("c is found"); 
		else System.out.println("c is not found");
	}
	
}

