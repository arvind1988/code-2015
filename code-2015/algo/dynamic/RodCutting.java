package algo.dynamic;

import java.util.Arrays;

public class RodCutting {

  public static int cutRod(int[] p, int n) {
    if (n == 0) return 0;
    int maxRevenue = Integer.MIN_VALUE;
    for (int i = 1; i < n; i++) {
      int temp = max(p[n - 1], cutRod(p, i) + cutRod(p, n - i));
      maxRevenue = max(temp, maxRevenue);
    }
    return maxRevenue;
  }

  private static int max(int a, int b) {
    return a > b ? a : b;
  }

  // Top-Down approach using memoization

  public static int cutRodMemoized(int[] p, int n) {
    int[] r = new int[n];
    Arrays.fill(r, Integer.MIN_VALUE);
    return cutRodMemoized(p, n, r);

  }

  // If the cutting of rod incurs a fixed cost of 2; Cormen 15.1-3
  private static int cutRodMemoized(int[] p, int n, int[] r) {
    if (r[n - 1] >= 0) return r[n - 1];
    if (n == 0) return 0;
    int maxRevenue = Integer.MIN_VALUE;
    for (int i = 1; i <= n; i++) {
      if(i == n)  
        maxRevenue = max(maxRevenue, p[i - 1] + cutRod(p, n - i));
      else 
        maxRevenue = max(maxRevenue, p[i - 1] - 2 + cutRod(p, n - i));
    }
    r[n - 1] = maxRevenue;
    return maxRevenue;
  }

  public static void main(String[] args) {
    int[] p = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };

    System.out.println(cutRodMemoized(p, p.length));

  }

}
