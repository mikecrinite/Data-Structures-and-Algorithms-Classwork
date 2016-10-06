
public class Driver {

	public static void main(String[] args) {
		//Test 1
		System.out.println("Test adding 7 nodes and deleting the root (minimum): ");
		Heap heap = new Heap(4);	//Add 4
		heap.insert(1);						//Add 1 
		heap.insert(2);						//Add 2
		heap.insert(3);						//Add 3
		heap.insert(5);						//Add 5
		heap.insert(6);						//Add 6
		heap.insert(7);						//Add 7
		heap.deleteMin();					//Delete 1
		heap.printHeap();					//Print each level
		System.out.println("\n\nTest findMin(): " + heap.findMin().toString());
		System.out.println("\n\n\n");
		
		//Test 2
		System.out.println("Test adding 15 nodes and then deleting one in the middle: ");
		Heap heap2 = new Heap(10);//Add 10
		heap2.insert(8);					//Add 8
		heap2.insert(9);					//Add 9
		heap2.insert(11);					//Add 11
		heap2.insert(7);					//Add 7
		heap2.insert(12);					//Add 12
		heap2.insert(13);					//etc
		heap2.insert(20);
		heap2.insert(-1);
		heap2.insert(14);
		heap2.insert(18);
		heap2.insert(17);
		heap2.insert(15);
		heap2.insert(16);
		heap2.insert(14);
		Node del = new Node(7);
		heap2.delete(del);				//Delete node with 7 as data
		heap2.printHeap();
		System.out.println("\n\n\n");
		
		//Test 3
		System.out.println("Test adding 7 nodes out of order and sorting them using heapsort: ");
		Heap heap3 = new Heap(4);
		heap3.insert(3);
		heap3.insert(5);
		heap3.insert(2);
		heap3.insert(6);
		heap3.insert(1);
		heap3.insert(7);
		System.out.println("The heap after adding (4,3,5,2,6,1,7)");
		heap3.printHeap();
		System.out.println("\n\tThe sorted list: ");
		System.out.print("\t");
		while(!heap3.isEmpty()){
			System.out.print(heap3.findMin().toString() + "  ");
			heap3.deleteMin();
		}
		System.out.println("\n\n\n");
		
		//Test 4
		System.out.println("Test merging two heaps (result of first test and second test):");
		heap.union(heap2);
		heap.printHeap();
		System.out.println("\n\n\n");
		
		//Test 5
		System.out.println("Test both cases of descreaseKey: ");
		Heap heap4 = new Heap(2);
		heap4.insert(3);
		heap4.insert(4);
		heap4.insert(5);
		heap4.insert(6);
		heap4.insert(7);
		heap4.insert(8);
		Node n1 = new Node(7);
		Node n2 = new Node(2);
		heap4.decreaseKey(n1, 1);
		heap4.decreaseKey(n2, 9);
		heap4.printHeap();
	}
	

	

}
