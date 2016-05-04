package algo.arrayp;

public class MaxSubArray {

  /**
   * Program to find the sum of contiguous subarray within a one-dimensional
   * array of numbers which has the largest sum. It uses Kadane's algorithm
   * 
   * @param A
   * @return
   */
  public static int maxSubArray(int[] A) {
    if (A == null) throw new NullPointerException();
    int runningSum = A[0];
    int maxSoFar = A[0];
    for (int i = 1; i < A.length; i++) {
      runningSum = Math.max(runningSum + A[i], A[i]);
      maxSoFar = Math.max(maxSoFar, runningSum);
    }
    return maxSoFar;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
