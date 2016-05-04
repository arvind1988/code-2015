package algo.sort;

import java.util.Arrays;

public class MaxPQ<K extends Comparable<K>> {
  // Arrays of keys representing the heap;
  private K[] key;
  // heapsize <= key.length
  private int heapsize;

  public MaxPQ(K[] data) {
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
      maxHeapify(i);
    }
  }

  // Maintains the heap property
  private void maxHeapify(int i) {
    // Get the left and right child indices
    int l = left(i);
    int r = right(i);
    int max = i;
    if (l < heapsize && lessThan(key[i], key[l])) max = l;
    if (r < heapsize && lessThan(key[max], key[r])) max = r;
    if (max == i) return;
    // Swap the max with i
    K temp = key[max];
    key[max] = key[i];
    key[i] = temp;
    // Swapping might have violated the property.
    maxHeapify(max);
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
  public K heapMax() {
    return key[0];
  }

  // Returns and remove max from the heap
  public K heapExtractMax() {
    if (heapsize <= 0) {
      System.out.println("Heap underflow");
      return null;
    }
    K max = key[0];
    key[0] = key[heapsize - 1];
    heapsize = heapsize - 1;
    maxHeapify(0);
    return max;
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
      maxHeapify(0);
    }
  }

  // Increase a key in the node to some greater value
  public void heapIncreaseKey(K x, int i) {
    if (i >= heapsize) {
      System.out.println("key not in heap");
      return;
    }
    if (lessThan(x, key[i])) {
      System.out.println("new key is smaller than this key ");
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
    while (p > 0 && !lessThan(key[i], key[p])) {
      K temp = key[i];
      key[i] = key[p];
      key[p] = temp;
      i = p;
      p = parent(i);
    }
  }

  public static void main(String[] args) {
    Integer[] data = new Integer[]{15,2,3,19,9,10,12,17,1};
    MaxPQ<Integer> pq = new MaxPQ<>(data);
   // System.out.println(pq.heapMax());
    System.out.println(pq.heapExtractMax());
    pq.sort(data);
    System.out.println(Arrays.toString(data));
    

  }

}
