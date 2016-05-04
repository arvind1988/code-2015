package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cycle {

  // keep track of undiscovered, discovered and finished vertices
  private int[]            color;
  private int[]            parent;
  private static final int WHITE = 0; // undiscoverd vertex
  private static final int GRAY  = 1; // discovered vertex
  private static final int BLACK = 2; // finished exploring
  private static int       time;
  private int[]            d;        // discovery time of u
  private int[]            f;        // finishing time of u
  private boolean          hasCycle;
  private List<Integer>    cycle;

  public Cycle(Graph G) {
    color = new int[G.V()];
    parent = new int[G.V()];
    d = new int[G.V()];
    f = new int[G.V()];
    dfs(G, G.V());
  }

  public void dfs(Graph G, int V) {
    Arrays.fill(color, WHITE);
    // Arrays.fill(parent, 0);
    for (int v = 0; v < V; v++) {
      if (color[v] == WHITE) {
        color[v] = GRAY;
        parent[v] = -1;
        dfsVisit(G, v, v);
      }
    }
  }

  /**
   * 
   * @param G the graph in which cycle has to be detected
   * @param u current dfs invocation source.
   * @param p vertex from where dfs on u is invovked
   */
  public void dfsVisit(Graph G, int u, int p) {
    time++;
    // set the discovery time
    d[u] = time;
    // change the color to indicate discovered
    color[u] = GRAY;
    // u belong to id count

    for (int v : G.adj(u)) {
      if (cycle != null) return;
      if (color[v] == WHITE) {
        color[v] = GRAY;
        parent[v] = u;
        dfsVisit(G, v, u);
      }
      // V is already GRAY or BLACK . This indicates a cycle. Now we only need
      // to check that this is not equal to vertex from which dfs on u was
      // invovked
      else if (v != p) {
        hasCycle = true;
        cycle = new ArrayList<Integer>();
        for (int x = u; x != v && x != -1; x = parent[x]) {
          cycle.add(x);
        }
        cycle.add(v);
        cycle.add(u);
      }
    }
    color[u] = BLACK;
    f[u] = ++time;
  }

  public boolean hasCycle() {
    return hasCycle;
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
    // System.out.println(G.toString());
    Cycle cycle = new Cycle(G);
    System.out.println(cycle.hasCycle());
    System.out.println(Arrays.toString(cycle.cycle.toArray()));
  }

}
