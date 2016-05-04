package algo.sort;

import java.util.Arrays;

public class CountingSort {
  /**
   * @param A the array to be sorted
   * @param R the upper bound on the numbers in A. Note that lower bound is 0.
   */
  public static int[] sort(int[] A, int R) {
    int[] count = new int[R];

    for (int x : A)
      count[x] += 1;
    // B[i] contains number of keys less than or equal to i
    for (int i = 1; i < R; i++)
      count[i] += count[i - 1];

    int[] C = new int[A.length];
    // Place A[i]'s at right position
    for (int i = A.length - 1; i >= 0; i--) {
      // Subtract 1 because position starts from 0
      C[count[A[i]] - 1] = A[i];
      count[A[i]] -= 1;
    }
    return C;
  }

  public static void main(String[] args) {
    int[] A = new int[] { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
    A = CountingSort.sort(A, 17);
    System.out.println(Arrays.toString(A));
  }
}
