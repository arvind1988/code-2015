package algo.ds.binarytree;

/**
 * 
 * Interval tree is BST created using the low endpoint of an interval as key.
 * Each node of the tree consists of low end point 'a', high endpoint 'b' and a
 * max field. The max field of a node x contains the maximum end point value in
 * the subtree root at that x.<br>
 * We need this max field for performing search operations.<br>
 * This class uses a helper class Interval which is a convenient way of
 * representing intervals and performing basic operations on intervals.
 * 
 * @author Arvind_Kumar2
 *
 * @param <K>
 */
public class IntervalTree<K extends Comparable<K>> {
  private Node root;

  private class Node {
    Interval<K> interval;
    K           max;
    Node        left;
    Node        right;

    Node(Interval<K> a) {
      this.interval = a;
    }
  }


  public void insert(Interval<K> a) {
    root = insert(root, a);
  }

  private Node insert(Node x, Interval<K> r) {
    if (x == null) x = new Node(r);
    int cmp = r.a.compareTo(x.interval.a);
    if (cmp < 0) x.left = insert(x.left, r);
    else if (cmp > 0) x.right = insert(x.right, r);
    else{}
    x.max = max(x.max, r.b); // maintain the max info.
    return x;
  }

  private K max(K a, K b) {
    if (a == null) return b;
    if (b == null) return a;
    return a.compareTo(b) > 0 ? a : b;
  }
  
  private void printTree(Node x){
    if(x != null){
      printTree(x.left);
      System.out.println(x.interval.toString() + ":" + x.max);
      printTree(x.right);
    }
  }
  
  public Interval<K> search(Interval<K> in){
    return search(root, in);
  }
  
  private Interval<K> search(Node x, Interval<K> in){
    if(x == null) return null;
    if(in.intersect(x.interval)) return x.interval;
    else if(x.left == null) return search(x.right, in);
    else if(in.a.compareTo(x.left.max) > 0) return search(x.right, in);
    else return search(x.left, in);
  }

  public static void main(String[] args) {
    
    IntervalTree<Integer> intervalTree = new IntervalTree<>();
    intervalTree.insert(new Interval<Integer>(1, 5));
    System.out.println(intervalTree.search(new Interval<Integer>(3, 7)).toString());
    
    intervalTree.insert(new Interval<Integer>(3, 7));
    System.out.println(intervalTree.search(new Interval<Integer>(2, 6)).toString());
    
    intervalTree.insert(new Interval<Integer>(2, 6));
    //System.out.println(Interval<Integer> intervalTree.search(new Interval<Integer>(10, 15)).toString() != null);
    
    
    intervalTree.insert(new Interval<Integer>(10, 15));
    System.out.println(intervalTree.search(new Interval<Integer>(5, 6)).toString());

    intervalTree.insert(new Interval<Integer>(5, 6));
    System.out.println(intervalTree.search(new Interval<Integer>(4, 100)).toString());

    intervalTree.insert(new Interval<Integer>(4, 100));
    
    
    //intervalTree.printTree(intervalTree.root);
    //Interval<Integer> i = intervalTree.search(new Interval<Integer>(15, 18));
    //System.out.println(i.toString());
    
    
  }

}
