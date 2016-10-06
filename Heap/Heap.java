import java.util.LinkedList;
import java.util.Queue;

/********************************************************************************
 * A generic Heap class which utilizes Nodes and a linked structure in order to *
 * maintain a minimum heap.                                                     *
 *                                                                              *
 *       1                                                                      *
 *      / \                                                                     *
 *     /   \                                                                    *
 *    /     \                                                                   *
 *   2       3                                                                  *
 *  / \     / \                                                                 *
 * 4   5   6   7                                                                *
 *                                                                              *
 * The indexes of the heap are shown above. The first index is 1 because of the *
 * implementation of later methods which require it to be so.                   *
 *                                                                              *
 * @author Michael Crinite                                                      *
 * @version 1.0                                                                 *
 * 04.14.2016                                                                   *
 ********************************************************************************/
@SuppressWarnings("rawtypes")
public class Heap implements MyHeap{
	private Node root;															// The root node
	private int size;																// The current size of the heap.
	
	/***************************************************************
	 * Constructor of Heap class which utilizes makeHeap
	 * @param value the data from which to create the root node.
	 ***************************************************************/
	public Heap(Comparable value){
		makeHeap(value);
	}
	
	/***************************************************************
	 * Creates a heap with a single node which uses the parameter value as its data.
	 * @param value The data from which to create the root node of the new heap
	 ***************************************************************/
	public Node makeHeap(Comparable value){
		root = new Node(value);
		size = 1;
		return root;
	}
	
	/***************************************************************
	 * Tests to see whether the heap is empty
	 * @return true if empty, false if the heap contains any Nodes
	 ***************************************************************/
	public boolean isEmpty(){
		if(root==null){
			return true;
		}else{
			return false;
		}
	}
	
	/***************************************************************
	 * Inserts a single node into the heap
	 * @param value The data from which to create the new Node
	 * @return true
	 ***************************************************************/
	public boolean insert(Comparable value){
		size++; 																			// Increase the value of size
		String bi = Integer.toBinaryString(size); 		// Use in traversing to find the next insert location
		Node n = new Node(value);
		Node curr = root;
		for(int i = 1; i<bi.length()-1; i++){					// Disregard first digit of binary code
			if(bi.charAt(i)=='0'){
				curr = curr.getLeftChild();								// 0 means traverse to left child
			}else{
				curr = curr.getRightChild();              // 1 means traverse to right child
			}
		}
		if(bi.endsWith("0")){
			curr.setLeftChild(n);
			n.setParent(curr);
		}else{
			curr.setRightChild(n);
			n.setParent(curr);
		}
		
		// The new node is in the right spot, but the value may not be correct
		siftUp(n);
		return true;
	}
	
	/***************************************************************
	 * Makes sure the heap remains a minimum heap by moving lesser Nodes toward the top of the structure
	 * @param n The node to tentatively move
	 ***************************************************************/
	public void siftUp(Node n){
		if(n.getParent()!=null){
			Node p = n.getParent();
			int c = n.getData().compareTo(p.getData());
			if(c<0){  // n < p (The parent value should be less than the child value)
				Comparable cp = p.getData();
				Comparable cn = n.getData();
				n.setData(cp);
				p.setData(cn);
				siftUp(p);
			}
		}
	}
	
	/***************************************************************
	 * Makes sure the heap remains a minimum heap by moving greater
	 *  Nodes toward the bottom of the structure
	 * @param n The Node to tentatively move
	 ***************************************************************/
	public void siftDown(Node n){
		int c = 0;
		Node l = n.getLeftChild();
		Node r = n.getRightChild();
		Comparable cm; //Data to move
		Comparable cn = n.getData();
		Node m = l;
		if(l!=null){
			if(r!=null){
				Comparable cl = l.getData();
				Comparable cr = r.getData();
				if(cr.compareTo(cl)<0){
					m = r;
				}//We now have the smaller child's value
			}//If r=null, l is already set as new Node
			cm = m.getData();
			if(cn.compareTo(cm)>0){
				n.setData(cm);
				m.setData(cn);
				siftDown(m);
			}
		}
	}
	
