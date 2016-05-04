package algo.string;

public class FirstNonRepeatingCharG {

  /**
   * Given a string, find the first non-repeating character in it. For example,
   * if the input string is “GeeksforGeeks”, then output should be ‘f’ and if
   * input string is “GeeksQuiz”, then output should be ‘G’.
   * 
   * @param input
   * @return
   */
  public char getFirstNonRepeatingCharInOnePass(String input) {
    class CounterIndex {
      int counter;
      int index;
    }
    CounterIndex[] ci = new CounterIndex[256];
    int i = 0;
    while (i < 256) {
      ci[i] = new CounterIndex();
      ci[i].index = ci[i].counter = 0;
      i++;
    }
    i = 0;
    while (i < input.length()) {
      ci[input.charAt(i)].counter += 1;
      if (ci[input.charAt(i)].index == 0) ci[input.charAt(i)].index = i;
      i++;
    }
    i = 0;
    int min = input.length();
    while (i < ci.length) {
      if (ci[i].counter == 1 & min > ci[i].index) min = ci[i].index;
      i++;
    }
    return input.charAt(min);
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
