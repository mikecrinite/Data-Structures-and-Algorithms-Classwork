package postfixinfix;

import java.util.Scanner;

/**
 * This class evaluates any fully-parenthesized infix expression.
 * An example of the correct format, as given by the lab description, is ((1 + 2) * (3 / 4))
 * 
 * @author Michael Crinite
 * @version 2.25.2016 
 */
public class InFix {

	public static void main(String[] args) {
		System.out.println("This program will evaluate any fully-parenthesized infix expression."
				+ "\nAn example of the correct format is ((1 + 2) * (3 / 4))"
				+ "\nValid operators are: (+, -, *, /, ^)");
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
	
	/**
	 * Determines whether the expression is properly formatted as an infix-notation expression
	 * The expression must be fully parenthesized. (e.g. ((A + B) + (C + D))   )
	 * 
	 * @param expression The expression to test
	 * @return True if formatted as an infix
	 */
	public static boolean isInfix(String expression){
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
	
	public static Float evaluateInfix(String expression){
		expression = reformat(expression);
		String[] exp = expression.trim().split("\\s+");
		boolean flag = true;
		int i = 0;               //index for parsing exp
		while(flag){
			if(i<=exp.length && exp[i].equals(")")){
				if(i<4){
					flag = false;
				}else if(!exp[i-4].equals("(")){
					System.out.println("There is not an even amount of parentheses");
				}else{
					//Fill the space in the array occupied by the open parenthesis with the result of the expression
					float op1 = Float.parseFloat(exp[i-3]);               //Operator 1
					String op = exp[i-2];                                 //Operand
					float op2 = Float.parseFloat(exp[i-1]);               //Operator 2
					float re = 0;                                         //Result
					if(op.equals("+")){
						re = op1 + op2;
					}else if(op.equals("-")){
						re = op1 - op2;
					}else if(op.equals("*")){
						re = op1 * op2;
					}else if(op.equals("/")){
						re = op1 / op2;
					}else if(op.equals("^")){
						re = Float.parseFloat("" + Math.pow(op1, op2));  //hashtag ghetto solution
					}else{
						System.out.println("You have entered an invalid operator");
						System.out.println("Valid operators are: (+, -, *, /, ^)");
						System.exit(0);
					}
						for(int temp = i-3; temp<=i; temp++){
							exp[i-4] = "" + re;
							exp[temp] = "";
						}
					
					//Move the entries down
					for(int temp = i-3; temp<exp.length-4; temp++){
						exp[temp]=exp[temp+4];
						exp[temp+4]="";
					}
					i=0;
				}
			}else if(exp[i].equals("")){
				flag = false;
			}else{
				i++;
			}
		}		
		return Float.parseFloat(exp[0]);	
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
