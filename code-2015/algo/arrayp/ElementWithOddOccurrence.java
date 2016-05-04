package algo.arrayp;

public class ElementWithOddOccurrence {

  /**
   * Given an array of positive integers. All numbers occur even number of times
   * except one number which occurs odd number of times. Find the number in O(n)
   * time & constant space. Here the input is considered to be right.
   * 
   * @param A
   */
  public static int getElementWithOddOccurrence(int[] A) {
    if (A == null) return -1;
    int num = A[0];
    for (int i = 1; i < A.length; i++)
      num = num ^ A[i];
    return num;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
