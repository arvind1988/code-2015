package algo.string;

public class LongestRepeatedSubstring {

  /**
   * This is quadratic time algorithm to solve this problem. Not useful for
   * large string.<br>
   * Faster method using suffix array is available.
   * 
   * @param s
   * @return
   */
  public static String lrp(String s) {
    int N = s.length();
    int maxMatch = Integer.MIN_VALUE;
    int p = 0;
    for (int i = 0; i < N; i++) {
      String ithSuffix = s.substring(i);
      for (int j = i + 1; j < N; j++) {
        int currMatch = LongestCommonPrefix.lcp(ithSuffix, s.substring(j));
        if (currMatch > maxMatch) {
          maxMatch = currMatch;
          p = j;
        }
      }
    }
    return s.substring(p, p + maxMatch);
  }

  public static void main(String[] args) {
    String text = "this is a cow and cow color is white";
    System.out.println(lrp(text));
  }

}
