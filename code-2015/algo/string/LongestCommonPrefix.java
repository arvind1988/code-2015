package algo.string;

public class LongestCommonPrefix {

  public static int lcp(String s, String t){
    int N = Math.min(s.length(), t.length());
      for(int i = 0; i < N; i++)
        if(s.charAt(i) != t.charAt(i)) return i;
      return N;
  }
  public static void main(String[] args) {
    

  }

}
