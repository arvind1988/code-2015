package algo.arrayp;

import java.util.Arrays;

public class TwoSumProblem {

	public static void main(String[] args) {
		System.out.println(isSumPossible(new int[] { 18, 11, 21, 28, 31, 3840,
				55, 60, 62 }, 66));
	}

	static int isSumPossible(int[] a, int N) {
		int[] b = new int[a.length];
		int[] c = new int[2 * a.length];
		for(int i = 0; i < a.length; i++){
			b[i] = N - a[i];
		}
		Arrays.sort(a);
		Arrays.sort(b);
		int i = 0;
		int j = 0; 
		int k = 0;
		while(i < a.length || j < b.length){
			if(i == a.length){
				c[k++] = b[j++];	
			}else if(j == b.length){
				c[k++] = a[i++];
			}else{
				if(a[i] <= b[j]){
					c[k++] = a[i++];
				}else{
					c[k++] = b[j++];
				}
			}
		}
		int key = c[0];
		for(i = 1; i < c.length; i++){
			if(c[i] == key){
				return 1;
			}else
				key = c[i];
		}
		return 0;
	}
}
