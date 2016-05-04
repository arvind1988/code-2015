package algo.linkedlist;

public class SingleLinkedList<K> {

  transient Node<K> first;

  private int       size;

  private static class Node<K> {
    K       key;
    Node<K> next;

    Node(K key, Node<K> next) {
      this.key = key;
      this.next = next;
    }
  }
  
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
