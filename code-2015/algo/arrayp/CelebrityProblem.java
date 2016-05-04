package algo.arrayp;

public class CelebrityProblem {

  private static boolean doesKnow(int i, int j) {
    boolean know[][] = { { false, false, true, false },
        { false, false, true, false }, { false, false, false, false },
        { false, false, true, false } };
    return know[i][j];
  }

  /**
   * In a party of N people, only one person is known to everyone. Such a person
   * may be present in the party, if yes, (s)he doesn’t know anyone in the
   * party. We can only ask questions like “does A know B? “. Find the stranger
   * (celebrity) in minimum number of questions.
   * 
   * We can describe the problem input as an array of numbers/characters
   * representing persons in the party. We also have a hypothetical function
   * HaveAcquaintance(A, B) which returns true if A knows B, false otherwise
   * 
   * @param A
   * @param n
   * @param low
   * @param high
   * @return
   */
  public static int celebrity(int[] A, int n, int low, int high) {
    if (n == 2) {
      if (doesKnow(A[low], A[high])) return A[high];
      else return A[low];
    }
    int c = celebrity(A, n - 1, low, high - 1);
    if (!doesKnow(c, A[high])) {
      return c;
    } else {
      boolean isCeleb = true;
      for (int i = low; i <= high - 1; i++) {
        if (doesKnow(A[n], A[i])) isCeleb = false;
      }
      if (isCeleb) return A[high];
      else return -1;
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
