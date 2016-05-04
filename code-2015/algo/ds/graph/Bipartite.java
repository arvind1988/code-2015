package algo.ds.graph;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Bipartite {

  // keep track of undiscovered, discovered and finished vertices
  private int[]            color;

  private int[]            parent;
  private static final int WHITE       = 0;    // undiscoverd vertex
  private static final int GRAY        = 1;    // discovered vertex
  private static final int BLACK       = 2;    // finished exploring
  private boolean          isBipartite = false;
  private boolean[]        twoColor;

  public Bipartite(Graph G) {
    isBipartite = true;
    color = new int[G.V()];
    parent = new int[G.V()];
    twoColor = new boolean[G.V()];
    dfs(G, G.V());
  }

  public void dfs(Graph G, int V) {
    Arrays.fill(color, WHITE);
    Arrays.fill(parent, -1);
    for (int v = 0; v < V; v++) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        dfsVisit(G, v);
      }
    }
  }

  public void dfsVisit(Graph G, int u) {
    color[u] = GRAY;
    if (!isBipartite) return;
    for (int v : G.adj(u)) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        parent[v] = u;
        twoColor[v] = !twoColor[u];
        dfsVisit(G, v);
      } else if (twoColor[v] == twoColor[u]) {
        isBipartite = false;
      }
    }
    color[u] = BLACK;
  }

  public boolean isBipartite() {
    return isBipartite;
  }

  public static void main(String[] args) {
    Class<?> thisClass = null;
    try {
      thisClass = Class.forName("algo.ds.graph.Digraph");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    InputStream in = thisClass.getResourceAsStream("/resources/data/tinyG.txt");
    Graph G = new Graph(new Scanner(in));
    Bipartite bp = new Bipartite(G);
    System.out.println(bp.isBipartite());

  }

}
