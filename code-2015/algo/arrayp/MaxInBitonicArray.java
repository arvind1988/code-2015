package algo.arrayp;

public class MaxInBitonicArray {

  /**
   * Given an array of integers which is initially increasing and then
   * decreasing, find the maximum value in the array.<br>
   * 
   * Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1} Output: 500<br>
   * 
   * Input: arr[] = {1, 3, 50, 10, 9, 7, 6} Output: 50<br>
   * 
   * Corner case (No decreasing part) Input: arr[] = {10, 20, 30, 40, 50}
   * Output: 50<br>
   * 
   * Corner case (No increasing part) Input: arr[] = {120, 100, 80, 20, 0}
   * Output: 120<br>
   * 
   * @param A
   * @param low
   * @param high
   * @return
   */
  public static int maximumInBitonic(int[] A, int low, int high) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 >= A.length || mid - 1 < 0) return A[mid];
      if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) return A[mid];
      else if (A[mid + 1] < A[mid] && A[mid - 1] > A[mid]) return maximumInBitonic(
          A, low, mid - 1);
      else return maximumInBitonic(A, mid + 1, high);
    } else return -1;
  }

}
