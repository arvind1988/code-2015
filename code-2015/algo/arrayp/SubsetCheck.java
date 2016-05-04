package algo.arrayp;

import java.util.Arrays;

public class SubsetCheck {

  /**
   * Given two arrays: arr1[0..m-1] and arr2[0..n-1]. Find whether arr2[] is a
   * subset of arr1[] or not. Both the arrays are not in sorted order.<br>
   * 
   * Examples:<br>
   * Input: Q[] = {11, 1, 13, 21, 3, 7}, P[] = {11, 3, 7, 1} Output: P[] is a
   * subset of Q[]<br>
   * 
   * Input: Q[] = {1, 2, 3, 4, 5, 6}, P[] = {1, 2, 4} Output: P[] is a subset of
   * Q[]<br>
   * 
   * Input: Q[] = {10, 5, 2, 23, 19}, P[] = {19, 5, 3} Output: P[] is not a
   * subset of Q[]<br>
   * <b> There are other methods also like sorting and then binary search,
   * Hashing.
   * 
   * @param P
   * @param Q
   * @return
   */
  public static boolean isSubset(int[] P, int[] Q) {
    if (P == null || Q == null) return true;
    Arrays.sort(P);
    Arrays.sort(Q);
    int i = 0;
    int j = 0;
    while (i < P.length && j < Q.length) {
      if (P[i] > Q[j]) j++;
      else if (P[i++] == Q[j++])
      ;
      else return false;
    }
    return i == P.length;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
