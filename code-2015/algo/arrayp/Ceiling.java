package algo.arrayp;

public class Ceiling {

  public static int ceiling(int[] A, int low, int high, int x) {
    if (x <= A[0]) return A[0];
    if (x >= A[A.length - 1]) return A[A.length - 1];
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 < A.length && A[mid + 1] > x && A[mid] < x) return A[mid + 1];
      else if (A[mid] == x) return A[mid + 1];
      else if (x < A[mid]) return ceiling(A, low, mid - 1, x);
      else return ceiling(A, mid + 1, high, x);
    } else return -1;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
