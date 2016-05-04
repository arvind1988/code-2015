package algo.dynamic;

public class LongestPalindromicSubSequence {

  /**
   * The longest palindromic subsequence (LPS) problem is the problem of finding
   * the longest subsequence of a string (a subsequence is obtained by deleting
   * some of the characters from a string without reordering the remaining
   * characters) which is also a palindrome. In general, the longest palindromic
   * subsequence is not unique. For example, the string alfalfa has four
   * palindromic subsequences of length 5: alala, afafa, alfla, and aflfa.
   * However, it does not have any palindromic subsequences longer than five
   * characters. Therefore all four are considred longest palindromic
   * subsequences of alfalfa.
   * 
   * @param S
   * @param i
   * @param j
   * @return
   */
  public static int longestPalindromicSubSeq(char[] S, int i, int j) {
    if (S == null) throw new NullPointerException();
    if (i == j) return 1;
    if (i < j) {
      if (S[i] == S[j]) return i + 1 == j ? 2 : longestPalindromicSubSeq(S,
          i + 1, j - 1) + 2;
      else return Math.max(longestPalindromicSubSeq(S, i + 1, j),
          longestPalindromicSubSeq(S, i, j - 1));
    } else return -1;
  }
}
