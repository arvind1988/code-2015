package algo.arrayp;

public class ReplaceWithNextGreater {

  /**
   * Given an array of integers, replace every element with the next greatest
   * element (greatest element on the right side) in the array. Since there is
   * no element next to the last element, replace it with -1. For example, if
   * the array is {16, 17, 4, 3, 5, 2}, then it should be modified to {17, 5, 5,
   * 5, 2, -1}<br>
   * 
   * <b> Time complexity : O(n)
   * 
   * @param A
   */
  public static void replaceElementWithNextGreatest(int[] A) {
    if (A == null) throw new NullPointerException();
    /* Travesing from right and maintain the current max of A[i, A.length -1] */
    int max = A[A.length - 1];
    A[A.length - 1] = -1;
    for (int i = A.length - 2; i >= 0; i--) {
      int temp = A[i];
      A[i] = max;
      if (temp > max) max = temp;
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
