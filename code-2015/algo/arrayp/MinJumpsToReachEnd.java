package algo.arrayp;

public class MinJumpsToReachEnd {

  private static int   minSteps    = Integer.MAX_VALUE;
  private static int[] memoization = new int[50];

  /**
   * Nice problem. Do revise before interview
   * 
   * @param A
   * @param start
   * @param end
   * @param steps
   */
  public static void countMinStepsToReachEnd(int[] A, int start, int end,
      int steps) {
    if (A == null) throw new NullPointerException();
    if (start == end) {
      if (minSteps > steps) {
        minSteps = steps;
        memoization[start] = minSteps;
      }
    }
    if (start < end) {
      for (int i = start + 1; i <= end && i <= start + A[start]; i++) {
        // steps = steps + 1; Wrong to update the steps variable here since it
        // will pass different steps value to all the fucntion call from this
        // fucntion call level.
        if (memoization[i] > 0 && minSteps > steps) {
          // step + 1 is required here since the next function call would have
          // increased steps by 1 but we are not call the function
          minSteps = steps + 1;
        } else countMinStepsToReachEnd(A, i, end, steps + 1);
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
