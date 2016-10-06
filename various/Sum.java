package various;

import java.util.Scanner;

public class Sum {
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.close();
		System.out.print(s(n));
	}
	
	public static int s(int n){
		if(n>0){
			return n+s(n-1);
		}else{
			return 0;
		}
	}
}
