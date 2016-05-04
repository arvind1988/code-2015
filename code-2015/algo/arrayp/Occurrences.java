package algo.arrayp;

public class Occurrences {

  /**
   * Count the number of occurrences in a sorted array Given a sorted array
   * arr[] and a number x, write a function that counts the occurrences of x in
   * arr[]. Expected time complexity is O(Logn)<br>
   * Examples:<br>
   * 
   * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 2 Output: 4 // x (or 2) occurs 4
   * times in arr[]<br>
   * 
   * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 3 Output: 1<br>
   * 
   * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 1 Output: 2<br>
   * 
   * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,}, x = 4 Output: -1 // 4 doesn't occur
   * in arr[]
   * 
   * @param A
   * @param x
   * @return
   */
  public static int countOccurrences(int[] A, int x) {
    if (A == null) throw new NullPointerException();
    int s = startIndex(A, 0, A.length - 1, x);
    int l = lastIndex(A, 0, A.length - 1, x);
    if (l == -1 && s == -1) return 0;
    return l - s + 1;

  }

  private static int startIndex(int[] A, int low, int high, int x) {
    if (A == null) return -1;
    if (low <= high) {
      int mid = (low + high) / 2;
      if ((mid - 1 < 0 && A[mid] == x) || (A[mid] == x && A[mid - 1] < x)) return mid;
      else if (x > A[mid]) return startIndex(A, mid + 1, high, x);
      else return startIndex(A, low, mid - 1, x);
    } else return -1;
  }

  private static int lastIndex(int[] A, int low, int high, int x) {
    if (A == null) return -1;
    if (low <= high) {
      int mid = (low + high) / 2;
      if ((mid + 1 > A.length - 1 && A[mid] == x)
          || (A[mid] == x && A[mid + 1] > x)) return mid;
      else if (x < A[mid]) return lastIndex(A, low, mid - 1, x);
      else return lastIndex(A, mid + 1, high, x);
    } else return -1;
  }

  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
