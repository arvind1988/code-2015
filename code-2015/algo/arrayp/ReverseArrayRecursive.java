package algo.arrayp;

public class ReverseArrayRecursive {

  /**
   * An ugly implementation of reversing any array just to understand recursion
   * better
   * 
   * @param A
   * @param low
   * @param high
   */
  public static void reverseArray(int[] A, int low, int high) {
    if (low == high) return;
    if (low < high) {
      reverseArray(A, low, high - 1);
      circularShiftRightByOne(A, low, high);
    }
  }
  private static void circularShiftRightByOne(int[] A, int low, int high) {
    if (A == null) return;
    if (low == high) return;
    int key = A[high], i;
    for (i = high - 1; i >= low; i--)
      A[i + 1] = A[i];
    A[i + 1] = key;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
