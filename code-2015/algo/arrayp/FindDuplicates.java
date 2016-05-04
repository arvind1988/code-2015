package algo.arrayp;

public class FindDuplicates {

  /**
   * Given an array of n elements which contains elements from 0 to n-1, with
   * any of these numbers appearing any number of times. Find these repeating
   * numbers in O(n) and using only constant memory space.<br>
   * 
   * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer
   * should be 1, 3 and 6.<br>
   * 
   * Algorithm : Use the same array as the counter or visited array instead of
   * different one. We can mark a particular element as visited but that element
   * values should be preserved. Therefore we can mark it as -ve of the number.<br>
   * 
   * Note that the range of number n is important here. If the size of the array
   * is n then it can hold only numbers from 0 to n-1. No negative numbers are
   * permitted
   * 
   * @param A
   */
  public static void findDuplicates(int[] A) {
    for (int i = 0; i < A.length; i++) {
      int a = Math.abs(A[i]);
      if (A[a] >= 0) A[a] = -A[a];
      else System.out.println(a);
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
