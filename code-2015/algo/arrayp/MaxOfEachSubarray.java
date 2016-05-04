package algo.arrayp;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxOfEachSubarray {

  /**
   * Given an array and an integer k, find the maximum for each and every
   * contiguous subarray of size k.<br>
   * 
   * Examples:<br>
   * Input : arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6} k = 3 Output : 3 3 4 5 5 5 6<br>
   * 
   * Input : arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} k = 4 Output : 10 10 10
   * 15 15 90 90.<br>
   * 
   * <b>Note: There is a solution on geekforgeeks which is more optimized than
   * this. Take it in next iteration. Think other methods of solving it.
   * 
   * @param A
   * @param k
   */
  public static void findMaxOfEachSubArray(int[] A, int k) {
    Deque<Integer> deque = new ArrayDeque<>(k);
    if (A.length < k) return;
    int max = -1;
    for (int i = 0; i < k; i++) {
      deque.add(A[i]);
      if (A[i] > max) max = A[i];
    }
    System.out.println(max);
    for (int i = k; i < A.length; i++) {
      int item = deque.remove();
      deque.add(A[i]);
      if (item != max) {
        if (A[i] > max) {
          System.out.println(A[i]);
          max = A[i];
        } else System.out.println(max);
      } else {
        // If the element removed from the queue is max element
        // How can we optmize this part?
        max = -1;
        for (int q : deque)
          if (q > max) max = q;

        System.out.println(max);
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
