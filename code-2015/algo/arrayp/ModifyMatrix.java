package algo.arrayp;

public class ModifyMatrix {

  public static int[][] modifyMatrix(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;
    boolean[] rowsDone = new boolean[m];
    boolean[] colDone = new boolean[n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1) {
          rowsDone[i] = true;
          colDone[j] = true;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rowsDone[i] || colDone[j]) A[i][j] = 1;
      }
    }
    return A;
  }

  /**
   * A slower method of doing the manipulation as modifyMatrix. But this doesnot
   * uses any extra memory 3N^2 + some more time in striking off the rows and
   * col repeating tasks which aready has been done.
   * 
   * @param A
   * @return
   */
  public static int[][] modifyMatrix1(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1) {
          A[i][j] = 2;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 2) {
          for (int k = 0; k < m; k++)
            if (A[k][j] != 2) A[k][j] = 1;

          for (int k = 0; k < n; k++)
            if (A[i][k] != 2) A[i][k] = 1;
        }
      }
    }
    // Change back 2 to 1
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 2) {
          A[i][j] = 1;
        }
      }
    }
    return A;
  }
}
