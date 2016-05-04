package algo.arrayp;

import java.util.Arrays;


public class FourNumbers {

  /**
   * Given an array of integers, find all combination of four elements in the
   * array whose sum is equal to a given value X. For example, if the given
   * array is {10, 2, 3, 4, 5, 9, 7, 8} and X = 23, then your function should
   * print “3 5 7 8" (3 + 5 + 7 + 8 = 23).<br>
   * 
   * Time complexit = O(n^3logn)
   * 
   * @param A
   * @param sum
   */
  public static void find4NumbersEqualToSum(int[] A, int sum) {
    Arrays.sort(A);
    for (int i = 0; i < A.length - 1; i++) {
      for (int j = i + 1; j < A.length; j++) {
        if (TwoSum.twoSum(A, sum - A[i] - A[j])) {
          System.out.println(A[i] + " " + A[j] + " ");
          return;
        }
      }
    }
  }
}
