package postfixinfix2;

import java.util.Scanner;

/**
 * This class determines whether an expression is properly formatted as an infix expression.
 * It then converts the expression to a PostFix expression and calculates the result.
 * 
 * @author Mike
 *
 */
public class Infix {
	
	public static void main(String[] args) {
		System.out.println("This program will evaluate any fully-parenthesized infix expression."
				+ "\nAn example of the correct format is ((1 + 2) * (3 / 4))"
				+ "\nValid operators are: (+, -, *, /)");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a fully-parenthesized infix expression");
		String expression = s.nextLine();
		s.close();
		
		if(isInfix(expression)==false){
			System.out.println("This is not formatted as an infix expression \nYou may be missing parentheses.");
		}else{
			System.out.println("Result: " + evaluateInfix(expression));
		}
		System.exit(0);
	}
	


	public static boolean isInfix(String expression){                //Reused, because I ran out of time.
		//Add spaces in order to make separating the parentheses easier.
		//expression.replaceAll("(", "$0 ");
		//expression.replaceAll(")", " $0");	
		expression = reformat(expression);
		//Split the string into an array of strings
		String[] exp = expression.trim().split("\\s+");
		//look for a ")"
		boolean isInfix = false; //Can be set to true later
		boolean flag = true;
		int i = 0;               //index for parsing exp
		while(flag){
			if(i<=exp.length && exp[i].equals(")")){
				if(i<4){
					flag = false;
				}else if(!exp[i-4].equals("(")){
					System.out.println("There is not an even amount of parentheses");
				}else{
					//For this expression to be infix, exactly 4 indexes previous to the
					//closed parenthesis must be an open parenthesis. For (A + B), it creates [(, A, +, B, )] 
					//Fill the space in the array occupied by the open parenthesis with the parenthetical expression
					for(int temp = i-3; temp<=i; temp++){
						exp[i-4] = exp[i-4].concat(exp[temp]);
						exp[temp] = "";
					}
					//Move the entries down
					for(int temp = i-3; temp<exp.length-4; temp++){
						exp[temp]=exp[temp+4];
						exp[temp+4]="";
					}
					isInfix = true;
					i=0;
				}
			}else if(exp[i].equals("")){
				flag = false; //End of program
			}else if(i==exp.length-1){
				flag = false; //reached the end of the Array
			}else{
				i++;
			}
		}
		if(!exp[1].equals("")){
			return false;
		}else{
			//System.out.println("" + exp[0]);    //Save to test successful functionality
			return isInfix;
		}
	}
	
	/**
	 * This method will convert the Infix expression to a PostFix expression
	 * and evaluate the expression using the PostFix class
	 * 
	 * @param expression The properly-formatted Infix expression
	 * @return The result of the expression
	 */
	public static float evaluateInfix(String expression){
		Stack stack = new Stack();
		expression = reformat(expression);
		String[] exp = expression.split("\\s+");
		try{
			for(int i = 0; i<exp.length; i++){
				if(exp[i].equals(")")){
					String op2 = (String) stack.pop();
					String operator = (String) stack.pop();
					String op1 = (String) stack.pop();
					stack.pop(); //remove opening parentheses
					stack.push("" + op1 + " " + op2 + " " + operator);
				}else if(exp[i].equals(" ")){
					//Do nothing
				}else{
					stack.push(exp[i]);
				}
			}
			return PostFix.evaluatePostfix((String) stack.pop());
		}catch(StackException e){
			System.out.println("An error occurred.");
			return new Float(0);
		}
	}
	
	/**
	 * Reformats the given string by adding spaces after each opening parenthesis 
	 * and before each closing parenthesis, because I couldn't figure out the correct
	 * regex to replaceAll in the String
	 * 
	 * @param expression The String to reformat
	 * @return The reformatted String
	 */
	public static String reformat(String expression){
		String[] temp = new String[expression.length()*2];
		int i = 0;
		for(String s : expression.split("")){
			if(s.equals("(")){
				temp[i]="(";
				i++;
				temp[i]=" ";
			}else if(s.equals(")")){
				temp[i]=" ";
				i++;
				temp[i]=")";
			}else{
				temp[i]=s;
			}
			i++;
		}
		String newExp = "";
		for(String s : temp){
			if(s!=null){
				newExp = newExp + s;
			}
		}
		return newExp;
	}
}
