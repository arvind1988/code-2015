package algo.arrayp;

import java.util.Arrays;

public class ProductPuzzle {

  /**
   * Given an array arr[] of n integers, construct a Product Array prod[] (of
   * same size) such that prod[i] is equal to the product of all the elements of
   * arr[] except arr[i].
   * 
   * @param A
   */
  public static void productPuzzle(int[] A) {
    int[] frwd = new int[A.length];
    Arrays.fill(frwd, 1);
    int[] bkwrd = new int[A.length];
    Arrays.fill(bkwrd, 1);
    for (int i = 0; i < A.length - 1; i++) {
      for (int j = i + 1; j < A.length; j++) {
        frwd[i] = frwd[i] * A[j];
      }
    }
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < i; j++) {
        bkwrd[i] = bkwrd[i] * A[j];
      }
    }
    for (int i = 0; i < A.length; i++) {
      A[i] = frwd[i] * bkwrd[i];
    }
    System.out.println(Arrays.toString(A));
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
