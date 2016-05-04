package algo.string;

public class Permute {

  public static void permute(String s, String result, int n) {
    if (s.length() == 0) System.out.println(result);
    else {
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        permute(s.substring(0, i) + s.substring(i + 1), result + c, n);
      }
    }
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
