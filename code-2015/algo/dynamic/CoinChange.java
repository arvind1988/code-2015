package algo.dynamic;

public class CoinChange {

  /**
   * Recursive solution
   * @param coins
   * @param sum
   * @return
   */
  public static int coinChange(int[] coins, int sum) {
    if (coins == null || coins.length == 0) return -1;
    if (sum == 0) return 0;

    int m = Integer.MAX_VALUE;
    for (int i = 0; i < coins.length; i++) {
      if (sum - coins[i] >= 0)
        m = Math.min(m, 1 + coinChange(coins, sum - coins[i]));
    }
    return m;
  }

  /**
   * using dyanamic programing
   * @param coins
   * @param sum
   * @return
   */
  public static int dynamicCoinChange(int[] coins, int sum) {
    int[] C = new int[sum + 1];
    C[0] = 0;

    for (int i = 1; i <= sum; i++) {
      C[i] = Integer.MAX_VALUE;
      int min = Integer.MAX_VALUE;
      for (int c = 0; c < coins.length; c++) {
        if (i - coins[c] >= 0) {
          if (1 + C[i - coins[c]] < min) min = 1 + C[i - coins[c]];
        }
      }
      C[i] = min;
    }
    return C[C.length - 1];
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
