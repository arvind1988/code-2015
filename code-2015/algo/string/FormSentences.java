package algo.string;

public class FormSentences {

  /**
   * Recursively print all sentences that can be formed from list of word lists
   * Given a list of word lists How to print all sentences possible taking one
   * word from a list at a time via recursion?
   * 
   * Example:
   * 
   * Input: {{"you", "we"}, {"have", "are"}, {"sleep", "eat", "drink"}}
   * 
   * @param words
   * @param output
   * @param m
   */
  public static void formSentences(String[][] words, String output, int m) {
    int R = words.length;
    if (m == R) {
      System.out.println(output.toString());
      return;
    }
    // We need to loop for number of columns in each row
    for (int c = 0; c < words[m].length; c++)
      formSentences(words, output + words[m][c] + " ", m + 1);
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
