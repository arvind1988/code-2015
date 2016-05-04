package algo.arrayp;

public class SubArraySum {

  /**
   * Given an unsorted array of nonnegative integers, find a continous subarray
   * which adds to a given number.<br>
   * 
   * Examples:<br>
   * 
   * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33 Ouptut: Sum found between
   * indexes 2 and 4<br>
   * 
   * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7 Ouptut: Sum found between
   * indexes 1 and 4<br>
   * 
   * Input: arr[] = {1, 4}, sum = 0 Output: No subarray found<br>
   * 
   * <b> Note: not very comfortable wiht j - 1;
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean subArraySum(int[] A, int sum) {
    if (A == null) throw new NullPointerException();
    int i = 0, j = 0;
    int subSum = A[0];
    i = 0;
    for (j = 1; j <= A.length; j++) {
      while (i < j - 1 && subSum > sum) {
        subSum -= A[i];
        i++;
      }
      if (subSum == sum) return true;
      if (j < A.length) subSum += A[j];
    }
    return false;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
