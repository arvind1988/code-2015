package algo.arrayp;

public class SmallestMissingElement {

  /**
   * Given a sorted array of n integers where each integer is in the range from
   * 0 to m-1 and m > n. Find the smallest number that is missing from the
   * array.<br>
   * 
   * Examples Input: {0, 1, 2, 6, 9}, n = 5, m = 10 Output: 3<br>
   * 
   * Input: {4, 5, 10, 11}, n = 4, m = 12 Output: 0 <br>
   * 
   * Input: {0, 1, 2, 3}, n = 4, m = 5 Output: 4<br>
   * 
   * Input: {0, 1, 2, 3, 4, 5, 6, 7, 10}, n = 9, m = 11 Output: 8<br>
   * 
   * The time complexity of this implementation is O(n). It could be done in
   * O(logn) using binary search technique.<br>
   * <b>Note: Try doing this using binary search in next iteration.<br>
   * 
   * @param A
   * @return
   */
  public static int smallestMissingElement(int[] A) {
    if (A == null) throw new NullPointerException();
    if (A[0] != 0) return 0;
    for (int i = 0; i < A.length - 1; i++) {
      int j = i + 1;
      if (A[j] - A[i] > 1) return A[i] + 1;
    }
    return A[A.length - 1] + 1;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
