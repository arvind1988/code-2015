package algo.ds.graph;

public class UnionFind {

  private int[] id;
  private int count;

  public UnionFind(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
    count = N;
  }

  // Return true if u and v are connected otherwise false
  public boolean connected(int u, int v) {
    return id[u] == id[v];
  }

  // connects u and v i.e puts u and v in the same component
  // If u and v belong to different components then change the id of all
  // elements of component in which v belongs to that of others
  public void union(int u, int v) {
    int uid = id[u];
    int vid = id[v];
    // Already connected
    if (uid == vid) return;
    // Each union decrease the component count by 1
    count--;
    id[v] = uid;
    for (int i = 0; i < id.length; i++) {
      if (id[i] == vid){
        id[i] = uid;
      }
    }
  }

  // counts total number of components
  public int count() {
    return count;
  }

  // returns the component identifier in which p belongs
  public int find(int p) {
    return id[p];
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
