package algo.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxDepthNestedParenthesis {

  /**
   * This function assumes that the expression is parenthesized in a balanced
   * way.
   * 
   * @param exp
   * @return
   */
  public static int maxDepthOfNestedParenthesis(String exp) {
    Deque<String> stack = new ArrayDeque<>(); // Can hold 16 items

    // Don't use in this for loop condition test otherwise it will be called
    // again and again
    int n = exp.length();
    int maxnesting = Integer.MIN_VALUE;
    int temp = 0;
    for (int i = 0; i < n /* exp.length() */; i++) {

      // This is a constant time operation.
      String brace = exp.substring(i, i + 1);
      if (brace.equals("(")) {
        stack.push(brace);
        temp++;
      } else if (brace.equals(")")) {
        if (maxnesting < temp) maxnesting = temp;
        if (stack.isEmpty()) return -1;
          stack.pop();
          temp--;
        
      }
    }
    // This means the parenthesisation is not balanced
    if (!stack.isEmpty()) return -1;
    return maxnesting;

  }

  public static void main(String[] args) {
    System.out.println(maxDepthOfNestedParenthesis("( a(b) (c) (d(e(f)g)h) I (j(k)l)m)"));
  }

}
