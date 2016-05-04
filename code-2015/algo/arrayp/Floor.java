package algo.arrayp;

public class Floor {

  /**
   * It is assummed that the array is sorted in ascending order. This will find
   * the floor x which is a value just smaller or equal to x
   * 
   * @param A The sorted array
   * @param low start index of A.
   * @param high End index of A
   * @param x floor which are interested in
   * @return
   */
  public static int floor(int[] A, int low, int high, int x) {
    if (x <= A[0]) return A[0];
    if (x > A[A.length - 1]) return A[A.length - 1];
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 < A.length && A[mid + 1] > x && A[mid] < x) return A[mid];
      else if (A[mid] == x) return A[mid - 1];
      else if (x < A[mid]) return floor(A, low, mid - 1, x);
      else return floor(A, mid + 1, high, x);
    } else return -1;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
