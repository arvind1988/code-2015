package algo.hack.warmup;

import java.util.Arrays;
import java.util.Scanner;

public class CutTheSticks {

	
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		s.nextLine();
		int [] A = new int[n];
		int i = 0;
		
		String cuts = s.nextLine();
        String[] next_split = cuts.split(" ");
        
        for(i = 0; i < n; i++) {
           A[i] = Integer.parseInt(next_split[i]);
           
        }
		s.close();
		getCuts(A, n);
		
	}
	

	public static void getCuts(int [] A, int n){
		
		System.out.println(n);
		
		Arrays.sort(A);
		int i = 0; int j;
		for(i = 0; i < A.length && n > 1;){
			int key = A[i];
			j = i + 1;
			while(j < A.length && key ==  A[j]){
				j++;
			}
			if(n-(j-i) != 0){
				System.out.println(n-(j-i));
			}
			
			n = n - (j - i);
			i = j;
		}
		
		
	}

}
