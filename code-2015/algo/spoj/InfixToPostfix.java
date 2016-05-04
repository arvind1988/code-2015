package algo.spoj;

import java.util.ArrayDeque;
import java.util.Deque;

public class InfixToPostfix {

  /**
   * This will return true if op_1 has higher precedence then op_2 when op_1
   * appears left to op_2 in the infix notation.<br>
   * Precedence order : '+' < '-' < '*' < '/' < '^'
   * 
   * @param op_1
   * @param op1
   * @return
   */
  public static boolean precedence(char op_1, char op_2) {

    if(op_2 == '(') return false;
    
    // We want to pop all the operators on the stack upto the first opening brace
    // but don't want to pop and add the opening brace to the output string. We need
    // to discard the opeing brace.
    if(op_2 == ')')
      return !(op_1 == '('); 
    
    switch (op_1) {
    case '+':
      return op_2 == '+' ? true : false;
    case '-':
      return op_2 == '+' || op_2 == '-' ? true : false;
    case '*':
      return op_2 == '/' || op_2 == '^' ? false : true;
    case '/':
      return op_2 == '^' ? false : true;
    case '^':
      return true;
    case '(': 
      // if on the top of the stack has '(' then any operator should be pushed
      return false;
    case ')':
      return true;
    default:
      return false;
    }
  }
  
  public static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == ')'
        || c == '(';
  }
  
  public static void infixToPostFix(String exp) {
    if (exp == null || exp.isEmpty()) return;
    Deque<Character> operatorStack = new ArrayDeque<>(exp.length());
    // This will contain the postfix expression.
    StringBuilder sb = new StringBuilder(exp.length());
    
    // One-by-one examine the infix symbols from lest to right.
    for (int i = 0; i < exp.length(); i++) {
       
       // Current symbol
      char symb = exp.charAt(i);
      
      // If symb is not a operator just append to the postfix builder.
      if (!isOperator(symb)) sb.append(symb);
      
      else {
        /*
         * If c has less precedence than the top of the stack then stack must be
         * popped until top of the stack has operator higher than the c. This is
         * because all the higher precedence operators must be present in the
         * postfix notation before lower precedence operators.
         */
        while (!operatorStack.isEmpty()
            && precedence(operatorStack.peekFirst(), symb)) {
          sb.append(operatorStack.pop());
        }
        if (symb != ')') 
          operatorStack.push(symb);
        else operatorStack.pop();
      }
    }
    // If the input is over then just pop the elements and append
    while (!operatorStack.isEmpty()) {
      sb.append(operatorStack.pop());
    }
    System.out.println(sb.toString());
  }
  public static void main(String[] args) {

    String infixExpr = "((a+t)*((b+(a+c))^(c+d)))";
    long time = System.currentTimeMillis();
    infixToPostFix(infixExpr);
    System.out.println(System.currentTimeMillis() - time);
  }

}
