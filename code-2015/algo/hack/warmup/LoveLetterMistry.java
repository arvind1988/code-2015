package algo.hack.warmup;

import java.util.Scanner;

public class LoveLetterMistry {

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T  = sc.nextInt();
		String[] words = new String[T];
		int i = 0;
		while(i < T && sc.hasNext()){
			words[i++] = sc.next(); 
		}
		sc.close();
		countMinSteps(words);
	}
	
	public static void countMinSteps(String[] words) {
		if (words == null)
			throw new NullPointerException();
		int i;
		int j;
		int countSteps = 0;
		for (String w : words) {
			i = 0;
			j = w.length()-1;
			countSteps = 0;
			while (i <= j) {
				countSteps += Math.abs(w.charAt(j--) - w.charAt(i++));
			}
			System.out.println(countSteps);
		}
	}

}
