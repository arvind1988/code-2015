package algo.sort;

import java.util.Arrays;

public class LSDRadixSort {

  /**
   * This method uses counting sort internally on each of the characters from
   * right to left. Therefore it does counting sort W times to sort the entire
   * array of Strings.<br>
   * Note that this method  work for strings of unequal sizes  also.
   * 
   * 
   * @param A
   * @param W
   */
  public static void sort(String[] A, int W, int R) {

    
    for (int i = 0; i < W; i++) {
      int[] count = new int[R];

      for (String s : A) {
        int p = s.length() - (i + 1);
        if (p >= 0) count[s.charAt(p)] += 1;
        else count[0] += 1;
      }
      // B[i] contains number of keys less than or equal to i
      for (int j = 1; j < R; j++)
        count[j] += count[j - 1];

      String[] C = new String[A.length];
      // Place A[i]'s at right position
      for (int j = A.length - 1; j >= 0; j--) {
        int p = A[j].length() - (i + 1);
        if (p >= 0) {
          // Subtract 1 because position starts from 0
          C[count[A[j].charAt(p)] - 1] = A[j];
          count[A[j].charAt(p)] -= 1;
        } else {
          // Subtract 1 because position starts from 0
          C[count[0] - 1] = A[j];
          count[0] -= 1;
        }

      }
      for (int j = 0; j < A.length; j++)
        A[j] = C[j];
    }
  }

  public static void main(String[] args) {
    String[] A = { "18", "107", "92", "145"};
    LSDRadixSort.sort(A, 3, 256);
    System.out.println(Arrays.toString(A));

  }

}
