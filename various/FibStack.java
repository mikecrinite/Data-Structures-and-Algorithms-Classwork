package various;

import java.util.Scanner;
import java.util.Stack;

public class FibStack {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter which fib u want: ");
		int n = s.nextInt();
		s.close();
		System.out.println(fib(n));
	}
	
	public static int fib(int n){
		Stack<Integer> s = new Stack<Integer>();
		s.push(0);
		s.push(1);
		n--;
		while(n>0){
			int num2 = s.pop();
			int num1 = s.pop();
			s.push(num2);
			s.push(num1 + num2);
			n--;
		}
		return s.pop();
	}
}
