package algo.arrayp;

public class BinarySearch {

  public static int binarySearch(int[] A, int low, int high, int key) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = high - (high - low) / 2;
      if (key == A[mid]) return mid;
      else if (key > A[mid]) return binarySearch(A, mid + 1, high, key);
      else return binarySearch(A, low, mid - 1, key);
    } else return -1;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
