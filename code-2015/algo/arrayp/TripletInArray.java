package algo.arrayp;

public class TripletInArray {

  /**
   * Given an array and a value, find if there is a triplet in array whose sum
   * is equal to the given value. If there is such a triplet present in array,
   * then print the triplet and return true. Else return false. For example, if
   * the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then there is a
   * triplet (12, 3 and 9) present in array whose sum is 24.<br>
   * <br>
   * 
   * <b>Note:</b> This is O(n^3) algorithm: Can we do better?
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean findTriplet(int[] A, int sum) {
    if (A == null) return false;
    if (A.length < 3) return false;
    for (int i = 0; i <= A.length - 3; i++) {
      for (int j = i + 1; j <= A.length - 2; j++) {
        for (int k = j + 1; k <= A.length - 1; k++) {
          if (A[i] + A[j] + A[k] == sum) {
            System.out.println(A[i] + " " + A[j] + " " + A[k]);
            return true;
          }
        }
      }
    }
    return false;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
