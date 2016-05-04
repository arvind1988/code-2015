package algo.arrayp;

public class MinimumDistance {

  /**
   * Given an unsorted array arr[] and two numbers x and y, find the minimum
   * distance between x and y in arr[]. The array might also contain duplicates.
   * You may assume that both x and y are different and present in arr[].<br>
   * 
   * Examples:<br>
   * Input: arr[] = {1, 2}, x = 1, y = 2 Output: Minimum distance between 1 and
   * 2 is 1.<br>
   * 
   * Input: arr[] = {3, 4, 5}, x = 3, y = 5 Output: Minimum distance between 3
   * and 5 is 2.<br>
   * 
   * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6 Output:
   * Minimum distance between 3 and 6 is 4.<br>
   * 
   * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2 Output: Minimum
   * distance between 3 and 2 is 1<br>
   * 
   * Implementation details:<br>
   * We scan the array from left to right and when for the first time x or y is
   * found then we save the value of that index in i. We continue searching for
   * the other value. In course of the search if the same value as A[i] is found
   * then we change the value of i to j. If different value is found then we
   * take the difference and compare with the current min distance and then
   * again change the value of i to j; i is previous kind of index which points
   * to either x or y.
   * 
   * @param A
   * @param x
   * @param y
   */
  public static int minimumDistance(int[] A, int x, int y) {
    int i = -1;
    int min = Integer.MAX_VALUE;
    if (x == y) return 0;
    for (int j = 0; j < A.length; j++) {
      if (i == -1 && (A[j] == x || A[j] == y)) i = j;
      else if (i != -1 && A[j] == A[i]) i = j;
      else if (i != -1 && (A[j] == x || A[j] == y) && A[j] != A[i]) {
        if (j - i < min) {
          min = j - i;
        }
        i = j;
      }
    }
    return min;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
