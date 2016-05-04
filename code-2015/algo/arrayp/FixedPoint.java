package algo.arrayp;

public class FixedPoint {

  /**
   * Given an array of n distinct integers sorted in ascending order, write a
   * function that returns a Fixed Point in the array, if there is any Fixed
   * Point present in array, else returns -1. Fixed Point in an array is an
   * index i such that arr[i] is equal to i. Note that integers in array can be
   * negative.<br>
   * 
   * Examples:<br>
   * 
   * Input: arr[] = {-10, -5, 0, 3, 7} Output: 3 // arr[3] == 3<br>
   * 
   * Input: arr[] = {0, 2, 5, 8, 17} Output: 0 // arr[0] == 0<br>
   * 
   * Input: arr[] = {-10, -5, 3, 4, 7, 9} Output: -1 // No Fixed Point<br>
   * 
   * Algorithm : Uses binary search for the required point.
   * 
   * @param A
   * @param low
   * @param high
   * @return
   */
  public static int fixedPoint(int[] A, int low, int high) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = (low + high) / 2; // mid index
      if (mid == A[mid]) return mid;
      else if (mid < A[mid] /* < A[m + 1] */) return fixedPoint(A, low, mid - 1);
      else return fixedPoint(A, mid + 1, high);
    } else return -1;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
