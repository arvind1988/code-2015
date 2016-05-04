package algo.sort;

import java.util.Arrays;

public class InsertionSort {

  public static void main(String[] args) {
    int[] A = { 2, 8, 7, 4, 18, 5, 9, 18, 56, 23, 34, 56, 19, 96, 45, 76 };
    System.out.println(Arrays.toString(A));
    sort(A, 0, A.length);
    System.out.println(Arrays.toString(A));
  }
  
  public static void sort(int[] A, int p, int r){
    int j;
    for(int i = p; i <= r; i++){
      j = i - 1;
      int key = A[i];
      while(j >= 0 && key < A[j]){
        A[j + 1] = A[j];
        j--;
      }
      A[j + 1] = key;
    }
  }

}
