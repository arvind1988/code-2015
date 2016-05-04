package algo.string;

public class BruteForcePatternSearch {

  public static int search(String text, String pat) {
    int N = text.length();
    int M = pat.length();
    if (N < M) return -1;
    for (int i = 0; i <= N - M; i++) {
      int j, k;
      for (j = i, k = 0; j < M + i; j++, k++) {
        if (text.charAt(j) != pat.charAt(k)) break;
      }
      if (j == M + i) return i;
    }
    return -1;
  }

  public static void main(String[] args) {
    String text = "ABACADABRAC";
    String pat = "ABRA";
    System.out.println(search(text, pat));
  }
}
