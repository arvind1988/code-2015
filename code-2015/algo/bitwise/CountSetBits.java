package algo.bitwise;

public class CountSetBits {

  /**
   * This is a Brain's algorithm for computing the number of set bits in a number n.
   * 
   * @param n
   * @return
   */
  public static int countSetBits(int n){
    int count = 0;
    while(n != 0){
      count += n & 1;
      n = n & n - 1;
    }
    return n;
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
