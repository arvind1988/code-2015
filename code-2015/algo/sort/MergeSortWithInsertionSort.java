package algo.sort;

import java.util.Arrays;

public class MergeSortWithInsertionSort {

  private static final int sentinal = Integer.MAX_VALUE;
  private static final int cutoff   = 5;

  public static void main(String[] args) {
    int[] A = { 2, 8, 7, 4, 18, 5, 9, 18, 56, 23, 34, 56, 19, 96, 45, 76, 3};
    System.out.println(Arrays.toString(A));
    MergeSortWithInsertionSort ims = new MergeSortWithInsertionSort();
    ims.sort(A, 0, A.length - 1);
    System.out.println(Arrays.toString(A));
  }

  // Merges two sorted sub-arrays A[p..q] and A[q + 1, r]
  public void merge(int[] A, int p, int q, int r) {
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

  public void sort(int[] A, int p, int r) {
    // Use insertion sort on small arrays
    if (r - p <= cutoff) InsertionSort.sort(A, p, r);
    else {
        int q = (p + r) / 2;
        sort(A, p, q);
        sort(A, q + 1, r);
        merge(A, p, q, r);
    }

  }

}
