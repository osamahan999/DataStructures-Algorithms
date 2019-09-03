package StacksHeapsQueues;
/**
 * Simple stack implementation
 * @author Osama
 *
 */
public class MyStack<T> {
	private StackNode top;
	
	private class StackNode {
		private T data;
		private StackNode next;
		
		public StackNode(T data) {
			this.data = data;
			next = null;
		}
	}
	
	public void push(T data) {
		StackNode newNode = new StackNode(data);
		
		if (top == null) top = newNode;
		else {
			newNode.next = top;
			top = newNode;
		}
	}
	
	public T pop() {
		
		T data = top.data;
		top = top.next;
		
		return data;
	}
	
	public T seek() {
		return top.data;
	}
	
	public boolean isEmpty() {
		if (top != null) return false;
		return true;
	}
	
	
	public static void main(String[] args) {
		MyStack<String> stringStack = new MyStack<String>();
		
		stringStack.push("Jimmy");
		stringStack.push("Osama");
		stringStack.push("Bobby");
		stringStack.push("Ronald");

		
		System.out.println(stringStack.pop());
		System.out.println(stringStack.pop());
		System.out.println(stringStack.pop());
		
		stringStack.push("Wait I am not a name!");
		System.out.println(stringStack.pop());
		System.out.println("Seek method got:" + stringStack.seek());
		
		if (stringStack.isEmpty()) System.out.println("Empty stack!");
		else System.out.println("Theres still stuff in the stack!");
	}
}
