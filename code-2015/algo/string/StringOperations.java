package algo.string;

public class StringOperations {

  public static void reverseString(String s, int low, int high) {
    if (low == high) return;
    if (low < high) {
      reverseString(s, low + 1, high);
      System.out.print(s.charAt(low));
    }
  }

  public static void permute(String s, String result, int n) {
    if (s.length() == 0) System.out.println(result);
    else {
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        permute(s.substring(0, i) + s.substring(i + 1), result + c, n);
      }
    }
  }

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

  /**
   * Check if a given sequence of moves for a robot is circular or not Given a
   * sequence of moves for a robot, check if the sequence is circular or not. A
   * sequence of moves is circular if first and last positions of robot are
   * same. A move can be on of the following.
   * 
   * G - Go one unit L - Turn left R - Turn right
   * 
   * Examples:
   * 
   * Input: path[] = "GLGLGLG" Output: Given sequence of moves is circular
   * 
   * Input: path[] = "GLLG" Output: Given sequence of moves is circular
   * 
   * @param path sequence of moves
   * @param x current x position
   * @param y current y position
   * @param direction current direction
   * @return
   */
  public static boolean isRobotPathCircular(String path, int x, int y,
      int direction) {

    for (int i = 0; i < path.length(); i++) {
      int[] nxtState = getNextRobotState(x, y, direction, path.charAt(i));
      if(nxtState[0] == 0 && nxtState[1] == 0) return true;
      x = nxtState[0];
      y = nxtState[1];
      direction = nxtState[2];
    }
    return false;
  }

  private static int[] getNextRobotState(int x, int y, int direction, char nextMove) {
    if (direction == 0) {
      switch (nextMove) {
      case 'G':
        return new int[] { x, y + 1, 0 }; // E = 0

      case 'L':
        return new int[] { x, y, 1 }; // N = 1

      case 'R':
        return new int[] { x, y, 2 }; // S = 2

      default:
        break;
      }
    }
    if (direction == 1) {
      switch (nextMove) {
      case 'G':
        return new int[] { x - 1, y, 1 }; // N = 0

      case 'L':
        return new int[] { x, y, 3 }; // W = 3

      case 'R':
        return new int[] { x, y, 0 }; // E = 0

      default:
        break;
      }
    }

    if (direction == 2) {
      switch (nextMove) {
      case 'G':
        return new int[] { x + 1, y, 2 }; // S = 2

      case 'L':
        return new int[] { x, y, 0 }; // E = 0

      case 'R':
        return new int[] { x, y, 3 }; // W = 3

      default:
        break;
      }
    }

    if (direction == 3) {
      switch (nextMove) {
      
      case 'G':
        return new int[] { x, y - 1, 3 }; // W = 3

      case 'L':
        return new int[] { x, y, 2 }; // S = 2

      case 'R':
        return new int[] { x, y, 1 }; // N = 1

      default:
        break;
      }
    }
    return null;
  }

  /**
   * Function to find Number of customers who could not get a computer Write a
   * function “runCustomerSimulation” that takes following two inputs a) An
   * integer ‘n': total number of computers in a cafe and a string: b) A
   * sequence of uppercase letters ‘seq': Letters in the sequence occur in
   * pairs. The first occurrence indicates the arrival of a customer; the second
   * indicates the departure of that same customer.
   * 
   * A customer will be serviced if there is an unoccupied computer. No letter
   * will occur more than two times. Customers who leave without using a
   * computer always depart before customers who are currently using the
   * computers. There are at most 20 computers per cafe.
   * 
   * For each set of input the function should output a number telling how many
   * customers, if any walked away without using a computer. Return 0 if all the
   * customers were able to use a computer.
   * 
   * runCustomerSimulation (2, “ABBAJJKZKZ”) should return 0
   * 
   * runCustomerSimulation (3, “GACCBDDBAGEE”) should return 1 as ‘D’ was not
   * able to get any computer
   * 
   * runCustomerSimulation (3, “GACCBGDDBAEE”) should return 0
   * 
   * runCustomerSimulation (1, “ABCBCA”) should return 2 as ‘B’ and ‘C’ were not
   * able to get any computer.
   * 
   * runCustomerSimulation(1, “ABCBCADEED”) should return 3 as ‘B’, ‘C’ and ‘E’
   * were not able to get any computer
   */

  public static int howManyWithoutComputer(int n, String sequence) {

    // Assuming only character set of 256 chars
    boolean[] using = new boolean[256];
    
    // On  start all the computers are vacant
    int vacant = n;
    
    // Counter to track how many person have not used sofar
    int notUsed = 0;
    
    for (int i = 0; i < sequence.length(); i++) {
      if (!using[sequence.charAt(i)]) {
        if (vacant > 0) { // If ith guy has not used and there is vacant computer
          using[sequence.charAt(i)] = true;
          vacant--;
        } else notUsed++; // here ith guy will not be able to use the computer
      } else{
        vacant++; // If ith departs and hence creates vacant position
        using[sequence.charAt(i)] = false;
      }
    }
    // Since each guy which has not used the computer appears twice in the
    // input sequence (arrival and depart) we divide the result by two.
    return notUsed/2;
  }
  
  
  
  public static void main(String[] args) {

    String path = "GLLG";
    System.out.println(howManyWithoutComputer(1, "ABCBCA"));
  }

}
