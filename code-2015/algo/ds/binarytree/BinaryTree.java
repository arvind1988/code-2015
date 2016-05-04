package algo.ds.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTree<K extends Comparable<K>, V> {
  protected Node root;
  protected int  size = 0;

  protected class Node {
    protected K    key;
    protected V    value;
    protected Node left;
    protected Node right;
    protected Node parent;
    protected Node next;     // Point to the inorder successor of this node
    protected Node nextRight;
    protected int  subSize;
    protected int  level;

    public Node(K key, V value, Node parent) {
      this.key = key;
      this.value = value;
      left = null;
      right = null;
      this.parent = parent;
      this.subSize = 1;
    }
  }

  /**
   * We have to make the constructor public for this to be able to be visible in
   * subclass. This class could be accesed from any class.
   * 
   * @author Arvind_Kumar2
   *
   */

  public BinaryTree() {
  }

  public BinaryTree(Node root) {
    this.root = root;
  }

  private Node getRootNode() {
    return root;
  }

  /**
   * This function is only applicable to Binary Tree which are not subtree
   * 
   * @param n
   * @param key
   * @param val
   * @param inLeft
   * @return
   */
  public Node insert(Node n, K key, V val, boolean inLeft) {
    if (this instanceof BST) throw new UnsupportedOperationException();
    if (size == 0) {
      root = new Node(key, val, null);
      root.parent = null;
      size += 1;
      root.level = 0;
      return root;
    }
    if (n == null) throw new NullPointerException("Void insertion");
    if (inLeft) {
      if (n.left == null) {
        Node newNode = new Node(key, val, n);
        newNode.level = n.level + 1; // level will be one more than its parent;
        n.left = newNode;
        newNode.parent = n;
        size += 1;
        return newNode;
      } else throw new IllegalArgumentException("Invalid insetion");
    } else {
      if (n.right == null) {
        Node newNode = new Node(key, val, n);
        n.right = newNode;
        newNode.level = n.level + 1;
        newNode.parent = n;
        size += 1;
        return newNode;
      } else throw new IllegalArgumentException("Invalid insetion");
    }
  }

  public V getValue(K key) {
    return getValue(root, key);
  }

  private void updateKey(Node x, K key) {
    x.key = key;
  }

  private V getValue(Node root, K key) {
    if (root == null) return null;
    V value = null;
    if (key.compareTo(root.key) == 0) value = root.value;
    else {
      V v1 = getValue(root.left, key);
      V v2 = getValue(root.right, key);
      if (v1 != null) return v1;
      else if (v2 != null) return v2;
      else return v1; // both v1 and v2 are null return anything
    }
    return value;
  }

  public boolean isLeft(Node p) {
    if (p == null) throw new NullPointerException("Null passed");
    if (p.parent == null)
      throw new IllegalArgumentException("p is the root node");
    return (p.parent.left == p);
  }

  public boolean isRight(Node p) {
    if (p == null) throw new NullPointerException("Null passed");
    if (p.parent == null)
      throw new IllegalArgumentException("p is the root node");
    return (p.parent.right == p);
  }

  /**
   * Returns the left node of p
   * 
   * @param p
   * @return
   */
  public Node left(Node p) {
    if (p == null) throw new NullPointerException("Null passed");
    return p.left;
  }

  /**
   * Returns the right node of p
   * 
   * @param p
   * @return
   */
  public Node right(Node p) {
    if (p == null) throw new NullPointerException("Null passed");
    return p.right;
  }

  /**
   * Returns the parent of p
   * 
   * @param p
   * @return
   */
  public Node parent(Node p) {
    if (p == null) throw new NullPointerException("Null passed");
    return p.parent;
  }

  /**
   * Maximum in this binary tree
   * 
   * @return Key
   */
  public K getMaximum() {
    if (root == null) throw new NullPointerException();
    return getMaximum(root);
  }

  private K getMaximum(Node root) {
    if (root == null) return null;
    if (root.left == null && root.right == null) return root.key;
    K maxLeft = null;
    K maxRight = null;
    if (root.left == null) {
      maxRight = getMaximum(root.right);
      return maxRight.compareTo(root.key) > 0 ? maxRight : root.key;
    } else if (root.right == null) {
      maxLeft = getMaximum(root.left);
      return maxLeft.compareTo(root.key) > 0 ? maxLeft : root.key;
    } else {
      K max = root.key;
      maxLeft = getMaximum(root.left);
      maxRight = getMaximum(root.right);
      if (maxLeft.compareTo(max) > 0) max = maxLeft;
      if (maxRight.compareTo(max) > 0) max = maxRight;
      return max;
    }
  }

  /**
   * Returns the minimum in this binary tree
   * 
   * @return key
   */
  public K getMinimum() {
    if (root == null) throw new NullPointerException();
    return getMinimum(root);
  }

  private K getMinimum(Node root) {
    if (root == null) return null;
    if (root.left == null && root.right == null) return root.key;
    K minLeft = null;
    K minRight = null;
    if (root.left == null) {
      minRight = getMinimum(root.right);
      return minRight.compareTo(root.key) < 0 ? minRight : root.key;
    } else if (root.right == null) {
      minLeft = getMinimum(root.left);
      return minLeft.compareTo(root.key) < 0 ? minLeft : root.key;
    } else {
      K max = root.key;
      minLeft = getMinimum(root.left);
      minRight = getMinimum(root.right);
      if (minLeft.compareTo(max) < 0) max = minLeft;
      if (minRight.compareTo(max) < 0) max = minRight;
      return max;
    }
  }

  /**
   * Inorder traversal of the tree. API to prints the node of the tree in order
   */
  public void printTree() {
    printTree(root);
  }

  private void printTree(Node r) {
    if (r == null) return;
    printTree(r.left);
    System.out.print(r.key + " ");
    printTree(r.right);
  }

  /**
   * Check if this binary tree is identical to a given binary x
   * 
   * @param x
   * @return true if this is identical to x else false
   */
  public boolean isIdentical(BinaryTree<K, V> x) {
    return isIdentical(x.getRootNode(), this.root);
  }

  private boolean isIdentical(Node x, Node y) {
    if (x == null && y == null) return true;
    else if (x == null && y != null) return false;
    else if (x != null && y == null) return false;
    else return isIdentical(x.left, y.left) && (x.key.compareTo(y.key) == 0)
        && isIdentical(x.right, y.right);
  }

  /**
   * Calculates and returns the size of binary tree.
   * 
   * @return
   */
  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    else if (x.left == null && x.right == null) return 1;
    else return 1 + size(x.left) + size(x.right);
  }

  /**
   * Create mirrot image of this binary tree Need to understand about it. If the
   * this object is BST then it should return BST rather than BinaryTree
   * 
   * @return
   */

  public BinaryTree<K, V> mirror() {
    return new BinaryTree<K, V>(mirror(this.root));
  }

  private Node mirror(Node x) {
    if (x == null) return null;
    else {
      Node lm = mirror(x.left);
      Node rm = mirror(x.right);
      x.left = rm;
      x.right = lm;
      return x;
    }
  }

  /**
   * Height of the tree
   * 
   * @param x
   * @return
   */
  public int height(Node x) {
    if (x == null) return 0;
    else if (x.left == null && x.right == null) return 1;
    else {
      int lh = height(x.left);
      int rh = height(x.right);
      return (lh > rh) ? lh + 1 : rh + 1;
    }
  }

  @SuppressWarnings("unused")
  private Node getNode(Node x, K key) {
    Node res = null;
    if (x == null) return null;
    if (key.compareTo(x.key) == 0) return x;
    res = getNode(x.left, key);
    if (res == null) res = getNode(x.right, key);
    return res;

  }

  /**
   * Here I have made a compromize by letting that keys would always be
   * integers. When we pass arrayList then only once copy of the object remains
   * for all the recursive function calls. Have a look for other possibility in
   * the next iteration of practice.
   */
  public void rootToLeafPaths() {
    int[] paths = new int[height(root) + 1];
    rootToLeafPaths(root, paths, 0);
  }

  private void rootToLeafPaths(Node x, int[] paths, int i) {
    if (x != null) {
      // I know that my test BT or BST will have int keys
      paths[i++] = (int) x.key;
      rootToLeafPaths(x.left, paths, i);
      rootToLeafPaths(x.right, paths, i);
      if (x.left == null && x.right == null)
        System.out.println(Arrays.toString(paths));
    }
  }

  /**
   * The is the implementation of BFS on a tree. It uses a queue to explore the
   * nodes on the tree. It explores all node at distance 1 before discovering
   * any Node at distance 2 and so on.
   */
  public void levelOrderTraversal() {
    Deque<Node> queue = new ArrayDeque<>(10);
    queue.add(root);
    while (!queue.isEmpty()) {
      int count = queue.size();
      while (count-- > 0) {
        Node curr = queue.remove();
        System.out.print(curr.key + " ");
        if (curr.left != null) queue.add(curr.left);
        if (curr.right != null) queue.add(curr.right);
      }
      System.out.println();
    }
  }

  public void printPathSums(Node x, int sum) {
    if (x == null) return;
    sum = sum + (int) x.key;
    if (x.left == null && x.right == null) {
      System.out.println(sum);
    }
    printPathSums(x.left, sum);
    printPathSums(x.right, sum);
  }

  private int max = Integer.MIN_VALUE;

  public void leftView(Node x, int level) {
    if (x == null) return;
    if (max < level) {
      System.out.println(x.key);
      max = level;
    }
    leftView(x.left, level + 1);
    leftView(x.right, level + 1);
  }

  public void rightView(Node x, int level) {
    if (x == null) return;
    if (max < level) {
      System.out.println(x.key);
      max = level;
    }
    rightView(x.right, level + 1);
    rightView(x.left, level + 1);
  }

  /**
   * Print all left boundary nodes ignoring the leaf nodes from top to bottom
   * 
   * @param x
   */
  public void printLeftBoundary(Node x) {
    if (x == null) return;
    if (x.left != null) {
      System.out.println(x.key);
      printLeftBoundary(x.left);
    } else if (x.right != null) {
      System.out.println(x.key);
      printLeftBoundary(x.right);
    }
  }

  /**
   * Print all right boundary nodes ignoring the leaf nodes from bottom to up
   * 
   * @param x
   */
  public void printRightBoundary(Node x) {
    if (x == null) return;
    if (x.right != null) {
      System.out.println(x.key);
      printRightBoundary(x.right);
    } else if (x.left != null) {
      System.out.println(x.key);
      printRightBoundary(x.left);
    }
  }

  public void printLeaves(Node x) {
    if (x == null) return;
    printLeaves(x.left);
    if (x.left == null && x.right == null) {
      System.out.println(x.key);
      return;
    }
    printLeaves(x.right);
  }

  public void printAllBoundary(Node x) {
    if (x == null) return;
    // Print the root node first to avoid duplication
    System.out.println(x.key);
    printLeftBoundary(x.left);
    System.out.println();
    printLeaves(x);
    System.out.println();
    printRightBoundary(x.right);
    System.out.println();
  }

  /**
   * Print all nodes at distance k from a given node. Given a binary tree, a
   * target node in the binary tree, and an integer value k, print all the nodes
   * that are at distance k from the given target node. No parent pointers are
   * available.
   * 
   * @param x root of the tree
   * @param y given node
   */
  public int findKDistanceNodes(Node x, Node y, int K) {

    if (x == null || y == null) return -1;
    if (x == y) {
      findNodeAtDistanceKFrom(y, K);
      return 0;
    }
    int dLeft = findKDistanceNodes(x.left, y, K);
    if (dLeft != -1) {
      if (dLeft + 1 == K) System.out.println(x.key);
      else findNodeAtDistanceKFrom(x.right, K - (dLeft + 2));
      return dLeft + 1;
    }
    int dRight = findKDistanceNodes(x.right, y, K);
    if (dRight != -1) {
      if (dRight + 1 == K) System.out.println(x.key);
      else findNodeAtDistanceKFrom(x.left, K - (dRight + 2));
      return dRight + 1;
    }
    return -1;
  }

  public void findNodeAtDistanceKFrom(Node y, int K) {
    if (y == null) return;
    if (K == 0) System.out.println(y.key);
    else {
      findNodeAtDistanceKFrom(y.left, K - 1);
      findNodeAtDistanceKFrom(y.right, K - 1);
    }
  }

  public int findDistanceFromRoot(Node x, Node y) {
    if (x == null || y == null) return -1;
    if (x == y) return -1;
    else {
      int dLeft = findDistanceFromRoot(x.left, y);
      int dRight = findDistanceFromRoot(x.right, y);
      return dLeft > 0 ? dLeft : dRight;
    }
  }

  /**
   * Given a Binary Tree and a positive integer k, print all nodes that are
   * distance k from a leaf node.
   * 
   * Here the meaning of distance is different from previous post. Here k
   * distance from a leaf means k levels higher than a leaf node. For example if
   * k is more than height of Binary Tree, then nothing should be printed.
   * Expected time complexity is O(n) where n is the number nodes in the given
   * Binary Tree.
   * 
   * Note that this also prints ..This has flaws in it
   * 
   * @param x
   * @param K
   * @return
   */
  public int findNodesAtDistanceKFromLeaf(Node x, int K) {
    if (x == null) return -1;
    if (x.left == null && x.right == null) return 0;
    int dLeft = findNodesAtDistanceKFromLeaf(x.left, K);
    int dRight = findNodesAtDistanceKFromLeaf(x.right, K);
    if (dLeft != -1) {
      if (dLeft + 1 == K) System.out.println(x.key);
      else findNodeAtDistanceKFrom(x.right, K - (dLeft + 2));
      return dLeft + 1;
    }
    // int dRight = findNodesAtDistanceKFromLeaf(x.right, K);
    if (dRight != -1) {
      if (dRight + 1 == K) System.out.println(x.key);
      else findNodeAtDistanceKFrom(x.left, K - (dRight + 2));
      return dRight + 1;
    }
    return -1;
  }

  /**
   * Find the LCA in a binary tree using single tree travesal. If root of a
   * subtree is equal to any of the nodes a or b then root is the LCA of node a
   * and b in subtree rooted at root. We return the non-null value when the
   * above condition is met. This symbolizes that one the node is found in left
   * subtree or right subtree. If the nodes a and b are in left and right
   * subtrees root at x repectively or vice-versa then x is LCA of node a and b.
   * 
   * @param x
   * @param a
   * @param b
   * @return
   */
  public Node binaryTreeLCA(Node x, Node a, Node b) {
    if (x == null) return null;
    if (x.key.compareTo(a.key) == 0) { return x; }
    Node left = binaryTreeLCA(x.left, a, b);
    Node right = binaryTreeLCA(x.right, a, b);
    if (left != null && right != null) return x;
    return left != null ? left : right;
  }

  /**
   * Finds out the number of leaves node in the tree and returns
   * 
   * @return No. of leaves node in the tree.
   */
  public int leafSize() {
    return leafSize(root);
  }

  private int leafSize(Node x) {
    if (x == null) return 0;
    if (x.left == null && x.right == null) return 1;
    else return leafSize(x.left) + leafSize(x.right);
  }

  static int preIndex = 0;

  /**
   * Creates a binary tree from inorder and preorder traversal arrays
   * 
   * @param inorder Array containing in-order traversals
   * @param li low index of inorder
   * @param hi high index of inorder
   * @param inodervalues values of the keys
   * @param preorder Array containing preorder traversal
   * @return returns the node of the tree created
   */
  public Node createTree(K[] inorder, int li, int hi, V[] inodervalues,
      K[] preorder) {
    if (li < hi) {
      Node n = new Node(preorder[preIndex++], inodervalues[0], null);
      int i = search(n.key, inorder, li, hi);
      n.left = createTree(inorder, li, i - 1, inodervalues, preorder);
      n.right = createTree(inorder, i + 1, hi, inodervalues, preorder);
      return n;
    } else if (li == hi) return new Node(preorder[preIndex++], inodervalues[0],
        null);
    else return null;
  }

  private int search(K key, K[] inorder, int low, int high) {
    int i = low;
    for (i = low; i <= high; i++)
      if (key.compareTo(inorder[i]) == 0) return i;
    return -1;
  }

  /**
   * Given a binary tree and a number, return true if the tree has a
   * root-to-leaf path such that adding up all the values along the path equals
   * the given number. Return false if no such path can be found.<br>
   * Note: Use this function if you know that keys are integers and nothing
   * else. We can't use instanceOf check with generics
   * 
   * @return true if the sum exits, false otherwise
   */
  public boolean isSumExits(int sum) {
    if (root != null && !(root.key instanceof Integer))
      throw new UnsupportedOperationException("Keys are not integers");
    return isSumExits(root, sum);
  }

  private boolean isSumExits(Node x, int sum) {
    if (x == null) return false;
    if (x.left == null && x.right == null && sum - (int) x.key == 0)
      return true;
    return isSumExits(x.left, sum - (int) x.key)
        || isSumExits(x.right, sum - (int) x.key);
  }

  /**
   * A tree is height-balanced if the heights of the left and right subtree's of
   * each node are within 1.<br>
   * Note: Empty or null trees are height balance. This is one of the base cases
   * we will use.
   * 
   * @return true if height balanced, otherwise false
   */
  public boolean isHeightBalanced() {
    return isHeightBalanced(root);
  }

  private boolean isHeightBalanced(Node x) {
    if (x == null) return true;
    int leftHeight = height(x.left);
    int rightHeight = height(x.right);
    if (Math.abs(leftHeight - rightHeight) <= 1 && isHeightBalanced(x.left)
        && isHeightBalanced(x.right)) return true;
    else return false;
  }

  /**
   * The diameter of a tree (sometimes called the width) is the number of nodes
   * on the longest path between two leaves in the tree.<br>
   * <b> A variant of the problem would be find out two end points the diameter.<br>
   * An optimization could also be done by finding the heights in the same
   * recursion. I will try this in next revision.</b>
   * 
   * @return the diameter of the tree.
   */
  public int diameter() {
    return diameter(root);
  }

  private int diameter(Node x) {
    if (x == null) return 0;
    int ld = diameter(x.left);
    int rd = diameter(x.right);
    int lsh = height(x.left);
    int rsh = height(x.right);
    return max(lsh + rsh + 1, max(ld, rd));
  }

  public int max(int a, int b) {
    return a > b ? a : b;
  }

  /**
   * This is a variant of level order tree traversal. Nodes at even level should
   * be printed in right to left order and the nodes at odd level should be
   * printed in left to right order. This is just a convention. we are free to
   * choose different opposite order for even and odd levels.
   */
  public void levelOrderSpiral() {
    if (root == null) throw new NullPointerException();
    Deque<Node> lstack = new ArrayDeque<>();
    Deque<Node> rstack = new ArrayDeque<>();
    lstack.push(root);
    boolean evenLevel = true;
    while (!lstack.isEmpty() || !rstack.isEmpty()) {
      if (evenLevel) {
        while (!lstack.isEmpty()) {
          Node curr = lstack.pop();
          System.out.print(curr.key + " ");
          if (curr.right != null) rstack.push(curr.right);
          if (curr.left != null) rstack.push(curr.left);
        }
      } else {
        while (!rstack.isEmpty()) {
          Node curr = rstack.pop();
          System.out.print(curr.key + " ");
          if (curr.left != null) lstack.push(curr.left);
          if (curr.right != null) lstack.push(curr.right);
        }
      }
      evenLevel = !evenLevel;
      System.out.println();
    }
  }

  /**
   * This method prints nodes of a particular level. This is same as printing
   * nodes at distance k(GeekForGeeks)
   * 
   * @param i the level of which nodes to be printed
   */
  public void printLevel(int i) {
    List<K> levelKeys = new ArrayList<>();
    getKeysAtLevel(root, i, levelKeys);
    if (levelKeys.size() == 0)
      System.out.println("Oops! no nodes at this level");
    for (K key : levelKeys)
      System.out.print(key + " ");

    System.out.println();
  }

  /**
   * Collects the keys of a particular level in the tree in the "keysHolder"
   * list.
   * 
   * @param x Pointer to the root node of the tree
   * @param level
   * @param keysHolder An empty list of keys.
   * 
   */
  public void getKeysAtLevel(Node x, int level, List<K> keysHolder) {
    if (x == null) return;
    if (level == 0) keysHolder.add(x.key);
    else {
      getKeysAtLevel(x.left, level - 1, keysHolder);
      getKeysAtLevel(x.right, level - 1, keysHolder);
    }
  }

  /**
   * For every node, data value must be equal to sum of data values in left and
   * right children. Consider data value as 0 for NULL children.
   * 
   * @return true if above property holds, false otherwise
   */
  public boolean hasSumProperty() {
    return hasSumProperty(root);
  }

  private boolean hasSumProperty(Node x) {
    if (x == null || x.left == null && x.right == null) return true;
    int lSum = 0, rSum = 0;
    if (x.left != null) lSum = (int) x.left.key;
    if (x.right != null) rSum = (int) x.right.key;
    return hasSumProperty(x.left) && hasSumProperty(x.right)
        && (int) x.key == lSum + rSum;
  }

  /**
   * A SumTree is a Binary Tree where the value of a node is equal to sum of the
   * nodes present in its left subtree and right subtree. An empty tree is
   * SumTree and sum of an empty tree can be considered as 0. A leaf node is
   * also considered as SumTree.
   * 
   * @return true if this is a sum tree otherwise false
   */
  public boolean isSumTree() {
    if (root != null && !(root.key instanceof Integer))
      throw new UnsupportedOperationException("keys must be Integers");
    return isSumTree(root);
  }

  private boolean isSumTree(Node x) {
    if (x == null || x.left == null && x.right == null) return true;
    int lSum = sum(x.left);
    int rSum = sum(x.right);
    return lSum + rSum == (int) x.key && isSumTree(x.left)
        && isSumTree(x.right);
  }

  /**
   * Gets the sum of a binary of keys of binary tree root at x. For this to work
   * correctly the keys should be integers
   * 
   * @param x
   * @return the sum of a binary tree root at x
   */
  public int sum(Node x) {
    if (x != null && !(x.key instanceof Integer)) throw new UnsupportedOperationException(
        "keys must be Integers");
    else return sumkeys(x);
  }

  private int sumkeys(Node x) {
    if (x == null) return 0;
    return (int) x.key + sum(x.left) + sum(x.right);
  }

  /**
   * This method explore the tree for the given key and then returns all the
   * ancestors of it. Currently we print the ancestors and return a list of it.
   * 
   * @param key
   * @return List<K> containing the ancestors
   */
  public List<K> getAncestors(K key) {
    List<K> ancestors = new ArrayList<>();
    getAncestors(root, key, ancestors);
    if (ancestors.isEmpty()) {
      System.out.println("No ancestors of this key");
      return null;
    }
    for (K k : ancestors)
      System.out.println(k.toString());

    return ancestors;
  }

  /* This function returns boolean true when it finds the ancestor of the given
   * key. This way when we find the key we can use that information to trace
   * back the path upto the root node. */
  private boolean getAncestors(Node x, K key, List<K> ancestors) {
    if (x == null) return false;
    if (key.compareTo(x.key) == 0) return true;
    if (getAncestors(x.left, key, ancestors)
        || getAncestors(x.right, key, ancestors)) {
      ancestors.add(x.key);
      return true;
    }
    return false;
  }

  /**
   * Given a key it searches for the in the tree returns it level. In the
   * recursive implementation -1 is returned if key is not found in a path from
   * root to leaf. Once we find any positive level we propogate it upward along
   * the path from that node to the root. -1 is just ignored since that can't be
   * level
   * 
   * @param key
   * @return level of key
   */
  public int getLevel(K key) {
    return getLevel(root, key, 0);
  }

  private int getLevel(Node x, K key, int level) {
    if (x == null) return -1;
    if (key.compareTo(x.key) == 0) return level;
    int inLeft = getLevel(x.left, key, level + 1);
    if (inLeft != -1) return inLeft;
    int inRight = getLevel(x.right, key, level + 1);
    if (inRight != -1) return inRight;
    return -1;
  }

  /**
   * A flodable binary tree is one in which every node's left child is left
   * child and other is right child or vice-versa.<br>
   * From geeksforgeeks a A tree can be folded if left and right subtrees of the
   * tree are structure wise mirror image of each other. An empty tree is
   * considered as foldable.
   * 
   * The algorithm checks whether both the child of a node are null then return
   * true and recursively check for subtrees or if one of the child is non-null
   * then check whether its mirror image child is non-null, if this is not the
   * case then return false.
   * 
   * @return true if the binary tree is foldable otherwise false.
   */
  public boolean isFoldable() {
    return isFoldable(root);
  }

  private boolean isFoldable(Node x) {
    if (x == null) return true;
    return isMirror(x.left, x.right);
  }

  private boolean isMirror(Node x, Node y) {
    if (x == null && y == null) return true;
    if ((x != null && y == null) || (x == null && y != null)) return false;
    return isMirror(x.left, y.right) && isMirror(x.right, y.left);
  }

  /**
   * Width of a level is the total number of nodes on that level. In
   * geeksforgeeks the problem was asked for maximum width which could be found
   * using this method by calling it for every level and taking the maximum of
   * the returned values.<br>
   * <b>Note we use the convention that root is at level 0;</b>
   * 
   * @param level
   * @return
   */
  public int width(int level) {
    return width(root, level);
  }

  private int width(Node x, int level) {
    if (x == null) return 0;
    if (level == 0) return 1;
    return width(x.left, level - 1) + width(x.right, level - 1);
  }

  public int maxWidth() {
    int h = height(root);
    int maxWidth = -1;
    for (int i = 0; i < h; i++) {
      int w = width(root, i);
      if (maxWidth < w) maxWidth = w;
    }
    return maxWidth;
  }

  public void verticalOrderTraversal() {
    MinMax minMax = new MinMax(0, 0);
    getMinMaxHD(minMax, 0);
    List<K> outKeys = new ArrayList<>();
    for (int i = minMax.min; i <= minMax.max; i++) {
      verticalOrderTraversal(i, outKeys, 0);
      System.out.println(outKeys.toString());
      outKeys.clear();
    }
  }

  /**
   * We store the nodes on the same line and return the list of all such keys.
   * This method is currently private
   * 
   * @param hd The vertical line number in terms of horizonatl distance
   * @param outKeys An empty list of keys to hold the result. This is required
   *        since this is a recursive fucntion
   * @param nodeHd
   * @return List<K> of the keys in the line hd
   */

  public List<K> verticalOrderTraversal(int hd, List<K> outKeys, int nodeHd) {
    outKeys.clear();
    return verticalOrderTraversal(root, hd, outKeys, 0);
  }

  private List<K> verticalOrderTraversal(Node x, int hd, List<K> outKeys,
      int nodeHd) {
    if (x == null) return null;
    if (nodeHd == hd) outKeys.add(x.key);
    verticalOrderTraversal(x.left, hd, outKeys, nodeHd - 1);
    verticalOrderTraversal(x.right, hd, outKeys, nodeHd + 1);
    return outKeys;
  }

  /**
   * This method finds out the minimum and maximum <b>Horizontal distance in the
   * tree.</b> Horizontal distance is computed as follows: hd(root) = 0 by
   * convention and for any node x in the tree hd of left and right child is
   * defined as<br>
   * hd(x.left) = hd(x) - 1;<br>
   * hd(x.right) = hd(x) + 1;<br>
   * 
   * @param minMaxPair An object which is the holder for min and max hd. The
   *        values be contained in this and can be accessed by the user once the
   *        method returns
   * @param hd Horizontal distance of the root of the tree;
   */
  public void getMinMaxHD(MinMax minMaxPair, int hd) {
    getMinMaxHD(root, minMaxPair, hd);
    System.out.println(minMaxPair.min + " " + minMaxPair.max);
  }

  private void getMinMaxHD(Node x, MinMax minMax, int hd) {
    if (x == null) return;
    if (hd < minMax.min) minMax.min = hd;
    if (hd > minMax.max) minMax.max = hd;
    getMinMaxHD(x.left, minMax, hd - 1);
    getMinMaxHD(x.right, minMax, hd + 1);
  }

  /**
   * For developing a recursive solution we must on abstract level and believe
   * that the fucntion is going to return true. This is called recursive leap of
   * faith. Identifying base cases and writing the recurives cases are key
   * steps;
   */
  public void convertToSumTree() {
    if (root != null && !(root.key instanceof Integer))
      throw new UnsupportedOperationException("Keys should be integers");
    convertToSumTree(root);
  }

  @SuppressWarnings("unchecked")
  private int convertToSumTree(Node x) {
    if (x == null) return 0;
    int prevData = (int) x.key;
    if (x.left == null && x.right == null) {
      this.updateKey(x, ((K) new Integer(0)));
      return prevData;
    }
    int newData = convertToSumTree(x.right) + convertToSumTree(x.left);
    this.updateKey(x, (K) new Integer(newData));
    return newData + prevData;
  }

  /**
   * Given a Binary Tree where each node has following structure, write a
   * function to populate next pointer for all nodes. The next pointer for every
   * node should be set to point to inorder successor.
   */
  public void connectInorderSucc() {
    connectInorderSucc(root);
  }

  private Node inSucc = null;

  public void connectInorderSucc(Node x) {
    if (x == null) return;
    connectInorderSucc(x.right);
    x.next = inSucc;
    inSucc = x;
    connectInorderSucc(x.left);
  }

  public void printUsingNext() {
    Node firtIn = getNode(root, getMinimum());
    printUsingNext(firtIn);

  }

  private void printUsingNext(Node x) {
    if (x == null) return;
    System.out.println(x.key);
    printUsingNext(x.next);

  }

  /**
   * Note that this doesn't work binaries tree which are not complete.
   */
  public void connectNodesAtSameLevel() {
    root.nextRight = null;
    connectNodesAtSameLevel(root);

  }

  private void connectNodesAtSameLevel(Node x) {
    if (x == null) return;
    if (x.left != null) x.left.nextRight = x.right;
    if (x.right != null)
      x.right.nextRight = (x.nextRight != null) ? x.nextRight.left : null;
    connectNodesAtSameLevel(x.left);
    connectNodesAtSameLevel(x.right);
    return;
  }

  public boolean isIdenticalTrees(BinaryTree<K, V> tree) {
    return isIdenticalTrees(root, tree.root);
  }

  private boolean isIdenticalTrees(Node x, Node y) {
    if (x == null && y == null) return true;
    if (x == null || y == null) return false;
    if (x.key.compareTo(y.key) == 0 && isIdentical(x.left, y.left)
        && isIdentical(x.right, y.right)) return true;
    else return false;
  }

  /**
   * This tree is the bigger one and explicit parameter is the tree which we
   * have to find in "this" tree.<br>
   * Note that the assumption is that there are no duplicate keys in the tree.
   * 
   * @param tree
   * @return
   */
  public boolean isSubtree(BinaryTree<K, V> tree) {
    Node x = getNode(root, tree.root.key);
    return (isIdentical(x, root));
  }

  public void iterPreOrder() {
    if (root == null) return;
    Deque<Node> stack = new ArrayDeque<>();
    System.out.println(root.key);
    stack.push(root);
    Node temp = root;
    while (true) {

      while (temp.left != null) {
        System.out.println(temp.left.key);
        stack.push(temp.left);
        temp = temp.left;
      }
      Node p = null;
      while (!stack.isEmpty()) {
        p = stack.pop();
        if (p.right != null) break;
      }
      if (stack.isEmpty()) return;

      System.out.println(p.right.key);
      stack.push(p.right);
      temp = p.right;
    }
  }

  /**
   * A node will be in bottom view if it is the last last node in its horizontal
   * distance.
   */
  public void bottomView() {
    Map<Integer, Node> map = new HashMap<>();
    bottomView(root, map, 0, 0);
    for (Map.Entry<Integer, Node> entries : map.entrySet()) {
      System.out.println(entries.getValue().key);
    }
  }

  // Node data structure is augumented with level of this node.
  private void bottomView(Node x, Map<Integer, Node> map, int level, int hd) {
    if (x == null) return;
    if (map.containsKey(hd)) {
      Node temp = map.get(hd);
      if (x.level > temp.level) map.put(hd, x);
    } else map.put(hd, x);

    bottomView(x.left, map, level + 1, hd - 1);
    bottomView(x.right, map, level + 1, hd + 1);
  }

  public void topView() {
    Map<Integer, Node> map = new HashMap<>();
    topView(root, map, 0, 0);
    for (Map.Entry<Integer, Node> entries : map.entrySet()) {
      System.out.println(entries.getValue().key);
    }
  }

  // Node data structure is augumented with level of this node.
  private void topView(Node x, Map<Integer, Node> map, int level, int hd) {
    if (x == null) return;
    if (map.containsKey(hd)) {
      Node temp = map.get(hd);
      if (x.level < temp.level) map.put(hd, x);
    } else map.put(hd, x);
    topView(x.left, map, level + 1, hd - 1);
    topView(x.right, map, level + 1, hd + 1);
  }

  /**
   * Currently this only gives the expected result in case of perfect binary
   * tree. We will try to extend the solution to tree in general.
   * <href>http://www
   * .geeksforgeeks.org/perfect-binary-tree-specific-level-order-traversal/
   */
  public void specificLevelOrder() {
    specificLevelOrder(root);
  }

  private void specificLevelOrder(Node x) {
    if (x == null) return;
    Deque<Node> queue = new ArrayDeque<>();
    System.out.print(x.key + " ");
    if (x.left != null) queue.add(x.left);
    if (x.right != null) queue.add(x.right);

    while (!queue.isEmpty()) {
      Node curr = queue.remove();
      Node next = null;
      if (!queue.isEmpty()) next = queue.remove();
      System.out.print(curr.key + " ");
      System.out.print(next.key + " ");
      if (curr.left != null) queue.add(curr.left);
      if (next != null && next.right != null) queue.add(next.right);
      if (curr.right != null) queue.add(curr.right);
      if (next != null && next.left != null) queue.add(next.left);
    }
  }

  public void removeAllHalfNodes() {
    root = removeAllHalfNodes(root);
  }

  private Node removeAllHalfNodes(Node x) {
    if (x == null) return null;
    x.left = removeAllHalfNodes(x.left);
    x.right = removeAllHalfNodes(x.right);

    // Check if this is a half node
    if (x.left == null && x.right != null) return x.right;
    if (x.right == null && x.left != null) return x.left;
    return x;
  }

  // Level order processing to find is the BT is complete
  public boolean isComplete() {
    Node x = root;
    if (x == null) return true;

    // Indicates if we have seen a non-full node.
    boolean flag = false;
    Deque<Node> queue = new ArrayDeque<>();
    queue.add(x);
    while (!queue.isEmpty()) {
      Node curr = queue.remove();

      // If non-full node has been seen then all next nodes should be leaf.
      if (flag && (curr.left != null || curr.right != null)) return false;

      // If we see non-full node then indicate using flag.
      if (curr.left == null || curr.right == null) flag = true;

      // If left child of current node is empty but not the right child
      if (flag && curr.right != null) return false;

      // Enqueue children for next level processing
      if (curr.left != null) queue.add(curr.left);
      if (curr.right != null) queue.add(curr.right);
    }
    return true;
  }

  private Node succ = null;

  public void createThreadedBT() {
    createThreadedBT(root);
  }

  /* The is implemented using reverse inorder traversal. In this when function
   * call starts returning then we can maintain the successors easily to be used
   * in next function return. */
  private void createThreadedBT(Node x) {
    if (x == null) return;
    if (x.right != null) createThreadedBT(x.right);
    else x.right = succ; // If a null right node then point it to its successor
    succ = x; // update the successor
    if (x.left != null) createThreadedBT(x.left);
  }

  /* public void printThreadBT(Node x){ if(x == null) return; boolean[] visited
   * = new boolean[] } */

  public int sumOfLeftLeaves() {
    return sumOfLeftLeaves(root, false);
  }

  // We keep track of whether a node in the recursive function call is left node
  // of its parent or right node
  private int sumOfLeftLeaves(Node x, boolean left) {
    if (x == null) return 0;
    if (x.left == null && x.right == null) return left ? (int) x.key : 0;
    return sumOfLeftLeaves(x.left, true) + sumOfLeftLeaves(x.right, false);
  }
  
  private void deleteTree(Node root){
      if(root == null)
          return;
      
      else {
          Object aObject = 1;
          deleteTree(root.left);
          deleteTree(root.right);
          root.left = null;
          root.right = null;
          root = null;
      }
  }

  public static void main(String[] args) {
    BinaryTree<Integer, Integer> bt = new BinaryTree<>();
    BinaryTree<Integer, Integer>.Node root = bt.insert(null, 20, 0, true);
    BinaryTree<Integer, Integer>.Node B = bt.insert(root, 8, 1, true);
    BinaryTree<Integer, Integer>.Node C = bt.insert(root, 22, 2, false);
    BinaryTree<Integer, Integer>.Node D = bt.insert(B, 5, 3, true);
    BinaryTree<Integer, Integer>.Node E = bt.insert(B, 3, 4, false);
    BinaryTree<Integer, Integer>.Node F = bt.insert(C, 25, 5, false);
    BinaryTree<Integer, Integer>.Node t = bt.insert(C, 45, 5, true);
    BinaryTree<Integer, Integer>.Node y = bt.insert(t, 19, 5, true);
    BinaryTree<Integer, Integer>.Node G = bt.insert(E, 100, 5, true);
    BinaryTree<Integer, Integer>.Node H = bt.insert(E, 14, 5, false);
    BinaryTree<Integer, Integer>.Node I = bt.insert(H, 16, 5, false);
    bt.deleteTree(root);
    bt.printLevel(1);

    // BinaryTree<Integer, Integer>.Node p = bt.insert(D, 11, 3, true);
    // BinaryTree<Integer, Integer>.Node q = bt.insert(D, 13, 3, false);
  }
}

class MinMax {
  protected int min;
  protected int max;

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }

  public MinMax(int a, int b) {
    min = a;
    max = b;
  }
}
