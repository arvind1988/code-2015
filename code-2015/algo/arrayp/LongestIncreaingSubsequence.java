package algo.arrayp;

public class LongestIncreaingSubsequence {

  /**
   * LIS(A[1..n]): (A) Case 1: does not contain an in which case LIS(A[1..n]) =
   * LIS(A[1..(n - 1)])
   *
   * (B) Case 2: contains an in which case LIS(A[1..n]) is not so clear.<br>
   * 
   * Observation:<br>
   * For second case we want to find a subsequence in A[1..(n-1)] that is
   * restricted to numbers less than an. This suggests that a more general
   * problem is LIS smaller(A[1..n], x) which gives the longest increasing
   * subsequence in A where each number in the sequence is less than x.
   * 
   * @param A
   * @param x
   * @return
   */
  public static int lis(int[] A, int n, int x) {
    if (n == 0) return 0;
    int m = lis(A, n - 1, x);
    if (A[n - 1] < x) { // this can also be A[1...n] since this is less than x
      m = Math.max(m, 1 + lis(A, n - 1, A[n - 1]));
    }
    return m;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
