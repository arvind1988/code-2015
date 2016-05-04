package algo.arrayp;

import java.util.Arrays;

public class TwoSumUsingSort {
  public static boolean twoSum(int[] A, int sum) {
    Arrays.sort(A);
    int i = 0;
    int j = A.length - 1;
    while (i < j) {
      int currentSum = A[i] + A[j];
      if (currentSum == sum) {
        System.out.print(A[i] + " " + A[j] + " ");
        return true;
      } else if (currentSum < sum) i++;
      else j--;
    }
    System.out.println("No two numbers found");
    return false;
  }
}
