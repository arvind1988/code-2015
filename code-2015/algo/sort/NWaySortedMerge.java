package algo.sort;

import java.util.Arrays;

public class NWaySortedMerge {

  // A and B are sorted arrays
  public static int[] merge(int[] A, int[] B) {
    int N = A.length;
    int M = B.length;
    int[] C = new int[N + M];
    // Pointer into A, B and C respectively
    int i = 0, j = 0, k = 0;
    while (i < N && j < M) {
      if (A[i] < B[j]) C[k++] = A[i++];
      else C[k++] = B[j++];
    }
    // If A has ended first B has elements
    if (i == N) {
      while (j < M)
        C[k++] = B[j++];
    }
    // if B has ended and A has elements
    if (j == M) {
      while (i < N)
        C[k++] = A[i++];
    }
    return C;
  }

  public static int[] merge(int[][] arrays) {
    int[] C = merge(arrays[0], arrays[1]);
    for (int i = 2; i < arrays.length; i++)
      C = merge(arrays[i], C);
    return C;
  }

  public static void main(String[] args) {
    int[][] arrays = new int[][] { { 3, 7, 9, 10 }, { 2, 4, 8, 11 },
        { 1, 6, 8, 15 } };
    int[] C = merge(arrays);
    System.out.println(Arrays.toString(C));

  }

}
