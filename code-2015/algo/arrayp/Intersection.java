package algo.arrayp;

import java.util.ArrayList;
import java.util.List;

public class Intersection {

  /**
   * Intersection of two sets which are in sorted order. Sets can't have
   * duplicate elements in it. Caller is expected to sort A and B before calling
   * this method. If the arrays are not sorted result is not defined.
   * 
   * @param A
   * @param B
   * @return
   */
  public static List<Integer> intersection(int[] A, int[] B) {
    List<Integer> intersectionList = new ArrayList<>(A.length);
    int i = 0, j = 0;
    while (i < A.length || j < B.length) {
      if (i != A.length && j != B.length) {
        if (A[i] < B[j]) i++;
        else if (A[i] > B[j]) j++;
        else {
          intersectionList.add(A[i++]);
          j++;
        }
      } else break;
    }
    for (Integer u : intersectionList)
      System.out.println(u + " ");
    return intersectionList;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
