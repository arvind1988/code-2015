package algo.arrayp;

public class SubsetSum {

  public static boolean subsetSum(int[] A) {
    int sum = 0;
    for(int x : A) 
      sum += x;

    return subsetSum(A, A.length, sum);
  }

  /**
   * 
   * @param A
   * @param n
   * @param sum
   * @return
   */
  public static boolean subsetSum(int[] A, int n, int sum) {
    if (sum == 0) return true;
    if (n == 0 && sum != 0) return false;
    // If the value of A[n - 1] > sum then it can't be included in subset.
    if (sum - A[n - 1] < 0) return subsetSum(A, n - 1, sum);
    return subsetSum(A, n - 1, sum) || subsetSum(A, n - 1, sum - A[n - 1]);
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
