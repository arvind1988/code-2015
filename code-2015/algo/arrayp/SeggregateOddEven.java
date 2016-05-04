package algo.arrayp;

public class SeggregateOddEven {

  /**
   * Given an array A[], write a function that segregates even and odd numbers.
   * The functions should put all even numbers first, and then odd numbers.
   * Example Input = {12, 34, 45, 9, 8, 90, 3} Output = {12, 34, 8, 90, 45, 9,
   * 3} The odrer of elements are mentained. The runs in O(n)
   */
  public static void seggregateEvenOdd(int[] A) {
    if (A == null || A.length == 0) return;
    int i = 0, j = 1;
    while (i < A.length) {
      if (A[i] % 2 != 0) {
        while (j < A.length && A[j] % 2 != 0) {
          j++;
        }
        if (j < A.length) {
          int temp = A[i];
          A[i] = A[j];
          A[j] = temp;
        } else break;
      } else j++;
      i++;
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
