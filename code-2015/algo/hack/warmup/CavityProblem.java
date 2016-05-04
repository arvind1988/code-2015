package algo.hack.warmup;

import java.math.BigInteger;
import java.util.Scanner;

public class CavityProblem {

	
	public boolean isCavity(char[][] A, int i, int j, int n) {
		if (i == 0 || i == n - 1 || j == 0 || j == n) 
			return false;
		
		if (A[i][j] > A[i][j - 1] && A[i][j] > A[i - 1][j]
				&& A[i][j] > A[i][j + 1] && A[i][j] > A[i + 1][j]) 
			return true;
		 else
			return false;
	}

	public void cavity(int [] arr){
		String[] A = new String[arr.length];
		for(int i = 0; i < A.length; i++){
			A[i] = Integer.toString(arr[i]);
		}
		char[][] B = new char[arr.length][arr.length];
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr.length; j++){
				B[i][j] = A[i].charAt(j);
			}
		}
	}
	public void findAndReplaceCavity(char[][] A, int n) {

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {

				if (isCavity(A, i, j, n)) {
					A[i][j] = 'x';
				}
			}
		}
		

	}
	
	public static void main(String[] args) {
		
		char [] B = new char[]{'a','b'};
		for(int i = 0; i < B.length; i++){
			;
			System.out.println(B.toString());
		}
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();s.nextLine();
		char [][] A = new char [n][n];
		int i = 0;int k =n-1; int j = k;
		
		while(n > 0 && s.hasNext()){
			j = k;
			String d = s.nextLine();
			while(j >=0){
				A[i][j] = d.charAt(j);
				j--;
			}
			i++; n--;
		}
		s.close();
		n = k+1;
		CavityProblem cp = new CavityProblem();
		cp.findAndReplaceCavity(A, n);
		
		for(i = 0; i < n; i++){
			for(j = 0; j < n; j++){
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
		
			
		

	}

}
