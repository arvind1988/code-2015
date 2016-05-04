package algo.arrayp;

public class MaxDifference {

  /**
   * Given an array arr[] of integers, find out the difference between any two
   * elements such that larger element appears after the smaller number in
   * arr[].<br>
   * 
   * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be
   * 8 (Diff between 10 and 2). If array is [ 7, 9, 5, 6, 3, 2 ] then returned
   * value should be 2 (Diff between 7 and 9)
   * 
   * @param A
   */
  public static int findMaxDifference(int[] A) {
    int maxDiff = 0;
    int maxNumOnRight = A[A.length - 1];
    for (int i = A.length - 2; i >= 0; i--) {
      int tempDiff = maxNumOnRight - A[i];
      if (A[i] < maxNumOnRight) maxDiff = tempDiff > maxDiff ? tempDiff
          : maxDiff;
      else maxNumOnRight = A[i];
    }
    return maxDiff;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
