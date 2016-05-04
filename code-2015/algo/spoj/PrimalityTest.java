package algo.spoj;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PrimalityTest {

  public static void main(String[] args) {
   /* for (int n = 2; n < 100000; n++) {
      if (trialDivisionSqrt(n)) System.out.println(n + " is prime");
    }*/

    //factors(1289489);
    generatePrimesInRange();
  }

  public static boolean trialDivision(int n) {
    if (n == 0 || n == 1) return false;
    int i = 2;
    int x = n;
    while (x != 1) {
      if (x % i == 0) {
        if (n == i) return true;
        x = x / i;
      } else i++;
    }
    return false;
  }

  public static boolean trialDivisionSqrt(int n) {
    if (n == 0 || n == 1) return false;
    // 2 is prime by definition
    if (n == 2) return true;
    // Not a prime if this is a even number
    if (n % 2 == 0) return false;

    // check for only odd numbers
    for (int i = 3; i <= Math.sqrt(n) + 1; i += 2) {
      if (n % i == 0) return false;
    }
    return true;
  }
  
  public static void factors(int n){
    Set<Integer> set = new TreeSet<>();
    int i = 2;
    while(n != 1){
      if(n % i == 0){
        set.add(i);
        n /= i;
        i = 2;
      }else i++;
    }
    for(int x : set)
      System.out.println(x);
  }
  
  
  public static void generatePrimesInRange() {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    class Pair {
      int m;
      int n;

      Pair(int m, int n) {
        this.m = m;
        this.n = n;
      }
    }
    Pair[] ranges = new Pair[t];
    int i = 0;
    while (i < t && sc.hasNextInt()) {
      ranges[i++] = new Pair(sc.nextInt(), sc.nextInt());
    }
    sc.close();
    for (Pair p : ranges) {
      if (p.m > 1000000000 || p.n > 1000000000) return;
      if (p.n - p.m > 100000) return;
      for (int s = p.m; s <= p.n; s++) {
        if (trialDivisionSqrt(s)) {
          System.out.println(s);
        }
      }
    }
  }

}
