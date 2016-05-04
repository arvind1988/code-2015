package algo.arrayp;

public class MissingAndRepeated {

  /**
   * Given an unsorted array of size n. Array elements are in range from 1 to n.
   * One number from set {1, 2, …n} is missing and one number occurs twice in
   * array. Find these two numbers.<br>
   * Examples:<br>
   * 
   * arr[] = {3, 1, 3} Output: 2, 3 // 2 is missing and 3 occurs twice
   * 
   * arr[] = {4, 3, 6, 2, 1, 1} Output: 1, 5 // 5 is missing and 1 occurs twice<br>
   * <b> Note: This is a very awesome technique
   * 
   * @param A
   * @param n
   */
  public static void findRepeatingAndMissing(int[] A, int n) {
    if (A == null) return;
    int xorArray = 0;
    for (int x : A)
      xorArray = xorArray ^ x;

    for (int x = 1; x <= n; x++)
      xorArray = xorArray ^ x;

    // xorArray now contains the missing and the duplicates
    // To get the rightmost bit
    int setbit = xorArray & ~(xorArray - 1);
    int a = 0, b = 0;
    for (int x : A) {
      if ((x & setbit) > 0) a = a ^ x;
      else b = b ^ x;
    }
    for (int x = 1; x <= n; x++) {
      if ((x & setbit) > 0) a = a ^ x;
      else b = b ^ x;
    }
    System.out.println(a + " " + b);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
