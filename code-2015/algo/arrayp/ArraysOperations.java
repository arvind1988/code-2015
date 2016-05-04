package algo.arrayp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import algo.ds.binarytree.BST;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

public class ArraysOperations {

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

  /**
   * <b>Majority Element:</b> A majority element in an array A[] of size n is an
   * element that appears more than n/2 times (and hence there is at most one
   * such element)
   * 
   * @param elements
   * @return
   */
  public static int majorityElement(int[] elements) {
    if (elements == null) return -1;
    int count = 1;
    int candidate = elements[0];
    for (int i = 1; i < elements.length; i++) {
      if (count == 0) {
        candidate = elements[i];
        count = 1;
      } else {
        if (elements[i] == candidate) count++;
        else count--;
      }
    }
    count = 0;
    for (int element : elements) {
      if (element == candidate) count++;
    }
    if (count > elements.length / 2) return candidate;
    return -1;
  }

  /**
   * Given an array of positive integers. All numbers occur even number of times
   * except one number which occurs odd number of times. Find the number in O(n)
   * time & constant space. Here the input is considered to be right.
   * 
   * @param A
   */
  public static int getElementWithOddOccurrence(int[] A) {
    if (A == null) return -1;
    int num = A[0];
    for (int i = 1; i < A.length; i++)
      num = num ^ A[i];
    return num;
  }

