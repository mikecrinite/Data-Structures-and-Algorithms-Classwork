package postfixinfix2;

/**
 * This class is an ADT stack which utilizes a circular Array
 * 
 * @author Michael Crinite
 * @version 03.07.16
 */
public class Queue {
	private Object[] queue;                 // Array-based implementation
	private boolean full = false;           // 
	private int front = 0;                  // Next to dequeue
	private int rear = 0;                   // Most-recently queued 
	
	/**
	 * Default Constructor
	 */
	public Queue(){
		queue = new Object[10];
	}
	
	/**
	 * Contructs a queue with designated max size
	 * @param size The max size of the queue
	 */
	public Queue(int size){
		queue = new Object[size];
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
		queue[rear] = o;
		rear = (rear + 1)%queue.length;
		isFull();
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
			Object o = queue[front];
			queue[front] = null;
			front = (front + 1)%queue.length;
			return o;
		}
	}
	
	/**
	 * Determines the amount of objects in the queue
	 * @return The amount of objects in the queue
	 */
	public int size(){
		if(isFull()){
			return queue.length;
		}else{
			return rear - front;
		}
	}
	
	/**
	 * Determines whether the queue is empty
	 * @return True if empty, false if not empty
	 */
	public boolean isEmpty(){
		if(queue[front]==null){
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
		if(isEmpty()){
			full = false;
		}//else if((rear + 1)%queue.length==(front)){
		else if(front == rear){
			full = true;
		}else{
			full = false;
		}
		return full;
	}
	
	/**
	 * Determines the position of the given object in the queue
	 * @param o The object to be searched for
	 * @return The object's position in the queue (first position is 1)
	 */
	public int positionOf(Object o){
		if(!isEmpty()){
			int i = 1; //Assuming the first spot in line has an index 1, instead of 0
			boolean found = false;
			for(int j = front; j!=rear && !found; j++){
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
			return queue[front];
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
			return queue[rear-1];
		}
	}
	
	/**
	 * If the queue is full, this method will expand the queue to 2 times its previous size.
	 */
	public void expandQ(){
		Object [] expandedQ = new Object[queue.length * 2];
		int index = front;
		int newRear = 0;
		for(int i = 0; i<queue.length; i++){
			expandedQ[i] = queue[index];
			index = (index + 1)%queue.length;
			newRear++;
		}
		front = 0;
		rear = newRear;
		queue = expandedQ;
	}
}


