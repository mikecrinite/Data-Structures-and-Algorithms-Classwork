package postfixinfix2;

public class Lab4Test {

	public static void main(String[] args) {
		postFixTest();
		inFixTest();
		queueTest();
		queue2Test();
		System.exit(0);
	}
	
	public static void postFixTest(){
		System.out.println("Now testing the PostFix class: \n");
		System.out.println("Testing incorrect format: 	1 + 1");
		postFixMainNoUserInput("1 + 1");
		System.out.println("\nTesting correct format: 	1 3 +");
		postFixMainNoUserInput("1 3 +");
		System.out.println("\nTesting correct format: 	1 2 3 + * 4 /");
		postFixMainNoUserInput("1 2 3 + * 4 /");
		System.out.println("\nTesting incorrect format: 	+ 1 3");
		postFixMainNoUserInput("+ 1 3");
		System.out.println("\nTesting correct format: 	2 4 3 5 4 + - * /");
		postFixMainNoUserInput("2 4 3 5 4 + - * /");
	}
	
	public static void postFixMainNoUserInput(String expression){
		if(PostFix.isPostfix(expression)){
			System.out.println("Result: " + PostFix.evaluatePostfix(expression));
		}else{
			System.out.println("This expression is not formatted properly");
		}
	}
	
	public static void inFixTest(){
		System.out.println("\n\nNow testing the InFix class: \n");
		System.out.println("Testing incorrect format: 	1 + 1");
		inFixMainNoUserInput("1 + 1");
		System.out.println("\nTesting incorrect format:	((1 + 2) + 3))");
		inFixMainNoUserInput("((1 + 2) + 3))");
		System.out.println("\nTesting incorrect format:	(((1 + 2) + 3)");
		inFixMainNoUserInput("(((1 + 2) + 3)");
		System.out.println("\nTesting correct format: 	(1 + 1)");
		inFixMainNoUserInput("(1 + 1)");
		System.out.println("\nTesting correct format: 	((1 + 2) * (3 / 4))");
		inFixMainNoUserInput("((1 + 2) * (3 / 4))");
	}
	
	public static void inFixMainNoUserInput(String expression){
		if(Infix.isInfix(expression)==false){
			System.out.println("This is not formatted as an infix expression \nYou may be missing parentheses.");
		}else{
			System.out.println("Result: " + Infix.evaluateInfix(expression));
		}
	}
	
	public static void queueTest(){
		System.out.println("\n\nNow testing the Queue class: \n");
		try{
			Queue queue = new Queue();
			System.out.println("\nCreated a queue with default storage space (10)");
			int i = 1;
			System.out.println("\nEnqueueing 5 items into the queue: ");
			while(i<6){
				queue.enqueue((String) "" + i);
				System.out.println(queue.last());
				i++;
			}
			System.out.println("\nDequeueing 3 items from the queue: ");
			System.out.println("\n"+queue.dequeue()+"\n"+queue.dequeue()+"\n"+queue.dequeue()+"\n");
			System.out.println("\nEnqueueing 10 items into the queue: \n");
			while(i<16){
				queue.enqueue((String) "" + i);
				//System.out.println(queue.last());
				System.out.println(i);
				i++;
			}			
			//String a = "8";
			//queue.remove((String) a);
			System.out.println("\nPosition of 4: " + queue.positionOf("4"));
			System.out.println("Position of 12: " + queue.positionOf("12"));
			System.out.println("\n(Position starts count at 1)\n");
			
			System.out.println("\nDequeueing all items in the queue: ");
			while(!queue.isEmpty()){
				System.out.println(queue.dequeue());
			}
		}catch(QueueException e){
			
		}
	}
	
	public static void queue2Test(){
		System.out.println("\n\nNow testing the Queue2 class: \n");
		try{
			Queue queue = new Queue();
			System.out.println("\nCreated a queue with default storage space (10)");
			int i = 3;
			System.out.println("\nEnqueueing 5 items into the queue: ");
			while(i<8){
				queue.enqueue((String) "" + i);
				System.out.println(queue.last());
				i++;
			}
			System.out.println("\nDequeueing 3 items from the queue: ");
			System.out.println("\n"+queue.dequeue()+"\n"+queue.dequeue()+"\n"+queue.dequeue()+"\n");
			System.out.println("\nEnqueueing 10 items into the queue: \n");
			while(i<18){
				queue.enqueue((String) "" + i);
				//System.out.println(queue.last());
				System.out.println(i);
				i++;
			}			
			//String a = "8";
			//queue.remove((String) a);
			System.out.println("\nPosition of 8: " + queue.positionOf("8"));
			System.out.println("Position of 12: " + queue.positionOf("12"));
			System.out.println("\n(Position starts count at 1)\n");
			
			System.out.println("\nDequeueing all items in the queue: ");
			while(!queue.isEmpty()){
				System.out.println(queue.dequeue());
			}
		}catch(QueueException e){
			
		}
	}

}
