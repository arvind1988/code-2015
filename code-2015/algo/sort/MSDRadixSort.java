package algo.sort;

import java.util.Arrays;

public class MSDRadixSort {

  /**
   * 
   * @param A
   * @param low
   * @param high
   * @param d
   */
  public static void sort(String[] A, String[] C, int low, int high, int d) {

    if (low < high) {
      int R = 256;
      int[] count = new int[R];

      for (int i = low; i <= high; i++) {
        int c = charAt(A[i], d);
        if (c > 0) count[c]++;
        else count[0]++;
      }
      for (int i = 1; i < R; i++)
        count[i] += count[i - 1];

      for (int i = high; i >= low; i--) {
        int c = charAt(A[i], d);
        if (c > 0) {
          C[count[c] - 1] = A[i];
          count[c] -= 1;
        } else {
          C[count[0] - 1] = A[i];
          count[0] -= 1;
        }
      }
      for (int i = low; i <= high; i++)
        A[i] = C[i];

      for (int r = 0; r < R; r++) {
        sort(A, C, low, count[r] - 1, d + 1);
        low = count[r];
      }

    }

  }

  private static int charAt(String s, int d) {
    if (d < s.length()) return s.charAt(d);
    else return -1;
  }

  public static void main(String[] args) {
    String[] A = { "18", "107", "92", "145" };
    String[] C = new String[A.length];
    MSDRadixSort.sort(A, C, 0, A.length - 1, 0);
    System.out.println(Arrays.toString(A));
  }

}
