package algo.arrayp;

public class SeggregateNegPos {

  /**
   * Seggregate negative and positive numbers in an array.
   * 
   * @param A
   * @return
   */
  public static int seggregateNegPos(int[] A) {
    if (A == null) throw new NullPointerException();
    int i = -1;
    int j = 0;
    for (j = 0; j < A.length; j++) {
      if (A[j] >= 0) {
        int temp = A[i + 1];
        A[i + 1] = A[j];
        A[j] = temp;
        i++;
      }
    }
    return i;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
