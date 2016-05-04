package algo.string;

public class RobotCircularPathG {

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

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
