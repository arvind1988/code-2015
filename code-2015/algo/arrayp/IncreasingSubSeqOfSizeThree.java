package algo.arrayp;

public class IncreasingSubSeqOfSizeThree {

  /**
   * Solution using some extra space. Given an array of n integers, find the 3
   * elements such that a[i] < a[j] < a[k] and i < j < k in 0(n) time. If there
   * are multiple such triplets, then print any one of them.<br>
   * 
   * Examples:<br>
   * 
   * Input: arr[] = {12, 11, 10, 5, 6, 2, 30} Output: 5, 6, 30<br>
   * 
   * Input: arr[] = {1, 2, 3, 4} Output: 1, 2, 3 OR 1, 2, 4 OR 2, 3, 4<br>
   * 
   * Input: arr[] = {4, 3, 2, 1} Output: No such triplet<br>
   * 
   * @param A
   * @param n
   */
  public static void increasingSubSeqOfSize3(int[] A, int n){
    int[] lessThan = new int[n];
    int[] moreThan = new int[n];
    
    int min = A[0];
    lessThan[0] = -1;
    for(int i = 1; i < n; i++){
      if(A[i] < min){
        min = A[i];
        lessThan[i] = -1;
      }else lessThan[i] = min;
    }
    moreThan[n - 1] = -1;
    int max = A[n - 1];
    for(int i = n - 2; i >= 0; i--){
      if(A[i] > max){
        max = A[i];
        moreThan[i] = -1;
      }else moreThan[i] = max;
    }
    
    for (int i = 0; i < n; i++) {
      if (lessThan[i] != -1 && moreThan[i] != -1){
        System.out.println(lessThan[i] + " " + A[i] + " " + moreThan[i]);
        return;
      }
    }
    System.out.println("No such sequence");
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
