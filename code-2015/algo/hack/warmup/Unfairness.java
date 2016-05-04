package algo.hack.warmup;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Unfairness {

	private static int findMinUnfairnessValue(int N, int K,
			int[] elements) {
		if (K < 2 || N < 2)
			throw new IllegalArgumentException();
		if (elements == null)
			throw new NullPointerException();
		Arrays.sort(elements, 0, N);
		int i = 0;
		int j = K - 1;
		int unfairness = Integer.MAX_VALUE;
		for(i = 0; i <= N - K; i++){
			j = i + K - 1;
			if((elements[j] - elements[i]) < unfairness){
				unfairness = elements[j] - elements[i];
			}
		}
		return unfairness;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		  int N = 0;
	      int K = 0;
	      Scanner sc = new Scanner(new File("input.txt"));
	      if(sc.hasNextInt())
	    	  N = sc.nextInt();
	      if(sc.hasNextInt())
	    	  K = sc.nextInt();
	      
	      int i = 0;
	      int[] elem = new int[N];
	      while(i < N){
	    	  if(sc.hasNext()){
	    		  elem[i++] = Integer.parseInt(sc.next());
	    	  }
	      }
	      sc.close();
	      System.out.println(findMinUnfairnessValue(N, K, elem));


	}

}
