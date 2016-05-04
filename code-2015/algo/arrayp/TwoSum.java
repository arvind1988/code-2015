package algo.arrayp;

import java.util.Arrays;

public class TwoSum {

  public static boolean isTwoSumPossible(int[] a, int N) {
    int[] b = new int[a.length];
    int[] c = new int[2 * a.length];
    for (int i = 0; i < a.length; i++)
      b[i] = N - a[i];

    Arrays.sort(a);
    Arrays.sort(b);
    int i = 0;
    int j = 0;
    int k = 0;
    while (i < a.length || j < b.length) {
      if (i == a.length) c[k++] = b[j++];
      else if (j == b.length) c[k++] = a[i++];
      else {
        if (a[i] <= b[j]) c[k++] = a[i++];
        else c[k++] = b[j++];
      }
    }
    int key = c[0];
    for (i = 1; i < c.length; i++) {
      if (c[i] == key) return true;
      else key = c[i];
    }
    return false;
  }
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

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
