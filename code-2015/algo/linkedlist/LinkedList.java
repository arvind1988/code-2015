package algo.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<K extends Comparable<K>> implements Iterable<K> {

  private int       size;
  transient Node<K> first;
  transient Node<K> last;

  public LinkedList(K[] items) {
  }

  public LinkedList() {
  }

  // Add a new node to the front of this list
  private void linkFirst(K key) {
    final Node<K> f = first;
    final Node<K> newNode = new Node<>(key, null, f);
    first = newNode;
    if (f == null) last = newNode;
    else f.prev = newNode;
    size++;
  }

  // Add a new node to the end of this list
  private void linkLast(K key) {
    final Node<K> l = last;
    final Node<K> newNode = new Node<>(key, l, null);
    last = newNode;
    if (l == null) first = newNode;
    else l.next = newNode;
    size++;
  }

  private void linkBefore(K e, Node<K> succ) {
    assert succ != null;
    final Node<K> pred = succ.prev;
    final Node<K> newNode = new Node<>(e, pred, succ);
    succ.prev = newNode;
    if (pred == null) first = newNode;
    else pred.next = newNode;
    size++;
  }

  private K unlink(Node<K> x) {
    assert x != null;

    final Node<K> pred = x.prev;
    final Node<K> succ = x.next;
    if (pred == null) first = succ;
    else pred.next = succ;
    if (succ == null) last = pred;
    else succ.prev = pred;
    // Help GC
    x.prev = null;
    x.next = null;
    K key = x.key;
    x.key = null;
    size--;
    return key;
  }

  public K getFirst() {
    final Node<K> f = first;
    if (f == null) throw new NoSuchElementException();
    return f.key;
  }

  public K getLast() {
    final Node<K> l = last;
    if (l == null) throw new NoSuchElementException();
    return l.key;
  }

  public K removeFirst() {
    final Node<K> f = first;
    if (f == null) throw new NoSuchElementException();
    return unlinkFirst(f);
  }

  public K removeLast() {
    final Node<K> l = last;
    if (l == null) throw new NoSuchElementException();
    return unlinkLast(l);
  }

  public void addFirst(K e) {
    linkFirst(e);
  }

  public void addLast(K e) {
    linkLast(e);
  }

  private K unlinkFirst(Node<K> f) {
    assert f == first && f != null;
    final Node<K> succ = f.next;
    first = succ;
    f.next = null;
    K item = f.key;
    f.key = null;
    if (succ == null) last = null;
    else succ.prev = null;
    size--;
    return item;
  }

  private K unlinkLast(Node<K> l) {
    assert l == last && l != null;
    final Node<K> pred = l.prev;
    last = pred;
    l.prev = null;
    K item = l.key;
    l.key = null;
    if (pred == null) first = null;
    else pred.next = null;
    size--;
    return item;
  }

  public boolean contains(Object e) {
    if (e == null) {
      for (Node<K> x = first; x != null; x = x.next) {
        if (x.key == null) return true;
      }
      return false;
    } else {
      for (Node<K> x = first; x != null; x = x.next) {
        if (e.equals(x.key)) return true;
      }
      return false;
    }
  }

  public K get(int index) {
    checkValidIndex(index);
    return node(index).key;
  }

  // Will travese from the side which is nearer to index
  private Node<K> node(int index) {
    if (index < (size >> 1)) {
      Node<K> x = first;
      for (; index > 0; x = x.next, index--)
        ;
      return x;
    } else {
      Node<K> x = last;
      index = size - index; // Now index represent indexth element
      for (; x != null && index > 1; index--)
        x = x.prev;
      return x;
    }
  }

  public void linkAt(int index, K item) {
    checkValidIndex(index);
    Node<K> ithNode = node(index);
    linkBefore(item, ithNode);
  }

  /**
   * This methods should not reside here since it assumes that the list is in
   * some sorted order.But for the purpose of practising I keep them here for a
   * time being
   * 
   * @param item
   * @param asce
   */
  private LinkedList<K> sortedInsert(K item, LinkedList<K> list) {
    Node<K> x = list.first;
    if (x == null) {
      list.linkFirst(item);
      return list;
    }
    // Find the position at which the new node would be inserted
    while (x != null && item.compareTo(x.key) >= 0) {
      x = x.next;
    }
    // check if we are out of the list, if so insert at last of the linked list
    if (x == null) {
      list.linkLast(item);
      return list;
    }
    // We have to insert before x
    list.linkBefore(item, x);
    return list;
  }

  /**
   * Insertion sort on linked list assuming that the list is singley connected
   * 
   * @return
   */
  public LinkedList<K> insertionSort() {
    Node<K> x = first;
    if (x == null) return null;
    LinkedList<K> list = new LinkedList<>();
    while (x != null) {
      sortedInsert(x.key, list);
      x = x.next;
    }
    x = list.first;
    while (x != null) {
      System.out.println(x.key);
      x = x.next;
    }
    // Post condition
    list.checkListSorted(true);
    return list;
  }

  public void append(LinkedList<K> that) {
    final Node<K> f1 = this.first;
    final Node<K> f2 = that.first;
    final Node<K> l1 = this.last;
    final Node<K> l2 = that.last;
    if (f1 != null && f2 != null) {
      l1.next = f2;
      f2.prev = l1;
      this.last = l2;
      that.first = null;
      that.last = null;
      return;
    }
    if (f1 == null) {
      this.first = f2;
      this.last = that.last;
      return;
    }
  }

  private Node<K> middle() {
    Node<K> slow = first;
    // Empty list
    if (slow == null) return null;
    // List with only one node
    if (slow.next == null) return slow;
    Node<K> fast = first;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  public K getMiddle() {
    return middle().key;
  }

  public void splitAtMiddle(LinkedList<K> left, LinkedList<K> right) {
    Node<K> mid = middle();
    if (mid == null) throw new IllegalArgumentException("Empty list");
    Node<K> f = first;
    left.first = f;
    left.last = mid;
    right.first = mid.next;
    if (mid.next != null) {
      right.first.prev = null;
      mid.next = null;
    }
  }

  // Assumtion the list is sorted in increasing order
  public void deleteDuplicates() {
    checkListSorted(true);
    Node<K> x = first;
    if (x == null) return;
    while (x.next != null) {
      K curr = x.key;
      K next = x.next.key;
      if (curr.equals(next)) {
        delete(x.next);
      }else x = x.next;
    }
  }

  /**
   * A generic method of deleting any node from the List.
   * @param e
   */
  private void delete(Node<K> e) {
    if (e == null) throw new NullPointerException();
    Node<K> pred = e.prev;
    Node<K> succ = e.next;
    // e is the first node
    if (pred == null) {
      // if e is the only node in the list
      if (succ == null) last = null;
      else succ.prev = null;
      first = succ;
      e.next = null;
      e.key = null;
      return;
    }
    // e is the last node
    if (succ == null) {
      pred.next = null;
      last = pred;
      e.prev = null;
      e.key = null;
      return;
    }
    // e is some node whose non-null pred and succ
    pred.next = succ;
    succ.prev = pred;
    e.prev = null;
    e.next = null;
    e.key = null;
    return;
  }
  
  /**
   * Removes node from a from the front of the list one by one and
   * add it to the front of this list
   * @param a
   */
  public void moveNode(LinkedList<K> a){
    Node<K> f = a.first;
    // If this list is empty
    while(f != null){
      f = f.next;
      K item = a.removeFirst();
      push(item);
    }
  }

  private void push(Node<K> x) {
    assert x != null;
    final Node<K> f = first;
    first = x;
    x.prev = null;
    x.next = f;
    if (f == null) last = x;
    else f.prev = x;
  }
  
  public void push(K item){
    Node<K> f = first;
    final Node<K> newNode = new Node<K>(item, null, f);
    push(newNode);
  }
  
  private void checkListSorted(boolean asce) {
    if (asce) {
      Node<K> x = first;
      while (x != null && x.next != null) {
        if (x.key.compareTo(x.next.key) > 0)
          throw new RuntimeException("Linked list is not sorted");
        x = x.next;
      }
    } else {
      Node<K> x = first;
      while (x != null && x.next != null) {
        if (x.key.compareTo(x.next.key) < 0)
          throw new RuntimeException("Linked list is not sorted");
        x = x.next;
      }
    }
  }

  private void checkValidIndex(int index) {
    if (!(index >= 0 && index < size)) throw new IndexOutOfBoundsException();
  }

  /* A static class is behaviourly top level class that is nested for convience.
   * static class like any other static members cannot access the non-static
   * members of the enclosing class */
  private static class Node<K> {
    private K       key;
    private Node<K> prev;
    private Node<K> next;

    Node(K key, Node<K> prev, Node<K> next) {
      this.key = key;
      this.prev = prev;
      this.next = next;
    }
  }

  @Override
  public Iterator<K> iterator() {
    return new ListItr();

  }

  private class ListItr implements Iterator<K> {
    private Node<K> f;

    private ListItr() {
      f = first;
    }

    @Override
    public boolean hasNext() {
      return f != null;
    }

    @Override
    public K next() {
      if (!hasNext()) throw new NoSuchElementException();
      K item = f.key;
      f = f.next;
      return item;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }

  }

  public static void main(String[] args) {
    LinkedList<Integer> a = new LinkedList<>();
    a.addLast(1);
    // System.out.println(a.getMiddle());
    a.addLast(2);
    // System.out.println(a.getMiddle());
    a.addLast(3);
    a.addLast(4);
    a.addLast(5);
    a.addLast(6);
    a.addLast(7);
    LinkedList<Integer> b = new LinkedList<>();
    // System.out.println(a.getMiddle());
    // a.addLast(12);
    // System.out.println(a.getMiddle());
   /* b.addLast(10);
    // System.out.println(a.getMiddle());
    b.addLast(18);
    b.addLast(9);
    b.addLast(11);
    b.addLast(13);
    b.addLast(14);*/
    
    a.moveNode(b);
    
    
   
    Iterator<Integer> itr = a.iterator();
    while(itr.hasNext()){
      System.out.println(itr.next());
    }
    
  }

}
