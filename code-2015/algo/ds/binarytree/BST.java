package algo.ds.binarytree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> extends BinaryTree<K, V> {
  // private Node root;
  // private int size;

  /*
   * private class Node { private K key; private V value; private Node left;
   * private Node right; private Node parent; Node(K key, V value, Node parent)
   * { this.key = key; this.value = value; left = null; right = null;
   * this.parent = parent; } }
   */

  public BST() {
    super();
  }

  public BST(Node root) {
    super(root);
  }

  public Node getRootNode() {
    return root;
  }

  public void insert(K key, V val) {
    if (key == null) throw new IllegalArgumentException("Null is not allowed");
    if (root == null) {
      root = new Node(key, val, null);
      return;
    }
    Node curr = root;
    while (curr != null) {
      int cmp = key.compareTo(curr.key);
      if (cmp > 0) {
        if (curr.right == null) {
          curr.right = new Node(key, val, curr);
          updateSubTreeSizes(root, curr.right);
          return;
        } else curr = curr.right;
      } else if (cmp < 0) {
        if (curr.left == null) {
          curr.left = new Node(key, val, curr);
          updateSubTreeSizes(root, curr.left);
          return;
        } else curr = curr.left;
      } else {
        System.out.println("Duplicate key");
        return;
      }
    }
  }

  /**
   * Here I have considered that there is no parent pointer available and and
   * hence using recursion to update the subSizes
   * 
   * @param r root node
   * @param curr Newly added node
   * @return
   */
  private boolean updateSubTreeSizes(Node r, Node curr) {
    if (r == null) return false;
    if (r == curr) return true;
    if (updateSubTreeSizes(r.left, curr) || updateSubTreeSizes(r.right, curr)) {
      r.subSize = r.subSize + 1;
      return true;
    } else return false;
  }

  public Node insertRecurr(K key, V val) {
    return null;
  }

  /**
   * Search the tree for the key and returns its associated value.
   */
  @Override
  public V getValue(K key) {
    if (key == null) throw new NullPointerException("Key is null");
    return getValue(root, key);
  }

  private V getValue(Node root, K key) {
    if (root == null) return null;
    int cmp = key.compareTo(root.key);
    if (cmp > 0) return getValue(root.right, key);
    else if (cmp < 0) return getValue(root.left, key);
    else return root.value;
  }

  @Override
  public K getMaximum() {
    return getMaximum(root).key;
  }

  private Node getMaximum(Node root) {
    if (root == null) return null; // Base case
    if (root.right == null) return root;
    else return getMaximum(root.right);
  }

  @Override
  public K getMinimum() {
    return getMinimum(root).key;
  }

  private Node getMinimum(Node root) {
    if (root == null) return null; // Base case
    if (root.left == null) return root;
    else return getMinimum(root.left);
  }

  public K getSucessor(K key) {
    if (key == null) throw new NullPointerException("Null key");
    return getSucessor(root, key).key;
  }

  private Node getSucessor(Node root, K key) {
    if (root == null) return null;
    Node keyNode = getNode(root, key);
    assert keyNode != null;
    if (keyNode.right != null) return getMinimum(keyNode.right);
    Node p = keyNode;
    while (p.parent != null && p != p.parent.left)
      p = p.parent;
    return (p.parent != null) ? p.parent : keyNode;
  }

  public K getPredecessor(K key) {
    if (key == null) throw new NullPointerException("Null key");
    return getPredecessor(root, key).key;
  }

  private Node getPredecessor(Node x, K key) {
    if (root == null) return null;
    Node keyNode = getNode(root, key);
    assert keyNode != null;
    if (keyNode.left != null) return getMaximum(keyNode.left);
    Node p = keyNode;
    while (p.parent != null && p != p.parent.right)
      p = p.parent;
    return (p.parent != null) ? p.parent : keyNode;
  }

  private Node getNode(Node root, K key) {
    if (key == null) throw new NullPointerException("Null key");
    if (root == null) return null;
    int cmp = key.compareTo(root.key);
    if (cmp > 0) return getNode(root.right, key);
    else if (cmp < 0) return getNode(root.left, key);
    else return root;

  }

  public void deleteMin() {
    root = deleteMin(root);
  }

  private Node deleteMin(Node x) {
    if (x == null) return null;
    if (x.left == null) return x.right;
    x.left = deleteMin(x.left);
    return x;
  }


  public void deleteMax() {
    deleteMax(root);
  }

  private Node deleteMax(Node x) {
    if (x == null) return null;
    if (x.right == null) return x.left;
    x.right = deleteMax(x.right);
    return x;
  }


  /**
   * Lcs : Lowest commmon ancestor The lowest common ancestor between two nodes
   * n1 and n2 is defined as the lowest node in T that has both n1 and n2 as
   * descendants (where we allow a node to be a descendant of itself).
   * 
   * @return lcs of k1 and k2
   */
  public K lca(K k1, K k2) {
    return lca(root, k1, k2).key;
  }

  private Node lca(Node x, K k1, K k2) {
    if (x == null) return null;
    int cmpK1 = k1.compareTo(x.key);
    int cmpK2 = k2.compareTo(x.key);
    if (cmpK1 < 0 && cmpK2 < 0) return lca(x.left, k1, k2);
    else if (cmpK1 > 0 && cmpK2 > 0) return lca(x.right, k1, k2);
    else return x;
  }

  public boolean isBst() {
    return isBst(root);
  }

  private boolean isBst(Node x) {
    if (x == null) return true;
    boolean cmpLeft = true, cmpRight = true;
    if (x.left != null)
      cmpLeft = this.getMaximum(x.left).key.compareTo(x.key) < 0;
    if (x.right != null)
      cmpRight = this.getMinimum(x.right).key.compareTo(x.key) > 0;
    return isBst(x.left) && isBst(x.right) && cmpLeft && cmpRight;
  }

  /**
   * Given two values a and kb (where a < b) and a root pointer to a Binary
   * Search Tree. Print all the keys of tree in range a to b. i.e. print all x
   * such that a <= x <= b and x is a key of given BST. Print all the keys in
   * increasing order.<br>
   * This implementation requires the binary tree to have integer keys,
   * otherwise it throws an unchecked UnsupportedOperationException exception
   * 
   * @param a lower bound of the range
   * @param b upper bound of the range
   */
  public void printKeysInRange(int a, int b) {
    if (root != null && !(root.key instanceof Integer))
      throw new UnsupportedOperationException("keys must be Integers");

    printKeysInRange(root, a, b);
  }

  private void printKeysInRange(Node x, int a, int b) {
    if (x == null) return;
    if ((int) x.key <= b && (int) x.key >= a) {
      printKeysInRange(x.left, a, b);
      System.out.println((int) x.key);
      printKeysInRange(x.right, a, b);
    } else if ((int) x.key > b) printKeysInRange(x.left, a, b);
    else printKeysInRange(x.right, a, b);

  }

  /**
   * Rank of key is the number of elements less than key. To find the rank of a
   * key we maintain a field "subSize" in every node of the tree. It hold size
   * of the sub-tree root at the node.
   * 
   * @param key
   * @return rank of the key
   */
  public int rank(K key) {
    return rank(root, key);
  }

  private int rank(Node x, K key) {
    if (x == null) return 0; // need to know about this
    int cmp = key.compareTo(x.key);
    if (cmp == 0) return (x.left != null ? x.left.subSize : 0);
    else if (cmp < 0) return rank(x.left, key);
    else return 1 + (x.left != null ? x.left.subSize : 0) + rank(x.right, key);
  }

  /**
   * Count the number of binary search trees possible if N keys are given from 1
   * to N.
   * 
   * <pre>
   * If say we choose 1 as the root then no element could be inserted in the left of 1 but n-1
   * elements could be inserted on the right of 1. Lets assume that f(n) gives the number of
   * binaries trees poosible with n distinct keys. Now if choose ith element then i-1 elements
   * may be inserted on the left of ith element and n-i to the right hence by law of multiplication 
   * we can say that number of binaries tree possible is f(i-1) * f(n-i). Summing over all the i 
   * we get the number of binaries trees possible.
   * <a>http://gatecse.in/wiki/Number_of_Binary_trees_possible_with_n_nodes</a>
   * </pre>
   * 
   * @param N
   * @return
   */
  public int countBST(int N) {
    int sum = 0;
    if (N <= 1) return 1;
    else {
      for (int i = 1; i <= N; i++)
        sum += countBST(i - 1) * countBST(N - i);
      return sum;
    }
  }

  /**
   * This method takes a sorted array and converts it into a balanced binary
   * tree;
   * 
   * @param keys
   * @param l low index into the array
   * @param h high index into the array
   * @return pointer to the newly created tree
   */
  private Node createBSTFromSortedKeys(K[] keys, int l, int h) {
    if (l <= h) {
      int mid = (l + h) / 2;
      Node n = new Node(keys[mid], null, null);
      n.left = createBSTFromSortedKeys(keys, l, mid - 1);
      n.right = createBSTFromSortedKeys(keys, mid + 1, h);
      return n;
    } else if (l == h) return new Node(keys[l], null, null);
    else return null;
  }

  public BST<K, V> getBSTFromSortedKeys(K[] keys, int l, int h) {
    BST<K, V> bst = new BST<>(createBSTFromSortedKeys(keys, l, h));
    return bst;
  }

  /**
   * This method gets called from ArraysOperations class and is assumed that
   * Node x is the root of the BST which has been constructed using a array of
   * numbers by traversing from right to left. If the BST is not constructed in
   * the above way then the results are unexpected and in most of the cases will
   * be wrong.
   * 
   * @param x
   * @param count
   * @param index
   */
  public void countRightSmallerElements(Node x, int[] count, int index, K elem) {
    if (count == null) throw new NullPointerException();
    if (x != null) {
      int cmp = x.key.compareTo(elem);
      if (cmp == 0) count[index] += 0;
      else if (cmp < 0) {
        count[index] = x.left != null ? count[index] + x.left.subSize + 1
            : count[index] + 1;
        countRightSmallerElements(x.right, count, index, elem);
      } else countRightSmallerElements(x.left, count, index, elem);
    }
  }

  /*
   * private int min, max, globalMax, size; public int maxBstSubtree(){
   * maxBstSubtree(root); return globalMax; } private boolean maxBstSubtree(Node
   * x){ int leftCount= 0; int rightCount = 0; int count = 0; int min if(x ==
   * null){ return true; } if(x.left == null && x.right == null) { count =
   * leftCount + rightCount; min = (int)x.key; min = (int)x.key; globalMax = 1;
   * return true; } if(maxBstSubtree(x.left) && maxBstSubtree(x.right) &&
   * (int)x.key > max && (int)x.key < min){ max = (int)x.key; min = (int)x.key;
   * } }
   */

  public void diagonalSums() {
    diagonalSums(root);
  }

  private void diagonalSums(Node x) {
    if (x == null) return;
    Queue<Node> queue = new LinkedList<>();
    queue.add(x);
    while (!queue.isEmpty()) {
      int count = queue.size();
      int diSum = 0;
      while (count != 0) {
        Node curr = queue.remove();
        System.out.print(curr.key + " ");
        diSum += (int) curr.key;
        if (curr.left != null) queue.add(curr.left);
        while (curr.right != null) {
          System.out.print(curr.right.key + " ");
          diSum += (int) curr.right.key;
          if (curr.right.left != null) queue.add(curr.right.left);
          curr = curr.right;
        }
        count--;
      }
      System.out.println(": Sum = " + diSum);
      System.out.println();
    }
  }

  public void deleteNode(K key){
    root = deleteNode(root, key);
  }

  /**
   * See this implementation again and again to get yourself comfortable with
   * it. It really deletes :) This is implementation of deletion of a node in
   * BST. This is capable of deleting any node from the tree.
   * 
   * @param x
   * @param key
   * @return
   */
  private Node deleteNode(Node x, K key) {
    if (x == null) return null;
    int cmp = key.compareTo(x.key);
    if (cmp < 0) x.left = deleteNode(x.left, key);
    else if (cmp > 0) x.right = deleteNode(x.right, key);
    else {
      //Case 1 : 0 or 1 child
      if (x.left == null) return x.right;
      if (x.right == null) return x.left;
      
      // Case 2: 2 children
      Node t = x;
      x = getMinimum(t.right);
      x.right = deleteMin(t.right);
      x.left = t.left;
    }
    //x.subSize = x.left.subSize + x.right.subSize + 1;
    return x;
  }
  
  
  
  public static void main(String[] args) {
    BST<Integer, String> bst = new BST<>();
    bst.insert(7, "A");
    bst.insert(12, "B");
    bst.insert(4, "C");
   // bst.insert(2, "D");
    bst.insert(6, "E");
    bst.insert(5, "F");
    //bst.insert(3, "G");
    bst.insert(9, "H");
    bst.insert(8, "I");
    bst.insert(11, "J");
    bst.insert(19, "K");
    bst.insert(15, "L");
    bst.insert(20, "M");
    bst.insert(10, "a");
    BST<Integer, String>.Node root = bst.getRootNode();
    //bst.printLeftBoundary(root);
    //System.out.println();
    bst.printAllBoundary(root);
    //bst.printPathSums(bst.getRootNode(), 0);
    //bst.bottomView();

  }
}
