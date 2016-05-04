package algo.ds.graph;

public class DepthFirstSearch {

  // Each of the vertices in V have a field color[i] where i is in V
  private boolean[]     color;
  private final boolean WHITE = false;
  private final boolean BLACK = true;

  // For each vertex v in V this records the parent of v in depth first tree
  private int[]         parent;

  /*
   * Each d[i] records when a vertex is discovered for the first time. We will
   * initialize a global "time" counter. Each time we visit a vertex in the
   * graph we increase the time counter by 1;
   */
  private int[]         d;

  // This records the finish time of each vertex;
  private int[]         f;
  private static int    time  = 0;

  public DepthFirstSearch(int V) {
    color = new boolean[V];
    parent = new int[V];
    d = new int[V];
    f = new int[V];
  }

  /**
   * Depth first searching of graph g gives us many useful datas. <i>parent</i>
   * array gives the depth-first-trees. <i>d, f</i> can be used to classify the
   * edges. We assume that the vertices are numbered from 0 to V-1 and we
   * believe there is always a way to map arbitrary vertex name with integers.
   * 
   * @param g Graph represented using adjacency list.
   */
  public void dfs(Graph g) {
    if (g == null) throw new NullPointerException("Graph is null");
    time = 0;
    int V = g.V();
    for (int u = 0; u < V; u++)
      parent[u] = -1;
    for (int u = 0; u < V; u++) {
      if (color[u] == WHITE) dfsVisit(g, u);
    }
  }

  public void dfsVisit(Graph g, int u) {
    ++time;
    d[u] = time;
    color[u] = BLACK;
    for (int v : g.adj(u)) {
      if (color[v] == WHITE) {
        parent[v] = u;
        dfsVisit(g, v);
      }
    }
    f[u] = ++time;
  }

  public static void main(String[] args) {

  }

}
