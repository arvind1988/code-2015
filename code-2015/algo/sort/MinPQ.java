package algo.sort;

import java.util.Arrays;

public class MinPQ<K extends Comparable<K>> {
  // Arrays of keys representing the heap;
  private K[] key;
  // heapsize <= key.length
  private int heapsize;

  public MinPQ(K[] data) {
    buildHeap(data);
  }

  /*
   * Builds the heap bottom up. Array elements from key.length/2 to key.length -
   * 1 are all leafs of the binary heap and hence the roots of trivial max
   * heaps.
   */
  private void buildHeap(K[] data) {
    key = data;
    heapsize = key.length;
    for (int i = key.length / 2; i >= 0; i--) {
      minHeapify(i);
    }
  }

  // Maintains the heap property
  private void minHeapify(int i) {
    // Get the left and right child indices
    int l = left(i);
    int r = right(i);
    int min = i;
    if (l < heapsize && lessThan(key[l], key[i])) min = l;
    if (r < heapsize && lessThan(key[r], key[min])) min = r;
    if (min == i) return;
    // Swap the min with i
    K temp = key[min];
    key[min] = key[i];
    key[i] = temp;
    // Swapping might have violated the property.
    minHeapify(min);
  }

  private boolean lessThan(K x, K y) {
    return x.compareTo(y) < 0;
  }

  // Returns the left child index
  private int left(int i) {
    return 2 * i + 1;
  }

  // Returns the right child index
  private int right(int i) {
    return 2 * (i + 1);
  }

  // Returns the parent of node i
  private int parent(int i) {
    if (i % 2 == 0) return i / 2 - 1;
    else return i / 2;
  }

  // Returns the max in the heap
  public K heapMin() {
    return key[0];
  }

  // Returns and remove max from the heap
  public K heapExtractMin() {
    if (heapsize <= 0) {
      System.out.println("Heap underflow");
      return null;
    }
    K min = key[0];
    key[0] = key[heapsize - 1];
    heapsize = heapsize - 1;
    minHeapify(0);
    return min;
  }

  /*
   * Sorts the array by removing the max elements and exchanging it with the
   * last element of the heap and then running maxheapify on the first element
   * to maintain the heap property
   */
  public void sort(K[] data) {
    buildHeap(data);
    for (int i = key.length - 1; i > 0; i--) {
      K temp = key[i];
      key[i] = key[0];
      key[0] = temp;
      heapsize -= 1;
      minHeapify(0);
    }
  }

  // Increase a key in the node to some greater value
  public void heapDecreaseKey(K x, int i) {
    if (i >= heapsize) {
      System.out.println("key not in heap");
      return;
    }
    if (lessThan(key[i], x)) {
      System.out.println("new key is greater than this key ");
      return;
    }
    key[i] = x;
    swim(i);
  }

  // Insert a new element in the heap
  public void heapInsert(K x) {
    if (heapsize == key.length) {
      System.out.println("Heap overflow");
      return;
    }
    key[heapsize] = x;
    // To maintain the heap property of the heap
    swim(heapsize);
    heapsize = heapsize + 1;
  }

  // If the key[i] < key[parent(i)] then move key[i] up the tree. Does it until
  // heap property gets satisfied
  public void swim(int i) {
    int p = parent(i);
    while (p > 0 && lessThan(key[i], key[p])) {
      K temp = key[i];
      key[i] = key[p];
      key[p] = temp;
      i = p;
      p = parent(i);
    }
  }

  public static void main(String[] args) {
    Integer[] data = new Integer[]{15,2,3,19,9,10,12,17,1};
    System.out.println(Arrays.toString(data));
    MinPQ<Integer> pq = new MinPQ<>(data);
    System.out.println(pq.heapExtractMin());
    pq.heapInsert(13);
    //System.out.println(pq.heapMin());
    //System.out.println(pq.heapExtractMin());
    pq.sort(data);
    System.out.println(Arrays.toString(data));
    

  }

}
