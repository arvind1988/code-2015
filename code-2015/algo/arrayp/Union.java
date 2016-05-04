package algo.arrayp;

import java.util.ArrayList;
import java.util.List;

public class Union {

  /**
   * Union of two sets which are in sorted order. Sets can't have duplicate
   * elements in it. Caller is expected to sort A and B before calling this
   * method. If the arrays are not sorted result is not defined.
   * 
   * @param A
   * @param B
   * @return
   */
  public static List<Integer> union(int[] A, int[] B) {
    List<Integer> unionList = new ArrayList<>(A.length);
    int i = 0, j = 0;
    while (i < A.length || j < B.length) {
      if (i != A.length && j != B.length) {
        if (A[i] < B[j]) unionList.add(A[i++]);
        else if (A[i] > B[j]) unionList.add(B[j++]);
        else {
          unionList.add(A[i++]);
          j++;
        }
      } else if (i == A.length) unionList.add(B[j++]);
      else unionList.add(A[i++]);
    }
    for (Integer u : unionList)
      System.out.println(u + " ");
    return unionList;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
