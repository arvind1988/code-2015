package algo.arrayp;

import java.util.Arrays;
import algo.ds.binarytree.BST;

public class SmallerElementsOnRight {

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

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
