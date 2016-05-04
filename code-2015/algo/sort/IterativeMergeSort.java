package algo.sort;

import java.util.Arrays;

public class IterativeMergeSort {

  private static final int sentinal = Integer.MAX_VALUE;

  // Merges two sorted sub-arrays A[p..q] and A[q + 1, r]
  public static void merge(int[] A, int p, int q, int r) {
    // Size of left sorted subarray + 1 for sentinal
    int nL = q - p + 1 + 1;
    // Size of left sorted subarray + 1 for sentinal
    int nR = r - q + 1;
    // Left sorted subarray
    int[] L = new int[nL];
    // Right sorted subarray
    int[] R = new int[nR];
    // Copy the subarray from A[p , q] into the left subarray
    for (int i = 0; i < nL - 1; i++)
      L[i] = A[p + i];
    L[nL - 1] = sentinal;
    // Copy the subarray from A[q + 1, r] into the right subarray
    for (int i = 0; i < nR - 1; i++)
      R[i] = A[q + 1 + i];
    R[nR - 1] = sentinal;
    // Merge the subarrays and put the result into A
    int i = 0, j = 0, k = p;
    while (i < nL || j < nR) {
      if (L[i] < R[j]) A[k++] = L[i++];
      else if (L[i] > R[j]) A[k++] = R[j++];
      else {
        if (L[i] != sentinal && R[j] != sentinal) A[k++] = L[i++];
        else {
          i++;
          j++;
        }
      }
    }
  }

  public static void sort(int[] A, int p, int r) {
    if (A == null) return;
    // s < r because when s == r then the array would be sorted
    // We take sub arrays of size 1 and 2 and then 4 and so on
    for (int s = 1; s < r; s *= 2) {
      // get the low index of the current subarray
      for (int low = p; low < r; low += 2 * s) {
        // q is the spilting point
        int q = Math.min(low + s - 1, r);
        // last index of the subarrays. Min function is used because high might
        // go out of array due to
        // insufficient elements
        int high = Math.min(q + s, r);
        merge(A, low, q, high);
      }
    }

  }

  public static void main(String[] args) {
    int[] A = { 2, 8, 7, 4, 18, 5, 9, 18, 56, 23, 34, 56, 19, 96, 45, 76 };
    System.out.println(Arrays.toString(A));
    IterativeMergeSort ims = new IterativeMergeSort();
    ims.sort(A, 0, A.length - 1);
    System.out.println(Arrays.toString(A));

  }

}
