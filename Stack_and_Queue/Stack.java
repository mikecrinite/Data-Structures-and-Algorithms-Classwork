package postfixinfix2;

/**
 * This class is an ADT stack that utilizes an array implementation
 * 
 * @author Michael Crinite
 * @version 02.25.2016
 */
public class Stack {
	private Object[] stack = new Object[10];    // An array to be used as a stack. Default size 10
	private int size = 10;                      // Current size of the stack
	private int top = -1;                       // Current index of the top of the stack
	
	/**
	 * Creates an empty stack object
	 */
	public Stack(){
		//stack already initialized
	}
	
	public Stack createStack(){
		Stack s = new Stack();
		return s;
	}
	
	/**
	 * Checks if the stack is empty. 
	 * @return true if empty, false if it contains any value
	 */
	public boolean isEmpty(){
		boolean empty = false;
		if(stack[0]==null){
			return true;
		}else{
			for(int i=0; i<size; i++){
				if(stack[i]!=""){
					empty = false;
				}else{
					empty = true;
				}
			}
		}
		return empty;
	}
	
	/**
	 * Pushes an object onto the stack. If the stack is full, this method
	 * extends the size of the stack and then adds the object.
	 * 
	 * @param o The object to add to the stack
	 */
	public void push(Object o) throws StackException{ 
		if(top<size){
			top++;
			stack[top] = o;
		}else{
			Object[] temp = new Object[size*2];    //create temporary array with double size
			for(int i = 0; i<size; i++){           
				temp[i] = stack[i];                  //dump objects onto new stack in same order
			}
			stack = temp;                          //assign new stack to stack variable
			size = size*2;
			top ++; 
			stack[top] = o;                        //push object onto stack                               
		}
	}
	
	/**
	 * Returns and removes the object on the top of the stack
	 * 
	 * @return The object on the top of the stack
	 * @throws StackException If stack is empty
	 */
	public Object pop() throws StackException{
		Object popped = peek();                   //store object
		stack[top] = null;                        //remove object
		top--;                                    //decrement
		return popped;
	}
	
	/**
	 * Pops all the items off the stack and returns them as a list
	 * @return The list of all items on the stack
	 */
	public Object[] popAll(){
		Object [] temp = stack;
		stack = new Object[size];
		top = -1;
		return temp;
	}
	
	/**
	 * Returns the top item on the stack
	 * @return The top item on the stack
	 * @throws StackException If stack is empty
	 */
	public Object peek() throws StackException{
		if(isEmpty()){
			throw new StackException("The stack is empty");
		}else{
			return stack[top];
		}
	}
}


