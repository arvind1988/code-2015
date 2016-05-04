package algo.ds.trie;

public class TernaryTrie {

  private static class Node {
    private int  value;
    private char c;
    private Node left;
    private Node mid;
    private Node right;

    public Node(char c) {
      this.c = c;
    }
  }

  private Node root = null;

  public void insert(String key, int value) {
    root = insert(root, key, value, 0);
  }

  private Node insert(Node x, String key, int value, int d) {
    char c = key.charAt(d);
    // If we get a null link, create a node
    if (x == null) x = new Node(c);
    // c is less than current node, go left without accessing next char in key
    if (x.c > c) x.left = insert(x.left, key, value, d);
    // c is greater than current node, go right with accessing next char in key
    else if (x.c < c) x.right = insert(x.right, key, value, d);
    // if node char is equal to c than go mid and access next char in key.
    // d < key.length() - 1 should be checked since we are accessing charAt(d)
    else if (d < key.length() - 1) x.mid = insert(x.mid, key, value, d + 1);
    // Put the value . Here d == key.length();
    else x.value = value;
    return x;
  }
  
  public int get(String key){
    Node x = get(root, key, 0);
    if(x == null || x.value <= 0) return -1; // key not in the trie
    return x.value;
  }
  
  private Node get(Node x, String key, int d){
    if(x == null) return null;
    char c = key.charAt(d);
    if(c < x.c) return get(x.left, key, d);
    else if(c > x.c) return get(x.right, key, d);
    else if(d < key.length() - 1) return get(x.mid, key, d + 1);
    else return x; // d == key.length() - 1, our search ends here.
  }

  public static void main(String[] args) {
    TernaryTrie trie = new TernaryTrie();
    trie.insert("sea", 5);

  }

}
