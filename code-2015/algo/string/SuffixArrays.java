package algo.string;

import java.util.Arrays;

public class SuffixArrays {

  private static void createSuffixArray(String s, int N, String[] suffixes) {
    for (int i = 0; i < N; i++)
      suffixes[i] = s.substring(i);
  }

  // Longest repeated substring a nlogn algorithm
  public static String lrp(String s) {
    int N = s.length();
    String[] suffixes = new String[N];
    // Create suffix array of s
    createSuffixArray(s, N, suffixes);
    // Sort the array in nlogn time
    Arrays.sort(suffixes);
    int maxMatch = Integer.MIN_VALUE;
    int j, p = 0;
    for (int i = 0; i < N - 1; i++) {
      j = i + 1;
      int currMatch = LongestCommonPrefix.lcp(suffixes[i], suffixes[j]);
      if (currMatch > maxMatch) {
        maxMatch = currMatch;
        p = j;
      }
    }
    if (maxMatch != Integer.MIN_VALUE) return suffixes[p]
        .substring(0, maxMatch);
    else return null;
  }

  public static void main(String[] args) {
    String text = "aacaagtttacaagc";
    System.out.println(lrp(text));
  }

}