	/***************************************************************
	 * Retrieves a node from the heap at the given position
	 * @param which The location of the heap to retrieve
	 * @return
	 ***************************************************************/
	public Node get(int which){
		String bi = Integer.toBinaryString(which); 		// Use in traversing to find the next insert location
		Node curr = root;
		for(int i = 1; i<bi.length(); i++){				  	// Disregard first digit of binary code
			if(bi.charAt(i)=='0'){
				curr = curr.getLeftChild();								// 0 means traverse to left child
			}else{
				curr = curr.getRightChild();              // 1 means traverse to right child
			}
		}
		return curr;
	}
	
	/***************************************************************
	 * Removes the minimum Node (the root) and shifts other Nodes
	 * accordingly
	 * @return true
	 ***************************************************************/
	public boolean deleteMin(){
		return delete(root);
	}
	
	/***************************************************************
	 * Decreases the value of a single Node and moves other Nodes
	 * accordingly
	 * @return true
	 ***************************************************************/
	public boolean decreaseKey(Node key, Comparable updateValue){
		Node n = findMatch(key);
		Comparable temp = n.getData();
		n.setData(updateValue);
		int c = temp.compareTo(updateValue);
			if(c<0){ //updateValue is higher, must sift down
				siftDown(n);
			}else if(c>0){
				siftUp(n);
			}
		return true;
	}
	
	/***************************************************************
	 * Deletes a single designated Node at any location and moves
	 * other Nodes accordingly
	 * @param del The Node to find and delete
	 * @return true
	 ***************************************************************/
	public boolean delete(Node del){
		Node swap = get(size);                                //Have to move the last Node
		Node delete = findMatch(del);													//delete = Node that matches del
		if(swap!=root){																				//if swap isn't the root
			delete.setData(swap.getData());											//the node to be deleted needs the new Node's data
			if(swap.getParent().getRightChild()==swap){					//If swap was the right child
				swap.getParent().setRightChild(null);  				  	//Swap's parent's right child is now null
			}else{																							//If swap was the left child
				swap.getParent().setLeftChild(null);							//Swap's parent's left child is now null
			}
			swap.setParent(null);																//Swap has no parents
			swap.setLeftChild(null);														//Swap has no children
			swap.setRightChild(null);														//Swap has been erased
		}else{
			root = null;
		}
		size--;
		siftDown(delete);
		return true;
	}
	
	/***************************************************************
	 * Merges two heaps together
	 * @param heap The heap to merge with the current heap
	 * @return true
	 ***************************************************************/
	public boolean union(MyHeap heap){
		while(!heap.isEmpty()){
			insert(heap.findMin());
			heap.deleteMin();
		}
		return true;
	}
	
	/***************************************************************
	 * Returns the root, which contains the minimum value.
	 * @return The root, which contains the minimum value.
	 ***************************************************************/
	public Comparable findMin(){
		return root.getData();
	}
	
	/***************************************************************
	 * Searches the heap for a matching Node
	 * @param key The Node to match
	 * @return The Node in the heap which matches the key node
	 ***************************************************************/
	public Node findMatch(Node key){
		int i=1;
		boolean found = false;
		Node n = null;
		while(i<=size && !found){
			n = get(i);
			if(key.getData().equals(n.getData())){
				found = true;
			}
			i++;
		}
		return n;
	}
	
	/***************************************************************
	 * Prints the heap in a somewhat-formatted-by-level manner.
	 * Useful for test purposes, but not very useful yet for 
	 * actually visualizing the structure of the heap, and 
	 * parent-child links.
	 ***************************************************************/
	public void printHeap(){
		Queue<Node> queue = new LinkedList<Node>();
		for(int index = 1; index <= size; index++){
			queue.add(get(index));
		}
		
		int i = 0;			
		int depth = 0;
		while(!queue.isEmpty()){
			i++;
			System.out.print(queue.remove().getData() + "  ");
			if(i == Math.pow(2, depth)){
				System.out.println("\n"); //prints 2 new lines
				depth++;
				i = 0;
			}
		}
	}
}