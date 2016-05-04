package algo.arrayp;

import java.util.Arrays;

public class PairWithDiff {

  public static void findPairWithDiff(int[] A, int X) {
    Arrays.sort(A);
    for (int i = 0; i < A.length - 1; i++) {
      int indx = BinarySearch.binarySearch(A, i + 1, A.length - 1, X + A[i]);
      if (indx >= 0) {
        System.out.println(A[i] + " " + A[indx]);
        return;
      }
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
