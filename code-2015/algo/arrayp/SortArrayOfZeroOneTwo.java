package algo.arrayp;

public class SortArrayOfZeroOneTwo {

  /**
   * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts
   * A[]. The functions should put all 0s first, then all 1s and all 2s in last.<br>
   * Example Input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}; Output = {0, 0, 0, 0,
   * 0, 1, 1, 1, 1, 1, 2, 2} We use a 3-way partitioing of the elements.<br>
   * 
   * A[0...i] contains value zero A[i + 1...j] contains value one and [A.length
   * -1, k] contains value two. The values between j and k are not known while
   * the algorithm is running. The loop invariant is mentained prior to
   * initialization, during loop and upon termination
   */
  public static void sortArrayContainingZerosOnesAndTwo(int[] A) {
    int i = -1, j = 0, k = A.length;
    while (i < A.length - 1 && j < k) {
      if (A[j] == 0) {
        int temp = A[j];
        A[j] = A[i + 1];
        A[i + 1] = temp;
        i = i + 1;
        j = j + 1;
      } else if (A[j] == 1) j++;
      else {
        k = k - 1;
        while (j < k && A[k] == 2)
          k--;
        int temp = A[k];
        A[k] = A[j];
        A[j] = temp;
        if (A[j] == 0) {
          temp = A[i + 1];
          A[i + 1] = A[j];
          A[j] = temp;
          i = i + 1;
        }
        j = j + 1;
      }
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
