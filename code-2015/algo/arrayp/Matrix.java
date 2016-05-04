package algo.arrayp;

public class Matrix {
    
    public static int[][] multiply(int[][] A, int[][] B, int n) {
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } else {
            int newSize = n / 2;
            int[][] a11 = new int[newSize][newSize];
            createSubMatrix(A, a11, 0, newSize - 1, 0, newSize - 1);
            int[][] a12 = new int[newSize][newSize];
            createSubMatrix(A, a12, 0, newSize - 1, newSize, n - 1);
            int[][] a21 = new int[newSize][newSize];
            createSubMatrix(A, a21, newSize, n - 1, 0, newSize - 1);
            int[][] a22 = new int[newSize][newSize];
            createSubMatrix(A, a22, newSize, n - 1, newSize, n - 1);
            int[][] b11 = new int[newSize][newSize];
            createSubMatrix(B, b11, 0, newSize - 1, 0, newSize - 1);
            int[][] b12 = new int[newSize][newSize];
            createSubMatrix(B, b12, 0, newSize - 1, newSize, n - 1);
            int[][] b21 = new int[newSize][newSize];
            createSubMatrix(B, b21, newSize, n - 1, 0, newSize - 1);
            int[][] b22 = new int[newSize][newSize];
            createSubMatrix(B, b22, newSize, n - 1, newSize, n - 1);

            int[][] c11 = new int[newSize][newSize];
            int[][] c12 = new int[newSize][newSize];
            int[][] c21 = new int[newSize][newSize];
            int[][] c22 = new int[newSize][newSize];

            c11 = add(multiply(a11, b11, newSize), multiply(a12, b21, newSize),
                    newSize);
            c12 = add(multiply(a11, b12, newSize), multiply(a12, b22, newSize),
                    newSize);
            c21 = add(multiply(a21, b11, newSize), multiply(a22, b21, newSize),
                    newSize);
            c22 = add(multiply(a21, b12, newSize), multiply(a22, b22, newSize),
                    newSize);
            int i = 0;
            int j = 0;

            for (i = 0; i < newSize; i++) {
                for (j = 0; j < newSize; j++) {
                    // System.out.print(c11[i][j] + " ");
                    C[i][j] = c11[i][j];
                    C[i + newSize][j] = c21[i][j];
                    C[i][j + newSize] = c12[i][j];
                    C[i + newSize][j + newSize] = c22[i][j];
                }
            }
            return C;
        }
    }

    private static void createSubMatrix(int[][] A, int[][] a, int sr, int er,
            int sc, int ec) {
        if (A == null || a == null) throw new NullPointerException();

        for (int i = sr; i <= er; i++) {
            for (int j = sc; j <= ec; j++)
                a[i - sr][j - sc] = A[i][j];
        }
        return;
    }

    public static int[][] add(int[][] A, int[][] B, int n) {
        if (A == null || B == null || n <= 0)
            throw new IllegalArgumentException();
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] + B[i][j];
        }
        return C;
    }

    public static void main(String[] args) {

        int[][] A = new int[][] { { 2, 3, 4, 6 }, { 9, 12, 2, 7 },
                { 5, 6, 1, 8 }, { 11, 5, 4, 7 }, };
        int[][] B = new int[][] { { 4, 5, 8, 13 }, { 9, 3, 6, 1 },
                { 12, 14, 5, 8 }, { 6, 6, 15, 15 } };
        int[][] C = new int[4][4];
        C = multiply(A, B, 4);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

    }

}
