package postfixinfix2;

/**
 * This class is an ADT stack which utilizes a circular Array.
 * This implementation of queue uses a variable MAX_QUEUE which does not reflect the logical size of the queue
 * but rather is a representation of the maximum amount of items the queue can store. One space in the array is 
 * permanently empty as a placeholder for the front pointer. The queue is empty when the rear pointer points to
 * the same location as the front pointer, and consequently, this means the rear pointer must point to the last
 * item in the array.
 * 
 * @author Michael Crinite
 * @version 03.07.16
 */
public class Queue2 {
	private Object[] queue;                 // Array-based implementation
	private int front = 0;                  // Points to an empty location before the first object in the queue
	private int rear = 0;                   // Index of item most-recently added to the queue
	private int MAX_QUEUE;                  // Maximum amount of items the queue can hold
	
	/**
	 * Default Constructor
	 */
	public Queue2(){
		queue = new Object[10];
	}
	
	/**
	 * Contructs a queue with designated max size
	 * @param size The max size of the queue
	 */
	public Queue2(int size){
		queue = new Object[size + 1];    // Leaves the first spot open for front placeholder
		MAX_QUEUE = size;                // The amount of items that can be stored is not the same as the array length
	}
	
	/**
	 * Adds an object to the back of the queue
	 * @param o Object to add
	 * @throws QueueException If the queue is full
	 */
	public void enqueue(Object o) throws QueueException{
		if(isFull()){
			expandQ();
		} 
		rear = (rear + 1)%queue.length;  // rear must be incremented before the object is created      
		queue[rear] = o; 
	}
	
	/**
	 * Removes and return the object at the head of the queue
	 * @return The removed object
	 * @throws QueueException If there are no objects in the queue
	 */
	public Object dequeue() throws QueueException{
		if(isEmpty()){
			throw new QueueException("Queue is empty");
		}else{
			Object o = queue[(front + 1)%queue.length];      // Get item to the right of the front pointer
			queue[(front + 1)%queue.length] = null;          // null that item's space
			front = (front + 1)%queue.length;                // Increment front shouldn't differ in this implementation
			return o;
		}
	}
	
	/**
	 * Determines the amount of objects in the queue
	 * @return The amount of objects in the queue
	 */
	public int size(){
		if(isFull()){
			return MAX_QUEUE;
		}else{
			return (rear - front)%queue.length;
		}
	}
	
	/**
	 * Determines whether the queue is empty
	 * @return True if empty, false if not empty
	 */
	public boolean isEmpty(){
		if(queue[front+1]==null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Determines whether the queue is full
	 * @return True if full, false if not full
	 */
	public boolean isFull(){
		if(front == (rear + 1)%(MAX_QUEUE + 1)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Determines the position of the given object in the queue
	 * @param o The object to be searched for
	 * @return The object's position in the queue (first position is 1)
	 */
	public int positionOf(Object o){
		if(!isEmpty()){
			int i = 0; //Assuming the first spot in line has an index 1, instead of 0, since front is null, we can start at 0
			boolean found = false;
			for(int j = front; j<=rear && !found; j++){
				if(queue[j].equals(o)){
					found = true;
				}else{
					i++;
				}
			}
			return i;
		}else{
			return -1;
		}
	}
	
	/**
	 * Removes all the objects in the queue
	 */
	public void clear() throws QueueException{
		if(isEmpty()){
			throw new QueueException("Queue is empty");
		}else{
			queue = new Object[queue.length];
			front = 0;
			rear = 0;
		}
	}
	
	/**
	 * Removes the specified object from the queue
	 * @param o The object to be removed
	 * @throws QueueException If there are no objects in the queue
	 */
	public void remove(Object o) throws QueueException{
		if(isEmpty()){
			throw new QueueException("No objects to remove");
		}else{
			Object[] newQ = new Object[queue.length];
			int j = 0;
			boolean found = false;
			for(int i=0; i<queue.length; i++){
				if(!queue[i].equals(o)){
					newQ[j] = queue[i];
					j++;
				}else{
					found = true;
				}
			}
			queue = newQ;
			if(found){
				rear = (rear-1)%queue.length;
			}
		}
	}
	
	/**
	 * Returns (but does not remove) the item at the front of the queue
	 * @return The item at the front of the queue
	 * @throws QueueException If there are no items in the queue
	 */
	public Object first() throws QueueException{
		if(isEmpty()){
			throw new QueueException("The queue is empty");
		}else{
			return queue[(front + 1) % queue.length];
		}
	}
	
	/**
	 * Returns (but does not remove) the item at the back of the queue
	 * @return The item at the back of the queue
	 * @throws QueueException If the queue is empty
	 */
	public Object last() throws QueueException{
		if(isEmpty()){
			throw new QueueException("The queue is empty");
		}else{
			return queue[rear];
		}
	}
	
	/**
	 * If the queue is full, this method will expand the queue to 2 times its previous size.
	 */
	public void expandQ(){
		Object [] expandedQ = new Object[queue.length * 2];
		MAX_QUEUE = (MAX_QUEUE * 2) + 1;
		int count = size();
		for(int i = 0; i<size(); i++){
			expandedQ[i]=queue[front];
			front = (front + 1)%queue.length;
		}
		front = 0;
		rear = count;
		queue = expandedQ;
	}
}


