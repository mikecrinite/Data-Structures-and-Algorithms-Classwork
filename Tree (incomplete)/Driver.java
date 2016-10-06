package pkg;

import java.util.LinkedList;

public class Driver{
	public static void main(String[] args) throws Exception{
		System.out.println("Tree 1: \nThis tree should have had the integers 0-10 inserted and then 3,5, and 8 deleted.");
		Node n = new Node(5, null, null, null);
		BinarySearchTree tree = new BinarySearchTree(n);
		//try {
			for(int i = 0; i < 5; i++){
				tree = tree.insert(tree, i);
			}
			for(int i = 6; i < 11; i++){
				tree = tree.insert(tree, i);
			}
		//} catch (Exception e) {
		//	System.out.println("Woah");
		//}
		tree.delete(tree, 3);
		tree.delete(tree, 5);
		tree.delete(tree, 8);
		LinkedList<Node> list = tree.inOrder(tree);		
		for(Object o : list){
			Node node = (Node) o;
			System.out.println(node.getData().toString());
		}
		System.out.println("\nIs there a 6? (Should print yes)");
		Comparable<Object> cO = (Comparable) new Integer(6);
		if(tree.search(tree, cO)){
			System.out.println("Yes");
		}else{
			System.out.println("No");
		}
		
		System.out.println("Values between 3 and 7 (non-inclusive): " + tree.count(3, 7));
		
		System.out.println("\n\nTree 2: \nThis tree should be composed of strings and be in order of the String's value");
		String five = "five";
		Node n2 = new Node(five, null, null, null);
		BinarySearchTree treeTwo = new BinarySearchTree(n2);
		treeTwo.insert(treeTwo, "this");
		treeTwo.insert(treeTwo, "is");
		treeTwo.insert(treeTwo, "just");
		treeTwo.insert(treeTwo, "a");
		treeTwo.insert(treeTwo, "tree");
		treeTwo.insert(treeTwo, "lab");
		treeTwo.insert(treeTwo, "test");
		treeTwo.delete(treeTwo, "five"); //Delete the root
		LinkedList<Node> list2 = treeTwo.inOrder(treeTwo);
		for(Object o : list2){
			Node node = (Node) o;
			System.out.println(node.getData().toString());
		}
	}
}
