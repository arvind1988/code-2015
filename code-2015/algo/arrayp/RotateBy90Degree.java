package algo.arrayp;

public class RotateBy90Degree {

  /**
   * This is an in place rotation of a 2D matrix by 90 degrees. The algorithm
   * transforms each boundary of the matrix one by one. The outermost boundary
   * is considered first. A[i][j] takes the value of A[B-j+offset][i]. The
   * offset is required for the inner matrices for the next boundary rotation.
   * 
   * @param A
   * @param N
   */
  public static void rotateBy90Degree(int[][] A, int N) {
    if (A == null) return;
    int i = 0;
    int offset = 0;
    while (N != 0) {
      int j = i;
      int B = N - 1;
      int k = 0;
      for (k = 0; k < N - 1; k++) {
        int top = A[i][j];
        A[i][j] = A[B - j + offset][i];
        A[B - j + offset][i] = A[B - i + offset][B - j + offset];
        A[B - i + offset][B - j + offset] = A[j][B - i + offset];
        A[j][B - i + offset] = top;
        j++;
      }
      N -= 2;
      offset += 2;
      i++;
    }
    for (i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
