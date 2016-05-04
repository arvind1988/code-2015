package algo.hack.warmup;

import java.util.Scanner;

public class SherlockArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine();
		
		int [][] A = new int[T][];
		int i = 0;int j = 0;
		while(i < T && sc.hasNextLine()){
			if(sc.hasNextInt()){
				int n = sc.nextInt();
				A[i] = new int [n];
				sc.nextLine();
			}
			String array = sc.nextLine();
			//System.out.println(array);
			String [] elements = array.split(" ");
			for(j = 0; j < A[i].length; j++){
				A[i][j] = Integer.parseInt(elements[j]);
			}
			i++;
			
		}
		sc.close();
		/*for(i = 0; i < T; i++){
			for(j = 0; j < A[i].length; j++){
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}*/
		findElement(A, T);
		
	}
	
	public static void findElement(int [][] A, int T){
		System.out.println("T = " + T);
		int i = 0; int j = 0; int k = 0;
		int leftSum = 0;boolean isFound = false;
		int rightSum = 0;
		for(i = 0; i < T; i++){
			
			for(j = 0; j < A[i].length; j++){
				rightSum = 0;
				leftSum = 0;
				for(k  = j - 1; k >= 0; k--){
					leftSum += A[i][k];
				}
				for(k  = j + 1; k < A[i].length; k++){
					rightSum += A[i][k];
				}
				if(leftSum == rightSum){
					isFound = true;
					System.out.println("YES");
					break;
				}
			}
			if(!isFound){
				System.out.println("NO");
			}else{
				isFound = false;
			}
				
		}
	}

}
