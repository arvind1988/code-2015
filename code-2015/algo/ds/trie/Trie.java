package algo.ds.trie;

import java.util.ArrayDeque;
import java.util.Deque;

public class Trie {
  private static final int R = 256;

  // Node in trie
  private static class Node {
    private Node[] next = new Node[R];
    private int    value;
  }

  // Root node of the trie
  private Node root = new Node();

  // Iterative insertion of a key in trie
  public Node insert(String key, int value) {
    int N = key.length();
    Node x = root;
    for (int i = 0; i < N; i++) {
      char c = key.charAt(i);
      // create a new node and point to by x.next[c]
      if (x.next[c] == null) x.next[c] = new Node();
      x = x.next[c];
    }
    x.value = value;
    return x;
  }

  // Recursive insert in the trie
  public Node recursiveInsert(String key, int value) {
    return insert(root, key, value, 0);
  }

  private Node insert(Node x, String key, int value, int d) {
    if (x == null) x = new Node();
    if (d == key.length()) {
      x.value = value;
      return x;
    }
    char c = key.charAt(d);
    x.next[c] = insert(x.next[c], key, value, d + 1);
    return x;
  }

  public Node delete(String key) {
    return delete(root, key, 0);
  }

  private Node delete(Node x, String key, int d) {
    if (x == null) return null;
    // Find the node to be deleted
    if (d == key.length()) x.value = 0;
    else {
      char c = key.charAt(d);
      x.next[c] = delete(x.next[c], key, d + 1);
    }
    if (x.value > 0) return x;
    for (int i = 0; i < R; i++)
      if (x.next[i] != null) return x;
    return null;
  }

  // Search for a key in trie
  public boolean contains(String key) {
    return get(key) != -1;
  }

  public int get(String key) {
    Node x = get(root, key, 0);
    if (x != null && x.value != 0) return x.value;
    else return -1;
  }

  private Node get(Node x, String key, int d) {
    if (x == null) return null;
    if (d == key.length()) return x;
    char c = key.charAt(d);
    return get(x.next[c], key, d + 1);
  }

  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    // Size of the trie is the sum of the sizes of all the subtries
    int count = 0;
    if (x.value > 0) count = 1;
    for (char c = 0; c < R; c++)
      count += size(x.next[c]);
    return count;

  }
  
  public Iterable<String> keys(){
    return keysWithPrefix("");
  }
  
  public Iterable<String> keysWithPrefix(String pre){
    Deque<String> q = new ArrayDeque<>();
    collect(get(root, pre, 0), pre, q);
    return q;
  }
  
  private void collect(Node x, String pre, Deque<String> q){
    if(x == null) return;
    if(x.value > 0) q.add(pre);
    for(char c = 0; c < R; c++){
      collect(x.next[c], pre + c, q);
    }
  }

  /**
   * Implementation to be done
   * @param s
   * @return
   */
  public String longestPrefixOf(String s)
  {
    return null;
  }
  
  /**
   * Implemetation needed
   * @param pat
   * @return
   */
  public Iterable<String> keysThatMatch(String pat)
  {
    return null;
  }
  public static void main(String[] args) {

    Trie trie = new Trie();
    trie.recursiveInsert("sea", 5);
    trie.recursiveInsert("she", 7);
    trie.recursiveInsert("sell", 1);
    trie.recursiveInsert("shells", 2);
    trie.recursiveInsert("by", 3);
    trie.recursiveInsert("the", 4);
    trie.recursiveInsert("shore", 6);
    System.out.println(trie.size());
    Iterable<String> itr = trie.keysWithPrefix("sh");
    for(String s : itr){
      System.out.println(s);
    }
    //trie.delete("shells");
    //System.out.println(trie.contains("she"));
  }

}
