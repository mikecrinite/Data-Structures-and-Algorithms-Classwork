package postfixinfix2;

import java.util.Scanner;

/**
 * This class utilizes a user-created stack class to evaluate a PostFix expression
 * 
 * @author Michael Crinite
 * @version 02.25.2016
 */
public class PostFix {
	public static void main(String[] args){
		System.out.println("This program will evaluate any properly-formatted postfix expression."
				+ "\nAn example of the correct format is 1 2 3 + * 4 /"
				+ "\nValid operators are: (+, -, *, /)");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a postfix expression");
		String expression = s.nextLine();
		s.close();
		
		if(isPostfix(expression)){
			System.out.println("Result: " + evaluatePostfix(expression));
		}else{
			System.out.println("This expression is not formatted properly");
		}
	}
	
	public static boolean isPostfix(String expression){
		boolean isPostfix = false;
		Stack stack = new Stack();
		String[] exp = expression.split("\\s+");
		for(int i=0; i<exp.length; i++){
			if(exp[i]==null){
				return false;
			}else if(exp[i].equals("+")
					|| exp[i].equals("-")
					|| exp[i].equals("*")
					|| exp[i].equals("/")){
				try {
					String re = "" + stack.pop() + stack.pop();
					stack.push(re);
				} catch (StackException e) {
					return false;
				}
				isPostfix = true;
			}else{
				try {
					stack.push(exp[i]);
				} catch (StackException e) {
					return false;
				}
			}
		}
		return isPostfix;
	}
	
	public static float evaluatePostfix(String expression){
		Stack stack = new Stack();
		String[] exp = expression.split("\\s+");
		try{
			for(int i = 0; i<exp.length; i++){
				if(exp[i].equals("+")){
					float op2 = Float.parseFloat((String)stack.pop());
					float op1 = Float.parseFloat((String)stack.pop());
					stack.push("" + (op1 + op2));
				}else if(exp[i].equals("-")){
					float op2 = Float.parseFloat((String)stack.pop());
					float op1 = Float.parseFloat((String)stack.pop());
					stack.push("" + (op1 - op2));
				}else if(exp[i].equals("*")){
					float op2 = Float.parseFloat((String)stack.pop());
					float op1 = Float.parseFloat((String)stack.pop());
					stack.push("" + (op1 * op2));
				}else if(exp[i].equals("/")){				
					float op2 = Float.parseFloat((String)stack.pop());
					float op1 = Float.parseFloat((String)stack.pop());
					stack.push("" + (op1 / op2));
				}else{
					stack.push(exp[i]);
				}
			}
			return Float.parseFloat((String)stack.pop());
		}catch(StackException e){
			System.out.println("Encountered a Stack Exception");
			return new Float(0);
		}
	}
}
