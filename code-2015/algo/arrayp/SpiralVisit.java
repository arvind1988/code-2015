package algo.arrayp;

public class SpiralVisit {

  public static void spiralVisit(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;
    // Current row and column
    int r = 0;
    int c = 0;
    while (r < m && c < n) {
      // Visit top row
      for (int i = c; i < n; i++)
        System.out.println(A[r][i]);
      // Visit right column
      for (int i = r + 1; i < m; i++)
        System.out.println(A[i][n - 1]);
      // Visit bottom row
      for (int i = n - 2; i >= c && m - 1 > r; i--)
        System.out.println(A[m - 1][i]);
      // Visit first column
      for (int i = m - 2; i > r; i--)
        System.out.println(A[i][c]);

      r++;
      c++;
      m--;
      n--;
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
