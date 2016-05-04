package algo.dynamic;

public class MaxNonAdjacentSum {

  /**
   * Given an array of positive numbers, find the maximum sum of a subsequence
   * with the constraint that no 2 numbers in the sequence should be adjacent in
   * the array. So 3 2 7 10 should return 13 (sum of 3 and 10) or 3 2 5 10 7
   * should return 15 (sum of 3, 5 and 7).Answer the question in most efficient
   * way.
   * 
   * @param A
   * @param index
   * @param sum
   * @param max
   * @return
   */
  public static int sum(int[] A, int index, int sum, int max) {
    sum = sum + A[index];
    if (index == A.length - 2 || index == A.length - 1) return sum;
    else {
      for (int i = index + 2; i < A.length; i++)
        max = Math.max(max, sum(A, i, sum, max));
    }
    return max;
  }

  public static int maxSum(int[] A) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < A.length; i++) {
      int temp = sum(A, i, 0, Integer.MIN_VALUE);
      if (temp > max) max = temp;
    }
    return max;
  }

  public static void main(String[] args) {
    int[] A = new int[] { 3, 2, 7, 10 };
    System.out.println(maxSum(A));
  }

}
