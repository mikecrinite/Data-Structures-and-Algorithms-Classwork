package pkg;

import java.util.LinkedList;

@SuppressWarnings("rawtypes")
public class BinarySearchTree {
	private Node root = null;
	
	/**
	 * Default constructor for objects of class BinarySearchTree
	 * 
	 * Creates a BinarySearchTree with no root. Probably isn't very useful
	 */
	public BinarySearchTree(){
		//Do nothing
	}
	
	/**
	 * Constructor for objects of class BinarySearchTree which creates a 
	 * tree with a single node at the root
	 * 
	 * @param root
	 */
	public BinarySearchTree(Node root){
		this.root = root;
	}
	
	/**
	 * Consumes a binary search tree and a Comparable object and returns a Boolean 
	 * indicating whether the Comparable object is in the tree
	 * 
	 * @param tree The tree to search
	 * @param o The object for which to search
	 * @return True if the object is in the tree
	 */
	public boolean search(BinarySearchTree tree, Comparable<Object> o){
		Node n = new Node(o, null, null, null);
		Node curr = tree.getRoot();
		boolean found = false;
		while(!found){
			int c = n.compare(curr);
			if(c==0){
				return true;
			}else if(c<0){
				if(curr.getLeft()!=null){
					curr = curr.getLeft();
				}else{
					return false;
				}
			}else if(c>0){
				if(curr.getRight()!=null){
					curr = curr.getRight();
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Consumes a binary search tree and a Comparable object and returns a new
	 * BinarySearchTree which contains all the Comparable objects from the first
	 * tree plus the new object (the new object is not duplicated)
	 * 
	 * @param tree The tree into which to insert
	 * @param o The object which to insert into the tree
	 * @return The new tree
	 * @throws Exception If some stuff goes wrong
	 */
	public BinarySearchTree insert(BinarySearchTree tree, Comparable o) throws Exception{
		Node curr = tree.getRoot();
		Node newRoot = curr;
		Node parent = curr;
		boolean here = false;
		Node newNode = new Node(o, null, null, null);
		while(!here){
			int c = newNode.compare(curr); // if o<curr, -1
			if(c==0){
				throw new Exception(); // Duplicate
			}else if(c<0){
				if(curr.getLeft()!=null){
					curr = curr.getLeft();
				}else{
					curr.setLeft(newNode);
					newNode.setParent(curr);
					here = true;
				}
			}else if(c>0){
				if(curr.getRight()!=null){
					curr = curr.getRight();
				}else{
					curr.setRight(newNode);
					newNode.setParent(curr);
					here = true;
				}
			}else{
				// This isn't even possible
			}
		}
		return new BinarySearchTree(newRoot);
	}
	
	/**
	 * Consumes a BinarySearchTree and a Comparable object and returns a new BinarySearchTree
	 * which contains all the Comparable objects from the first tree, minus the given Comparable object
	 * 
	 * @param tree The tree from which to delete
	 * @param o The object to delete from the tree
	 * @return The new tree sans o
	 */
	public BinarySearchTree delete(BinarySearchTree tree, Comparable o){
		Node curr = tree.getRoot();
		//Node parent = null;
		Node newRoot = curr;
		Node newNode = new Node(o, null, null, null);
		if(curr.isLeaf()){
			return tree;
		}else{
			boolean flag = true;
			while(flag && !curr.isLeaf()){
				int n = newNode.compare(curr);
				if(n==0){
					//this will need to be removed
					flag = false;
					
					// Retrieve data from rightmost Node in left subtree and substitute it into this node, then remove that node.
					Node temp = curr;									//Hold on to the current node
					if(curr.getLeft()!=null){					//If this node has a left child
						temp = curr.getLeft();							//Hold on to the left child
						while(temp.getRight()!=null){				//While the current node has a right child
							temp = temp.getRight();						//Since we want the rightmost child, keep getting that one
						}
						curr.setData(temp.getData());   //Set the current node's data to the inorder predecessor's data
						Node parent = temp.getParent();		//Store temp's parent
						if(parent.getRight()==temp){
							parent.setRight(null);
						}else if(parent.getLeft()==temp){
							parent.setLeft(null);
						}
						
					}else if(curr.getRight()!=null){	//If this node has a right child but no left child
						temp = curr.getRight();         		//Hold on tot he right child
						while(temp.getLeft()!=null){				//While the current node has a left child
							temp = temp.getLeft();						//Since we want the leftmost child, keep getting that one
						}
						curr.setData(temp.getData());		//Set the current node's data to the inorder successor's data
						Node parent = temp.getParent();		//Store temp's parent
						if(parent.getRight()==temp){
							parent.setRight(null);
						}else if(parent.getLeft()==temp){
							parent.setLeft(null);
						}
						
					}else{														//This node is the leaf, and has no children
						//Cant be this, it's not a leaf  
					}
				}else if(n==-1){
					curr = curr.getLeft();
				}else if(n==1){
					curr = curr.getRight();
				}
			}
		}
		return new BinarySearchTree(newRoot);
	}
	
	/**
	 * Consumes a BinarySearchTree and returns a list of the Comparable objects in the tree in sorted order
	 * 
	 * @param tree The tree to list items from
	 * @return The list of items
	 */
	public LinkedList<Node> inOrder(BinarySearchTree tree){
		Node curr = tree.getRoot();
		LinkedList<Node> inOrder = new LinkedList<Node>();
		inOrder.addAll(parse(curr));
		return inOrder;
	}
	
	/**
	 * Parses the tree recursively. This is used exclusively
	 * to assist the inOrder method
	 * 
	 * @param n The node to parse
	 * @return A list containing the left, current, and right nodes in order
	 */
	public LinkedList<Node> parse(Node n){
		LinkedList<Node> list = new LinkedList<Node>();
		if(n!=null){
			list.addAll(parse(n.getLeft()));
			list.add(n);
			list.addAll(parse(n.getRight()));
		}
		return list;
	}
	
	/**
	 * Counts all values in a BinarySearchTree that are between x0 and x1. 
	 * 
	 * @param x0 Lower boundary
	 * @param x1 Upper boundary
	 * @return The amount of items whose values are between those boundaries
	 */
	@SuppressWarnings("unchecked")
	public int count(Comparable x0,Comparable x1){
		int count = 0;
		for(Node n : inOrder(this)){
			Comparable o = (Comparable) n.getData();
			int less = x1.compareTo(o); //if o<=x1, 0 or -1
			int grtr = x0.compareTo(o); //if o>=x0, 0 or 1
			if((less>0)&&(grtr>0)){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Returns the root
	 * @return the root
	 */
	public Node getRoot(){
		return root;
	}
}
