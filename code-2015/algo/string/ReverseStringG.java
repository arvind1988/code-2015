package algo.string;

public class ReverseStringG {
  
  public static void reverseString(String s, int low, int high) {
    if (low == high) return;
    if (low < high) {
      reverseString(s, low + 1, high);
      System.out.print(s.charAt(low));
    }
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub
  }

}
