package algo.string;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseStackElements {

  public static void reverse(Deque<String> stack) {
    if (!stack.isEmpty()) {
      String item = stack.pop();
      reverse(stack);
      insertAtBottom(stack, item);
    }
  }

  public static void insertAtBottom(Deque<String> stack, String item) {

    if (stack.isEmpty()) {
      stack.push(item);
      return;
    }
    String p = stack.pop();
    insertAtBottom(stack, item);
    stack.push(p);
  }

  public static void main(String[] args) {
    Deque<String> stack = new ArrayDeque<>();
    stack.push("A");
    stack.push("B");
    stack.push("C");
    reverse(stack);
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }

  }

}
