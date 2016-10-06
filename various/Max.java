package various;

public class Max {
	private static int[] a = {2,3,31,5,34,7,11,29};
	
	public static void main(String[] args){
		Max m = new Max();
		System.out.println(m.max(a, 0, 0));
		//System.out.println(m.largest(a, 0, 0));
	}
	
	public Max(){}
	
	public int max(int[] a, int index, int max){
		if(index == a.length){
			return max;
		}else{
			if(a[index]>max){
				max = a[index];
			}
			return max(a, index + 1, max);
		}
	}
	
	public int largest(int[] a, int start, int largest){
		if(start == a.length){
			return largest;
		}else{
			int l = (a[start] > largest ? a[start] : largest);
			return largest(a, start + 1, l);
		}
	}
	
}
