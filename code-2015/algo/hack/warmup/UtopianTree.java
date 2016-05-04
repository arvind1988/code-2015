package algo.hack.warmup;

import java.util.Scanner;

public class UtopianTree {

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();
		int i = 0;
		
		int [] cycles = new int[T];
		while(i < T){
			if(sc.hasNextInt())
				cycles[i++] = sc.nextInt();
		}
		sc.close();
		UtopianTree ut = new UtopianTree();
		ut.findHeight(cycles);
	}
	
	public void findHeight(int [] N){
		if(N == null)
			throw new IllegalArgumentException();
		int height = 1;
		for(int i : N){
			height = 1;
			for(int j = 0; j < i; j++)
				height = (j % 2 == 0) ? height * 2 : height + 1;
			System.out.println(height);	
		}
	}
}
