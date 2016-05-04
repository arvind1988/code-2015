package algo.ds.graph;

import java.util.Arrays;

public class UF {
  // Number of different components
  private int   count;
  // id[i] gives the id of the parent of i;
  private int[] id;
  // Size of each component
  private int[] size;

  public UF(int N) {
    id = new int[N];
    size = new int[N];
    // Initially each component is of size 1
    Arrays.fill(size, 1);
    count = N;
    // There are N different components at the start of the processing
    for (int i = 0; i < N; i++)
      id[i] = i;
  }

  public void union(int p, int q) {
    // Get p's tree root and q's tree root.
    int i = find(p);
    int j = find(q);
    // If already connected then return.
    if (i == j) return;
    // Connect the smaller tree parent as child of larger tree
    if (size[i] <= size[j]) {
      id[i] = j;
      // Update the new tree size
      size[i] = size[i] + size[j];
    } else {
      id[j] = i;
      size[j] = size[i] + size[j];
    }
    // decrease the component size
    count--;
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  // counts total number of components
  public int count() {
    return count;
  }

  // returns the component identifier in which p belongs
  public int find(int p) {
    return root(p);
  }

  // Returns the root of the tree in which P lies by following the parent
  // pointer
  private int root(int p) {
    int x = p;
    while (x != id[x]) {
      x = id[x];
    }
    return x;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
