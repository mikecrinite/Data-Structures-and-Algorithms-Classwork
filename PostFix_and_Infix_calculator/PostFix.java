package postfixinfix;

import java.util.Scanner;

/**
 * This class will recognize and evaluate postfix expressions. 
 * 
 * @author Michael Crinite
 * @version 02/18/2016
 */
public class PostFix {

	public static void main(String[] args) {
		System.out.println("This program will evaluate any properly-formatted postfix expression."
				+ "\nAn example of the correct format is 1 2 3 + * 4 /"
				+ "\nValid operators are: (+, -, *, /, ^)");
		Scanner s = new Scanner(System.in);
		System.out.println("Enter a postfix expression");
		String expression = s.nextLine();
		s.close();
		
		if(isPostfix(expression)==false){
			System.out.println("This is not formatted as a postfix expression");
		}else{
			System.out.println("Result: " + evaluatePostfix(expression));
		}
		System.exit(0);
	}
	
	/**
	 * Parses the expression to confirm that it is a proper 
	 * postfix expression. EX: A B C + * D /  = (A (B C + ) * ) D /
	 * 
	 * @return True if properly formatted in postfix form
	 */
	public static boolean isPostfix(String expression){
		boolean isPostfix = false;
		//Search through String array
		String[] temp = new String[expression.length()];
		String[] exp = expression.trim().split("\\s+"); //Was just String [] don't know if that space matters
		for(int tempIndex = 0; tempIndex < temp.length; tempIndex ++){
			if(tempIndex < exp.length && exp[tempIndex]!=null){
				temp[tempIndex] = exp[tempIndex];
			}else if(tempIndex>exp.length){
				temp[tempIndex] = "";
			}		
		}
		boolean flag = true;
		int i = 0;
		String s = "";
		while(flag){
			if(temp[i]!=null){
				s = temp[i];
			}else{
				flag = false;
			}
			if(s.equals("")){
				//It has encountered the end of the expression
				//Exit the while loop
				flag = false;
			}else if(s.equals("+") 
					|| s.equals("-") 
					|| s.equals("*") 
					|| s.equals("/")
					|| s.equals("^")){
				if(i<2){
					//This means that there is a maximum of 1 operand before the operator
					//If there is only 1 operand, it is not formatted as a postfix
					isPostfix = false;
					return false;
				}else{
					//If s is an operator (and this is the third index, or else it cannot be valid)
					//There must be two operands previous to this point, so...
					isPostfix = true; //for now, it is formatted correctly
					//combine and recurse
					temp[i-2] = "" + temp[i-2] + temp[i-1] + s; //combine
					for(int index = i-1; index< temp.length - 2; index++){
						temp[index] = temp[index+2];              //move the rest of the entries
						temp[index+2] = "";                       //clear that space.
					}
					i=0; //Start over
				}
			}else{
				//Do nothing, continue
				i++;
			}
		}	
		return isPostfix;
	}
	
	/**
	 * Evaluates the expression
	 * 
	 * @return The value of the evaluated expression
	 */
	public static float evaluatePostfix(String expression){
		String[] temp = new String[expression.length()];
		String [] exp = expression.trim().split("\\s+");
		for(int tempIndex = 0; tempIndex < temp.length; tempIndex ++){
			if(tempIndex < exp.length && exp[tempIndex]!=null){
				temp[tempIndex] = exp[tempIndex];
			}else if(tempIndex>exp.length){
				temp[tempIndex] = "";
			}		
		}
		boolean flag = true;
		int i = 0;
		String s = "";
		while(flag){
			if(temp[i]!=null){
				s = temp[i];
				if(s.equals("")){
					flag = false;
				}else if(s.equals("+")
						|| s.equals("-")
						|| s.equals("*")
						|| s.equals("/")
						|| s.equals("^")){
					if(s.equals("+")){
						temp[i-2] = "" + (Float.parseFloat(temp[i-2])+Float.parseFloat(temp[i-1]));
					}else if(s.equals("-")){
						temp[i-2] = "" + (Float.parseFloat(temp[i-2])-Float.parseFloat(temp[i-1]));
					}else if(s.equals("*")){
						temp[i-2] = "" + (Float.parseFloat(temp[i-2])*Float.parseFloat(temp[i-1]));
					}else if(s.equals("/")){
						temp[i-2] = "" + (Float.parseFloat(temp[i-2])/Float.parseFloat(temp[i-1]));
					}else if(s.equals("^")){
						temp[i-2] = "" + Math.pow(Float.parseFloat(temp[i-2]),Float.parseFloat(temp[i-1]));
					}else{
						System.out.println("Your input contains an invalid operator");
						System.exit(0);
					}
						for(int index = i-1; index< temp.length - 2; index++){
							temp[index] = temp[index+2];              
							temp[index+2] = "";     
						}
						i=0;
					}else{
						i++;
					}
				}else{
					flag = false;
				}
			}
			return Float.valueOf(temp[0]);
		}
}
