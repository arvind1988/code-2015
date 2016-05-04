package algo.ds.graph;

import java.util.Arrays;

public class CC {

  // keep track of undiscovered, discovered and finished vertices
  private int[]            color;
  // id of connected components in the range of 0 to V - 1
  private int[]            id;
  private int              count;
  // set of vertices in the Depth-first-forest created by DFS
  private int[]            parent;
  private static final int WHITE = 0; // undiscoverd vertex
  private static final int GRAY  = 0; // discovered vertex
  private static final int BLACK = 0; // finished exploring
  private static int       time;
  private int[]            d;        // discovery time of u
  private int[]            f;        // finishing time of u

  public CC(Graph G) {
    dfs(G, G.V());
  }

  public void dfs(Graph G, int V) {
    Arrays.fill(color, WHITE);
    Arrays.fill(parent, -1);
    for (int v = 0; v < V; v++) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        count++;
        dfsVisit(G, v);
      }
    }
  }

  public void dfsVisit(Graph G, int u) {
    time++;
    // set the discovery time
    d[u] = time;
    // change the color to indicate discovered
    color[u] = GRAY;
    // u belong to id count
    id[u] = count;
    for (int v : G.adj(u)) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        parent[v] = u;
        dfsVisit(G, v);
      }
    }
    color[u] = BLACK;
    f[u] = ++time;
  }

  public boolean connected(int u, int v){
    return id[u] == id[v];
  }
  
  public int id(int v){
    return id[v];
  }
  
  public int count(){
    return count;
  }
  public static void main(String[] args) {
    
    

  }

}
