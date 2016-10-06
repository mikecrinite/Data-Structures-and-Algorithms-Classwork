package pkg;
/**
 * 
 * @author Michael Crinite
 *
 */
@SuppressWarnings("rawtypes")
public class Node{
	private Comparable data; 				  // An object which can be compared
	private Node parent;              
	private Node left;                 
	private Node right;
	
	public Node(Comparable data){
		this.data = data;
		parent = null;
		left = null;
		right = null;
	}
	
	public Node(Comparable data, Node parent, Node left, Node right){
		this.data = data;
		this.parent = parent;
		left = null;
		right = null;
	}
	
	public boolean isRoot(){
		if(parent==null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isLeaf(){
		if(left==null && right==null){
			return true;
		}else{
			return false;
		}
	}

	public Comparable getData() {
		return data;
	}

	public void setData(Comparable data) {
		this.data = data;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		//Node l = new Node(left, this, null, null);
		//this.left = l;
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		//Node r = new Node(right, this, null, null);
		//this.right = r;
		this.right = right;
	}
	
	public Node getParent(){
			return parent;	
	}

	public int compare(Node n) {
		return data.compareTo(n.getData());
	}

	public void setParent(Node parent) {
		this.parent = parent;
		
	}
}
