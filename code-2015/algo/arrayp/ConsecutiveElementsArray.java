package algo.arrayp;

public class ConsecutiveElementsArray {

  /**
   * Given an unsorted array of numbers, write a function that returns true if
   * array consists of consecutive numbers.<br>
   * 
   * Examples: a) If array is {5, 2, 3, 1, 4}, then the function should return
   * true because the array has consecutive numbers from 1 to 5.<br>
   * 
   * b) If array is {83, 78, 80, 81, 79, 82}, then the function should return
   * true because the array has consecutive numbers from 78 to 83.<br>
   * 
   * c) If the array is {34, 23, 52, 12, 3 }, then the function should return
   * false because the elements are not consecutive.<br>
   * 
   * d) If the array is {7, 6, 5, 5, 3, 4}, then the function should return
   * false because 5 and 5 are not consecutive.<br>
   * 
   * @param A
   * @return
   */
  public static boolean areElementsConsecutive(int[] A) {
    if (A == null) throw new NullPointerException();
    boolean[] visited = new boolean[A.length];
    int max = A[0];
    int min = A[0];
    for (int i = 0; i < A.length; i++) {
      if (A[i] > max) max = A[i];
      if (A[i] < min) min = A[i];
    }
    if (max - min + 1 != A.length) return false;
    for (int i = 0; i < A.length; i++) {
      if (visited[A[i] - min]) return false;
      visited[A[i] - min] = true;
    }
    return true;
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