  /**
   * Program to find the sum of contiguous subarray within a one-dimensional
   * array of numbers which has the largest sum. It uses Kadane's algorithm
   * 
   * @param A
   * @return
   */
  public static int maxSubArray(int[] A) {
    if (A == null) throw new NullPointerException();
    int runningSum = A[0];
    int maxSoFar = A[0];
    for (int i = 1; i < A.length; i++) {
      runningSum = max(runningSum + A[i], A[i]);
      maxSoFar = max(maxSoFar, runningSum);
    }
    return maxSoFar;
  }

  
  public static int binarySearch(int[] A, int low, int high, int key) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = high - (high - low) / 2;
      if (key == A[mid]) return mid;
      else if (key > A[mid]) return binarySearch(A, mid + 1, high, key);
      else return binarySearch(A, low, mid - 1, key);
    } else return -1;
  }

  public static void findPairWithDiff(int[] A, int X) {
    Arrays.sort(A);
    for (int i = 0; i < A.length - 1; i++) {
      int indx = binarySearch(A, i + 1, A.length - 1, X + A[i]);
      if (indx >= 0) {
        System.out.println(A[i] + " " + A[indx]);
        return;
      }
    }
  }

  /**
   * An ugly implementation of reversing any array just to understand recursion
   * better
   * 
   * @param A
   * @param low
   * @param high
   */
  public static void reverseArray(int[] A, int low, int high) {
    if (low == high) return;
    if (low < high) {
      reverseArray(A, low, high - 1);
      circularShiftRightByOne(A, low, high);
    }
  }

  private static void circularShiftRightByOne(int[] A, int low, int high) {
    if (A == null) return;
    if (low == high) return;
    int key = A[high], i;
    for (i = high - 1; i >= low; i--)
      A[i + 1] = A[i];
    A[i + 1] = key;
  }

  /*
   * public static int findPivotInRotatedSorted(int[] A, int low, int high) {
   * if(A == null)throw new NullPointerException(); if(low <= high){ int mid =
   * high - (high - low)/2; if(A[mid + 1] < A[mid]) return mid; else if() return
   * mid; else if() } }
   */
  public static int max(int a, int b) {
    if (a > b) return a;
    else return b;
  }

  /**
   * Given an array arr[] of n integers, construct a Product Array prod[] (of
   * same size) such that prod[i] is equal to the product of all the elements of
   * arr[] except arr[i].
   * 
   * @param A
   */
  public static void productPuzzle(int[] A) {
    int[] frwd = new int[A.length];
    Arrays.fill(frwd, 1);
    int[] bkwrd = new int[A.length];
    Arrays.fill(bkwrd, 1);
    for (int i = 0; i < A.length - 1; i++) {
      for (int j = i + 1; j < A.length; j++) {
        frwd[i] = frwd[i] * A[j];
      }
    }
    for (int i = 0; i < A.length; i++) {
      for (int j = 0; j < i; j++) {
        bkwrd[i] = bkwrd[i] * A[j];
      }
    }
    for (int i = 0; i < A.length; i++) {
      A[i] = frwd[i] * bkwrd[i];
    }
    System.out.println(Arrays.toString(A));
  }

  /**
   * It is assummed that the array is sorted in ascending order. This will find
   * the floor x which is a value just smaller or equal to x
   * 
   * @param A The sorted array
   * @param low start index of A.
   * @param high End index of A
   * @param x floor which are interested in
   * @return
   */
  public static int floor(int[] A, int low, int high, int x) {
    if (x <= A[0]) return A[0];
    if (x > A[A.length - 1]) return A[A.length - 1];
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 < A.length && A[mid + 1] > x && A[mid] < x) return A[mid];
      else if (A[mid] == x) return A[mid - 1];
      else if (x < A[mid]) return floor(A, low, mid - 1, x);
      else return floor(A, mid + 1, high, x);
    } else return -1;
  }

  public static int ceiling(int[] A, int low, int high, int x) {
    if (x <= A[0]) return A[0];
    if (x >= A[A.length - 1]) return A[A.length - 1];
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 < A.length && A[mid + 1] > x && A[mid] < x) return A[mid + 1];
      else if (A[mid] == x) return A[mid + 1];
      else if (x < A[mid]) return ceiling(A, low, mid - 1, x);
      else return ceiling(A, mid + 1, high, x);
    } else return -1;
  }

  /**
   * Union of two sets which are in sorted order. Sets can't have duplicate
   * elements in it. Caller is expected to sort A and B before calling this
   * method. If the arrays are not sorted result is not defined.
   * 
   * @param A
   * @param B
   * @return
   */
  public static List<Integer> union(int[] A, int[] B) {
    List<Integer> unionList = new ArrayList<>(A.length);
    int i = 0, j = 0;
    while (i < A.length || j < B.length) {
      if (i != A.length && j != B.length) {
        if (A[i] < B[j]) unionList.add(A[i++]);
        else if (A[i] > B[j]) unionList.add(B[j++]);
        else {
          unionList.add(A[i++]);
          j++;
        }
      } else if (i == A.length) unionList.add(B[j++]);
      else unionList.add(A[i++]);
    }
    for (Integer u : unionList)
      System.out.println(u + " ");
    return unionList;
  }

  /**
   * Intersection of two sets which are in sorted order. Sets can't have
   * duplicate elements in it. Caller is expected to sort A and B before calling
   * this method. If the arrays are not sorted result is not defined.
   * 
   * @param A
   * @param B
   * @return
   */
  public static List<Integer> intersection(int[] A, int[] B) {
    List<Integer> intersectionList = new ArrayList<>(A.length);
    int i = 0, j = 0;
    while (i < A.length || j < B.length) {
      if (i != A.length && j != B.length) {
        if (A[i] < B[j]) i++;
        else if (A[i] > B[j]) j++;
        else {
          intersectionList.add(A[i++]);
          j++;
        }
      } else break;
    }
    for (Integer u : intersectionList)
      System.out.println(u + " ");
    return intersectionList;
  }

  /**
   * Given an array arr[] of integers, find out the difference between any two
   * elements such that larger element appears after the smaller number in
   * arr[].<br>
   * 
   * Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be
   * 8 (Diff between 10 and 2). If array is [ 7, 9, 5, 6, 3, 2 ] then returned
   * value should be 2 (Diff between 7 and 9)
   * 
   * @param A
   */
  public static int findMaxDifference(int[] A) {
    int maxDiff = 0;
    int maxNumOnRight = A[A.length - 1];
    for (int i = A.length - 2; i >= 0; i--) {
      int tempDiff = maxNumOnRight - A[i];
      if (A[i] < maxNumOnRight) maxDiff = tempDiff > maxDiff ? tempDiff
          : maxDiff;
      else maxNumOnRight = A[i];
    }
    return maxDiff;
  }

  /**
   * Given an array A[], write a function that segregates even and odd numbers.
   * The functions should put all even numbers first, and then odd numbers.
   * Example Input = {12, 34, 45, 9, 8, 90, 3} Output = {12, 34, 8, 90, 45, 9,
   * 3} The odrer of elements are mentained. The runs in O(n)
   */
  public static void seggregateEvenOdd(int[] A) {
    if (A == null || A.length == 0) return;
    int i = 0, j = 1;
    while (i < A.length) {
      if (A[i] % 2 != 0) {
        while (j < A.length && A[j] % 2 != 0) {
          j++;
        }
        if (j < A.length) {
          int temp = A[i];
          A[i] = A[j];
          A[j] = temp;
        } else break;
      } else j++;
      i++;
    }
  }

  /**
   * Given an array A[] consisting 0s, 1s and 2s, write a function that sorts
   * A[]. The functions should put all 0s first, then all 1s and all 2s in last.<br>
   * Example Input = {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1}; Output = {0, 0, 0, 0,
   * 0, 1, 1, 1, 1, 1, 2, 2} We use a 3-way partitioing of the elements.<br>
   * 
   * A[0...i] contains value zero A[i + 1...j] contains value one and [A.length
   * -1, k] contains value two. The values between j and k are not known while
   * the algorithm is running. The loop invariant is mentained prior to
   * initialization, during loop and upon termination
   */
  public static void sortArrayContainingZerosOnesAndTwo(int[] A) {
    int i = -1, j = 0, k = A.length;
    while (i < A.length - 1 && j < k) {
      if (A[j] == 0) {
        int temp = A[j];
        A[j] = A[i + 1];
        A[i + 1] = temp;
        i = i + 1;
        j = j + 1;
      } else if (A[j] == 1) j++;
      else {
        k = k - 1;
        while (j < k && A[k] == 2)
          k--;
        int temp = A[k];
        A[k] = A[j];
        A[j] = temp;
        if (A[j] == 0) {
          temp = A[i + 1];
          A[i + 1] = A[j];
          A[j] = temp;
          i = i + 1;
        }
        j = j + 1;
      }
    }
  }

  /**
   * Given an array of n elements which contains elements from 0 to n-1, with
   * any of these numbers appearing any number of times. Find these repeating
   * numbers in O(n) and using only constant memory space.<br>
   * 
   * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer
   * should be 1, 3 and 6.<br>
   * 
   * Algorithm : Use the same array as the counter or visited array instead of
   * different one. We can mark a particular element as visited but that element
   * values should be preserved. Therefore we can mark it as -ve of the number.<br>
   * 
   * Note that the range of number n is important here. If the size of the array
   * is n then it can hold only numbers from 0 to n-1. No negative numbers are
   * permitted
   * 
   * @param A
   */
  public static void findDuplicates(int[] A) {
    for (int i = 0; i < A.length; i++) {
      int a = Math.abs(A[i]);
      if (A[a] >= 0) A[a] = -A[a];
      else System.out.println(a);
    }
  }

  /**
   * To be done
   * 
   * @param A
   */
  public static void dijikstra3WayPartitioning(int[] A) {

  }

  /**
   * This is an in place rotation of a 2D matrix by 90 degrees. The algorithm
   * transforms each boundary of the matrix one by one. The outermost boundary
   * is considered first. A[i][j] takes the value of A[B-j+offset][i]. The
   * offset is required for the inner matrices for the next boundary rotation.
   * 
   * @param A
   * @param N
   */
  public static void rotateBy90Degree(int[][] A, int N) {
    if (A == null) return;
    int i = 0;
    int offset = 0;
    while (N != 0) {
      int j = i;
      int B = N - 1;
      int k = 0;
      for (k = 0; k < N - 1; k++) {
        int top = A[i][j];
        A[i][j] = A[B - j + offset][i];
        A[B - j + offset][i] = A[B - i + offset][B - j + offset];
        A[B - i + offset][B - j + offset] = A[j][B - i + offset];
        A[j][B - i + offset] = top;
        j++;
      }
      N -= 2;
      offset += 2;
      i++;
    }
    for (i = 0; i < A.length; i++) {
      for (int j = 0; j < A.length; j++) {
        System.out.print(A[i][j] + " ");
      }
      System.out.println();
    }
  }

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

  /**
   * Given a sorted array of n integers where each integer is in the range from
   * 0 to m-1 and m > n. Find the smallest number that is missing from the
   * array.<br>
   * 
   * Examples Input: {0, 1, 2, 6, 9}, n = 5, m = 10 Output: 3<br>
   * 
   * Input: {4, 5, 10, 11}, n = 4, m = 12 Output: 0 <br>
   * 
   * Input: {0, 1, 2, 3}, n = 4, m = 5 Output: 4<br>
   * 
   * Input: {0, 1, 2, 3, 4, 5, 6, 7, 10}, n = 9, m = 11 Output: 8<br>
   * 
   * The time complexity of this implementation is O(n). It could be done in
   * O(logn) using binary search technique.<br>
   * <b>Note: Try doing this using binary search in next iteration.<br>
   * 
   * @param A
   * @return
   */
  public static int smallestMissingElement(int[] A) {
    if (A == null) throw new NullPointerException();
    if (A[0] != 0) return 0;
    for (int i = 0; i < A.length - 1; i++) {
      int j = i + 1;
      if (A[j] - A[i] > 1) return A[i] + 1;
    }
    return A[A.length - 1] + 1;
  }

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

  /**
   * Given an array and an integer k, find the maximum for each and every
   * contiguous subarray of size k.<br>
   * 
   * Examples:<br>
   * Input : arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6} k = 3 Output : 3 3 4 5 5 5 6<br>
   * 
   * Input : arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13} k = 4 Output : 10 10 10
   * 15 15 90 90.<br>
   * 
   * <b>Note: There is a solution on geekforgeeks which is more optimized than
   * this. Take it in next iteration. Think other methods of solving it.
   * 
   * @param A
   * @param k
   */
  public static void findMaxOfEachSubArray(int[] A, int k) {
    Deque<Integer> deque = new ArrayDeque<>(k);
    if (A.length < k) return;
    int max = -1;
    for (int i = 0; i < k; i++) {
      deque.add(A[i]);
      if (A[i] > max) max = A[i];
    }
    System.out.println(max);
    for (int i = k; i < A.length; i++) {
      int item = deque.remove();
      deque.add(A[i]);
      if (item != max) {
        if (A[i] > max) {
          System.out.println(A[i]);
          max = A[i];
        } else System.out.println(max);
      } else {
        // If the element removed from the queue is max element
        // How can we optmize this part?
        max = -1;
        for (int q : deque)
          if (q > max) max = q;

        System.out.println(max);
      }
    }
  }

  /**
   * Given two arrays: arr1[0..m-1] and arr2[0..n-1]. Find whether arr2[] is a
   * subset of arr1[] or not. Both the arrays are not in sorted order.<br>
   * 
   * Examples:<br>
   * Input: Q[] = {11, 1, 13, 21, 3, 7}, P[] = {11, 3, 7, 1} Output: P[] is a
   * subset of Q[]<br>
   * 
   * Input: Q[] = {1, 2, 3, 4, 5, 6}, P[] = {1, 2, 4} Output: P[] is a subset of
   * Q[]<br>
   * 
   * Input: Q[] = {10, 5, 2, 23, 19}, P[] = {19, 5, 3} Output: P[] is not a
   * subset of Q[]<br>
   * <b> There are other methods also like sorting and then binary search,
   * Hashing.
   * 
   * @param P
   * @param Q
   * @return
   */
  public static boolean isSubset(int[] P, int[] Q) {
    if (P == null || Q == null) return true;
    Arrays.sort(P);
    Arrays.sort(Q);
    int i = 0;
    int j = 0;
    while (i < P.length && j < Q.length) {
      if (P[i] > Q[j]) j++;
      else if (P[i++] == Q[j++])
      ;
      else return false;
    }
    return i == P.length;
  }

  /**
   * Given an unsorted array arr[] and two numbers x and y, find the minimum
   * distance between x and y in arr[]. The array might also contain duplicates.
   * You may assume that both x and y are different and present in arr[].<br>
   * 
   * Examples:<br>
   * Input: arr[] = {1, 2}, x = 1, y = 2 Output: Minimum distance between 1 and
   * 2 is 1.<br>
   * 
   * Input: arr[] = {3, 4, 5}, x = 3, y = 5 Output: Minimum distance between 3
   * and 5 is 2.<br>
   * 
   * Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6 Output:
   * Minimum distance between 3 and 6 is 4.<br>
   * 
   * Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2 Output: Minimum
   * distance between 3 and 2 is 1<br>
   * 
   * Implementation details:<br>
   * We scan the array from left to right and when for the first time x or y is
   * found then we save the value of that index in i. We continue searching for
   * the other value. In course of the search if the same value as A[i] is found
   * then we change the value of i to j. If different value is found then we
   * take the difference and compare with the current min distance and then
   * again change the value of i to j; i is previous kind of index which points
   * to either x or y.
   * 
   * @param A
   * @param x
   * @param y
   */
  public static int minimumDistance(int[] A, int x, int y) {
    int i = -1;
    int min = Integer.MAX_VALUE;
    if (x == y) return 0;
    for (int j = 0; j < A.length; j++) {
      if (i == -1 && (A[j] == x || A[j] == y)) i = j;
      else if (i != -1 && A[j] == A[i]) i = j;
      else if (i != -1 && (A[j] == x || A[j] == y) && A[j] != A[i]) {
        if (j - i < min) {
          min = j - i;
        }
        i = j;
      }
    }
    return min;
  }

  /**
   * Given an unsorted array of size n. Array elements are in range from 1 to n.
   * One number from set {1, 2, …n} is missing and one number occurs twice in
   * array. Find these two numbers.<br>
   * Examples:<br>
   * 
   * arr[] = {3, 1, 3} Output: 2, 3 // 2 is missing and 3 occurs twice
   * 
   * arr[] = {4, 3, 6, 2, 1, 1} Output: 1, 5 // 5 is missing and 1 occurs twice<br>
   * <b> Note: This is a very awesome technique
   * 
   * @param A
   * @param n
   */
  public static void findRepeatingAndMissing(int[] A, int n) {
    if (A == null) return;
    int xorArray = 0;
    for (int x : A)
      xorArray = xorArray ^ x;

    for (int x = 1; x <= n; x++)
      xorArray = xorArray ^ x;

    // To get the rightmost bit
    int setbit = xorArray & ~(xorArray - 1);
    int a = 0, b = 0;
    for (int x : A) {
      if ((x & setbit) > 0) a = a ^ x;
      else b = b ^ x;
    }
    for (int x = 1; x <= n; x++) {
      if ((x & setbit) > 0) a = a ^ x;
      else b = b ^ x;
    }
    System.out.println(a + " " + b);
  }

  public static void spiralVisit(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;
    // Current row and column
    int r = 0;
    int c = 0;
    while (r < m && c < n) {
      // Visit top row
      for (int i = c; i < n; i++)
        System.out.println(A[r][i]);
      // Visit right column
      for (int i = r + 1; i < m; i++)
        System.out.println(A[i][n - 1]);
      // Visit bottom row
      for (int i = n - 2; i >= c && m - 1 > r; i--)
        System.out.println(A[m - 1][i]);
      // Visit first column
      for (int i = m - 2; i > r; i--)
        System.out.println(A[i][c]);

      r++;
      c++;
      m--;
      n--;
    }
  }

  public static int[][] modifyMatrix(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;
    boolean[] rowsDone = new boolean[m];
    boolean[] colDone = new boolean[n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1) {
          rowsDone[i] = true;
          colDone[j] = true;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (rowsDone[i] || colDone[j]) A[i][j] = 1;
      }
    }
    return A;
  }

  /**
   * A slower method of doing the manipulation as modifyMatrix. But this doesnot
   * uses any extra memory 3N^2 + some more time in striking off the rows and
   * col repeating tasks which aready has been done.
   * 
   * @param A
   * @return
   */
  public static int[][] modifyMatrix1(int[][] A) {
    if (A == null) throw new NullPointerException();
    int m = A.length;
    int n = A[0].length;

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1) {
          A[i][j] = 2;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 2) {
          for (int k = 0; k < m; k++)
            if (A[k][j] != 2) A[k][j] = 1;

          for (int k = 0; k < n; k++)
            if (A[i][k] != 2) A[i][k] = 1;
        }
      }
    }
    // Change back 2 to 1
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 2) {
          A[i][j] = 1;
        }
      }
    }
    return A;
  }

  /**
   * Given an array of n distinct integers sorted in ascending order, write a
   * function that returns a Fixed Point in the array, if there is any Fixed
   * Point present in array, else returns -1. Fixed Point in an array is an
   * index i such that arr[i] is equal to i. Note that integers in array can be
   * negative.<br>
   * 
   * Examples:<br>
   * 
   * Input: arr[] = {-10, -5, 0, 3, 7} Output: 3 // arr[3] == 3<br>
   * 
   * Input: arr[] = {0, 2, 5, 8, 17} Output: 0 // arr[0] == 0<br>
   * 
   * Input: arr[] = {-10, -5, 3, 4, 7, 9} Output: -1 // No Fixed Point<br>
   * 
   * Algorithm : Uses binary search for the required point.
   * 
   * @param A
   * @param low
   * @param high
   * @return
   */
  public static int fixedPoint(int[] A, int low, int high) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = (low + high) / 2; // mid index
      if (mid == A[mid]) return mid;
      else if (mid < A[mid] /* < A[m + 1] */) return fixedPoint(A, low, mid - 1);
      else return fixedPoint(A, mid + 1, high);
    } else return -1;
  }

  /**
   * Given an array of integers which is initially increasing and then
   * decreasing, find the maximum value in the array.<br>
   * 
   * Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1} Output: 500<br>
   * 
   * Input: arr[] = {1, 3, 50, 10, 9, 7, 6} Output: 50<br>
   * 
   * Corner case (No decreasing part) Input: arr[] = {10, 20, 30, 40, 50}
   * Output: 50<br>
   * 
   * Corner case (No increasing part) Input: arr[] = {120, 100, 80, 20, 0}
   * Output: 120<br>
   * 
   * @param A
   * @param low
   * @param high
   * @return
   */
  public static int maximumInBitonic(int[] A, int low, int high) {
    if (A == null) throw new NullPointerException();
    if (low <= high) {
      int mid = (low + high) / 2;
      if (mid + 1 >= A.length || mid - 1 < 0) return A[mid];
      if (A[mid] > A[mid + 1] && A[mid] > A[mid - 1]) return A[mid];
      else if (A[mid + 1] < A[mid] && A[mid - 1] > A[mid]) return maximumInBitonic(
          A, low, mid - 1);
      else return maximumInBitonic(A, mid + 1, high);
    } else return -1;
  }

  /**
   * Write a function to count number of smaller elements on right of each
   * element in an array. Given an unsorted array arr[] of distinct integers,
   * construct another array countSmaller[] such that countSmaller[i] contains
   * count of smaller elements on right side of each element arr[i] in array.<br>
   * 
   * Examples:
   * 
   * Input: A[] = {12, 1, 2, 3, 0, 11, 4} Output: count[] = {6, 1, 1, 1, 0, 1,
   * 0}<br>
   * 
   * (Corner Cases) Input: A[] = {5, 4, 3, 2, 1} Output: count[] = {4, 3, 2, 1,
   * 0}<br>
   * 
   * Input: A[] = {1, 2, 3, 4, 5} Output: count[] = {0, 0, 0, 0, 0}<br>
   * 
   * @param A
   */
  public static void countrightSmallerElements(int[] A) {
    if (A == null) throw new NullPointerException();
    BST<Integer, Integer> bst = new BST<>();
    int[] count = new int[A.length];
    for (int i = A.length - 1; i >= 0; i--) {
      bst.insert(A[i], A[i]);
      bst.countRightSmallerElements(bst.getRootNode(), count, i, A[i]);
    }
    System.out.println(Arrays.toString(count));
  }

  private static int   minSteps    = Integer.MAX_VALUE;
  private static int[] memoization = new int[50];

  /**
   * Nice problem. Do revise before interview
   * 
   * @param A
   * @param start
   * @param end
   * @param steps
   */
  public static void countMinStepsToReachEnd(int[] A, int start, int end,
      int steps) {
    if (A == null) throw new NullPointerException();
    if (start == end) {
      if (minSteps > steps) {
        minSteps = steps;
        memoization[start] = minSteps;
      }
    }
    if (start < end) {
      for (int i = start + 1; i <= end && i <= start + A[start]; i++) {
        // steps = steps + 1; Wrong to update the steps variable here since it
        // will pass different steps value to all the fucntion call from this
        // fucntion call level.
        if (memoization[i] > 0 && minSteps > steps) {
          // step + 1 is required here since the next function call would have
          // increased steps by 1 but we are not call the function
          minSteps = steps + 1;
        } else countMinStepsToReachEnd(A, i, end, steps + 1);
      }
    }
  }

  /*
   * public static void countMinStepsToReachEndBo(int[] A, int n) { if (A ==
   * null) throw new NullPointerException(); memoization[0] = 0; for (int j = 1;
   * j < n; j++) { int steps = Integer.MAX_VALUE; for (int i = 0; i < j && i <=
   * i + A[i]; i++) { steps = min(steps, memoization[i] + 1); } memoization[j] =
   * steps; } System.out.println(memoization[n - 1]); }
   */

  /**
   * LIS(A[1..n]): (A) Case 1: does not contain an in which case LIS(A[1..n]) =
   * LIS(A[1..(n - 1)])
   *
   * (B) Case 2: contains an in which case LIS(A[1..n]) is not so clear.<br>
   * 
   * Observation:<br>
   * For second case we want to find a subsequence in A[1..(n-1)] that is
   * restricted to numbers less than an. This suggests that a more general
   * problem is LIS smaller(A[1..n], x) which gives the longest increasing
   * subsequence in A where each number in the sequence is less than x.
   * 
   * @param A
   * @param x
   * @return
   */
  public static int lis(int[] A, int n, int x) {
    if (n == 0) return 0;
    int m = lis(A, n - 1, x);
    if (A[n - 1] < x) { // this can also be A[1...n] since this is less than x
      m = max(m, 1 + lis(A, n - 1, A[n - 1]));
    }
    return m;
  }

  public static int min(int a, int b) {
    return a < b ? a : b;
  }

  /**
   * Given an unsorted array of nonnegative integers, find a continous subarray
   * which adds to a given number.<br>
   * 
   * Examples:<br>
   * 
   * Input: arr[] = {1, 4, 20, 3, 10, 5}, sum = 33 Ouptut: Sum found between
   * indexes 2 and 4<br>
   * 
   * Input: arr[] = {1, 4, 0, 0, 3, 10, 5}, sum = 7 Ouptut: Sum found between
   * indexes 1 and 4<br>
   * 
   * Input: arr[] = {1, 4}, sum = 0 Output: No subarray found<br>
   * 
   * <b> Note: not very comfortable wiht j - 1;
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean subArraySum(int[] A, int sum) {
    if (A == null) throw new NullPointerException();
    int i = 0, j = 0;
    int subSum = A[0];
    i = 0;
    for (j = 1; j <= A.length; j++) {
      while (i < j - 1 && subSum > sum) {
        subSum -= A[i];
        i++;
      }
      if (subSum == sum) return true;
      if (j < A.length) subSum += A[j];
    }
    return false;
  }

  /**
   * Given an array and a value, find if there is a triplet in array whose sum
   * is equal to the given value. If there is such a triplet present in array,
   * then print the triplet and return true. Else return false. For example, if
   * the given array is {12, 3, 4, 1, 6, 9} and given sum is 24, then there is a
   * triplet (12, 3 and 9) present in array whose sum is 24.<br>
   * <br>
   * 
   * <b>Note:</b> This is O(n^3) algorithm: Can we do better?
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean findTriplet(int[] A, int sum) {
    if (A == null) return false;
    if (A.length < 3) return false;
    for (int i = 0; i <= A.length - 3; i++) {
      for (int j = i + 1; j <= A.length - 2; j++) {
        for (int k = j + 1; k <= A.length - 1; k++) {
          if (A[i] + A[j] + A[k] == sum) {
            System.out.println(A[i] + " " + A[j] + " " + A[k]);
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * This method uses the problem of finding 2 sum by fixing A[i] and checking
   * for sum - A[i]. If the sum - A[i] is possible in A then A[i] with the other
   * two elements for which b + c = sum - A[i] are triplets.<br>
   * <br>
   * Time complexity = O(n^2);
   * 
   * @param A
   * @param sum
   * @return
   */
  public static boolean findTripletUsingTwoSum(int[] A, int sum) {
    if (A == null) throw new NullPointerException();
    boolean isTripletFound = false;
    for (int i = 0; i <= A.length - 3; i++) {
      isTripletFound = isTwoSumPossible(A, sum - A[i]);
    }
    return isTripletFound;
  }

  /**
   * Seggregate negative and positive numbers in an array.
   * 
   * @param A
   * @return
   */
  public static int seggregateNegPos(int[] A) {
    if (A == null) throw new NullPointerException();
    int i = -1;
    int j = 0;
    for (j = 0; j < A.length; j++) {
      if (A[j] >= 0) {
        int temp = A[i + 1];
        A[i + 1] = A[j];
        A[j] = temp;
        i++;
      }
    }
    return i;
  }

  private static boolean doesKnow(int i, int j) {
    boolean know[][] = { { false, false, true, false },
        { false, false, true, false }, { false, false, false, false },
        { false, false, true, false } };
    return know[i][j];
  }

  /**
   * In a party of N people, only one person is known to everyone. Such a person
   * may be present in the party, if yes, (s)he doesn’t know anyone in the
   * party. We can only ask questions like “does A know B? “. Find the stranger
   * (celebrity) in minimum number of questions.
   * 
   * We can describe the problem input as an array of numbers/characters
   * representing persons in the party. We also have a hypothetical function
   * HaveAcquaintance(A, B) which returns true if A knows B, false otherwise
   * 
   * @param A
   * @param n
   * @param low
   * @param high
   * @return
   */
  public static int celebrity(int[] A, int n, int low, int high) {
    if (n == 2) {
      if (doesKnow(A[low], A[high])) return A[high];
      else return A[low];
    }
    int c = celebrity(A, n - 1, low, high - 1);
    if (!doesKnow(c, A[high])) {
      return c;
    } else {
      boolean isCeleb = true;
      for (int i = low; i <= high - 1; i++) {
        if (doesKnow(A[n], A[i])) isCeleb = false;
      }
      if (isCeleb) return A[high];
      else return -1;
    }
  }

  public static int coinChange(int[] coins, int sum) {
    if (coins == null || coins.length == 0) return -1;
    if (sum == 0) return 0;

    int m = Integer.MAX_VALUE;
    for (int i = 0; i < coins.length; i++) {
      if (sum - coins[i] >= 0)
        m = min(m, 1 + coinChange(coins, sum - coins[i]));
    }
    return m;
  }

  public static int dynamicCoinChange(int[] coins, int sum) {
    int[] C = new int[sum + 1];
    C[0] = 0;

    for (int i = 1; i <= sum; i++) {
      C[i] = Integer.MAX_VALUE;
      int min = Integer.MAX_VALUE;
      for (int c = 0; c < coins.length; c++) {
        if (i - coins[c] >= 0) {
          if (1 + C[i - coins[c]] < min) min = 1 + C[i - coins[c]];
        }
      }
      C[i] = min;
    }
    return C[C.length - 1];
  }

  /**
   * Given an array of integers, find all combination of four elements in the
   * array whose sum is equal to a given value X. For example, if the given
   * array is {10, 2, 3, 4, 5, 9, 7, 8} and X = 23, then your function should
   * print “3 5 7 8″ (3 + 5 + 7 + 8 = 23).<br>
   * 
   * Time complexit = O(n^3logn)
   * 
   * @param A
   * @param sum
   */
  public static void find4NumbersEqualToSum(int[] A, int sum) {
    Arrays.sort(A);
    for (int i = 0; i < A.length - 1; i++) {
      for (int j = i + 1; j < A.length; j++) {
        if (twoSum(A, sum - A[i] - A[j])) {
          System.out.println(A[i] + " " + A[j] + " ");
          return;
        }
      }
    }
  }

  /**
   * Given an array of integers, replace every element with the next greatest
   * element (greatest element on the right side) in the array. Since there is
   * no element next to the last element, replace it with -1. For example, if
   * the array is {16, 17, 4, 3, 5, 2}, then it should be modified to {17, 5, 5,
   * 5, 2, -1}<br>
   * 
   * <b> Time complexity : O(n)
   * 
   * @param A
   */
  public static void replaceElementWithNextGreatest(int[] A) {
    if (A == null) throw new NullPointerException();
    /* Travesing from right and maintain the current max of A[i, A.length -1] */
    int max = A[A.length - 1];
    A[A.length - 1] = -1;
    for (int i = A.length - 2; i >= 0; i--) {
      int temp = A[i];
      A[i] = max;
      if (temp > max) max = temp;
    }
  }

  /**
   * The longest palindromic subsequence (LPS) problem is the problem of finding
   * the longest subsequence of a string (a subsequence is obtained by deleting
   * some of the characters from a string without reordering the remaining
   * characters) which is also a palindrome. In general, the longest palindromic
   * subsequence is not unique. For example, the string alfalfa has four
   * palindromic subsequences of length 5: alala, afafa, alfla, and aflfa.
   * However, it does not have any palindromic subsequences longer than five
   * characters. Therefore all four are considred longest palindromic
   * subsequences of alfalfa.
   * 
   * @param S
   * @param i
   * @param j
   * @return
   */
  public static int longestPalindromicSubSeq(char[] S, int i, int j) {
    if (S == null) throw new NullPointerException();
    if (i == j) return 1;
    if (i < j) {
      if (S[i] == S[j]) return i + 1 == j ? 2 : longestPalindromicSubSeq(S,
          i + 1, j - 1) + 2;
      else return max(longestPalindromicSubSeq(S, i + 1, j),
          longestPalindromicSubSeq(S, i, j - 1));
    } else return -1;
  }
  
  public static boolean subsetSum(int[] A) {
    int sum = 0;
    for(int x : A) 
      sum += x;

    return subsetSum(A, A.length, sum);
  }

  /**
   * 
   * @param A
   * @param n
   * @param sum
   * @return
   */
  public static boolean subsetSum(int[] A, int n, int sum) {
    if (sum == 0) return true;
    if (n == 0 && sum != 0) return false;
    // If the value of A[n - 1] > sum then it can't be included in subset.
    if (sum - A[n - 1] < 0) return subsetSum(A, n - 1, sum);
    return subsetSum(A, n - 1, sum) || subsetSum(A, n - 1, sum - A[n - 1]);
  }


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
  
  
  
  /*public static boolean increasingSubSeqOfSize3Recursive(int[] A, int n){
    
    if (n == 3) {
      if (A[n - 3] < A[n - 2] && A[n - 2] < A[n - 1]) return true;
      else return false;
    }

    boolean temp1 = false;
    int j;
    for(j = n - 2; j >= 0; j--){
      if(A[j] < A[n - 1]){ 
        temp1 = true;
        break;
      }
    }
    boolean temp2 = false;
    for(int i = j - 1; i >= 0; i--){
      if(A[i] < A[j]){ 
        temp2 = true;
        break;
      }
    }
    if(temp1 && temp2){
      return true;
    }else return increasingSubSeqOfSize3Recursive(A, n - 1);
  }*/
  
  /**
   * 1 .Dynamic Programming | Set 15 (Longest Bitonic Subsequence) 2. Stack
   * method The Celebrity Problem 3. Find a sorted subsequence of size 3 in
   * linear time 4.Find a sorted subsequence of size 3 in linear time
   * 
   * @param args
   */

  public static void main(String[] args) {
    int[] A = new int[] {12, 11, 10, 5, 6, 2, 30};
  
  }

  class DoubleStack {

    int   top1;
    int   top2;
    int[] S;
    int   maxBufferSize;

    public DoubleStack(int maxBufferSize) {
      this.top1 = -1;
      this.top2 = maxBufferSize;
      this.maxBufferSize = maxBufferSize;
      this.S = new int[maxBufferSize];
    }

    public void pushIntoLeftStack(int item) {
      if (isLeftStackFull()) return;
      S[++top1] = item;
    }

    public void pushIntoRightStack(int item) {
      if (isRightStackFull()) return;
      S[--top2] = item;
    }

    public boolean isLeftStackFull() {
      return (top1 == top2 - 1);
    }

    public boolean isRightStackFull() {
      return (top2 == top1 + 1);
    }

    public int popLeft() {
      if (isLeftStackEmpty()) return -1;
      return S[top1--];
    }

    public int popRight() {
      if (isRightStackEmpty()) return -1;
      return S[top2++];
    }

    public boolean isLeftStackEmpty() {
      return (top1 == -1);
    }

    public boolean isRightStackEmpty() {
      return (top2 == maxBufferSize);
    }

  }
}
