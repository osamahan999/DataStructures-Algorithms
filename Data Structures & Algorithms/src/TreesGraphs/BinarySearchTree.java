package TreesGraphs;

/**
 * Unbalanced, BST with three traversal strategies. Used lots of help from internet. Will be redone a 
 * few times to make sure I know how to do it. Also, only int data, did not implement comparator class or w/e its called
 * @author Osama
 *
 */
public class BinarySearchTree {
	
	private static TreeNode head = null;
	
	private class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;
		
		public TreeNode(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}
	
	
	//calls recursive insert method insertRec
	//replaces our head node with the new head node
	public void insert(TreeNode head, int data) {
		this.head = insertRec(head, data);
	}
	
	//non balancing insertion method
	// if node is empty, replaces it with new node with data
	//if less than inserted node, recursively calls to the left, if less again, another call, until it is null. then replaced. 
	//same with if more than inserted node
	public TreeNode insertRec(TreeNode node, int data) {
		
		if (node == null) { 
			node = new TreeNode(data);
			return node;
			
		} 
		
		if (node.data > data) {
			node.left = insertRec(node.left, data);
		} else if (node.data <= data) {
			node.right = insertRec(node.right, data);
		}
		
		return node;
	}
	
	//left middle right
	public void inOrderTraversal(TreeNode node) {
		//visit left, then mid, then right

		if (node != null) {
			inOrderTraversal(node.left);
			System.out.println(node.data);
			inOrderTraversal(node.right);
		}
	}
	
	public void postOrderTraversal(TreeNode node) {
		if (node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			System.out.println(node.data);
		}
	}
	
	public void preOrderTraversal(TreeNode node) {
		if (node != null) {
			System.out.println(node.data);
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
		}
	}
	
	public void visit(TreeNode node) {
		System.out.println(node.data);
	}
	
	
	public static void main(String[] args) {
		int[] intArray = {5, 2, 8, 3, 7, 4, 6};

		System.out.println("arr of data is {5, 2, 8, 3, 7, 4, 6}");
		BinarySearchTree tree = new BinarySearchTree();
		
		for (int i = 0; i < intArray.length; i++) {
			tree.insert(head, intArray[i]);
		}
		System.out.println("inOrderTraversal");
		tree.inOrderTraversal(head);
		System.out.println("postOrderTraversal");
		tree.postOrderTraversal(head);
		System.out.println("preOrderTraversal");
		tree.preOrderTraversal(head);
	}
	
	
}
