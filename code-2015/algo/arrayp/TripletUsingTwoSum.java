package algo.arrayp;

public class TripletUsingTwoSum {

  /**
   * This method uses the problem of finding 2 sum by fixing A[i] and checking
   * for sum - A[i]. If the sum - A[i] is possible in A then A[i] with the other
   * two elements for which b + c = sum - A[i] are triplets.<br>
   * <br>
   * Time complexity = O(n^2);
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean findTripletUsingTwoSum(int[] A, int sum) {
    if (A == null) throw new NullPointerException();
    boolean isTripletFound = false;
    for (int i = 0; i <= A.length - 3; i++) {
      isTripletFound = TwoSum.isTwoSumPossible(A, sum - A[i]);
    }
    return isTripletFound;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
