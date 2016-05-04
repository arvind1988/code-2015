package algo.bitwise;

public class PowerOfTwo {

  /**
   * Subtracting 1 from a number which is power of 2 toggles all its bits from
   * right to left. If we new take bit mask with n then if the result is zero
   * then it
   * 
   * @param n
   * @return
   */
  public static boolean isPowerOfTwo(int n) {
    return (n & (n - 1)) == 0;
  }

  public static void main(String[] args) {
    System.out.println(isPowerOfTwo(12));

  }

  /**
   * *************************************************************************
   * *************************************************************************
   * Rule 1 : If a number is power of two and 1 is subtracted from that number
   * then all bit from that first (including first) toggles
   */
}
