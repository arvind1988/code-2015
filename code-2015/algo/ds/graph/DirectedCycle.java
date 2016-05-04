package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class DirectedCycle {

  private static int       time;
  private static final int WHITE = 0;
  private static final int GRAY  = 1;
  private static final int BLACK = 2;

  private int[]            color;
  private int[]            parent;
  private int[]            d;
  private int[]            f;

  private Deque<Integer>    cycle;

  private boolean          hasCycle;

  public DirectedCycle(Digraph G) {
    int V = G.V();
    color = new int[V];
    parent = new int[V];
    d = new int[V];
    f = new int[V];
    dfs(G);
  }

  public void dfs(Digraph G) {
    int V = G.V();
    Arrays.fill(color, WHITE);
    Arrays.fill(parent, -1);
    for (int u = 0; u < V; u++) {
      if (color[u] == WHITE) {
        dfsVisit(G, u);
      }
    }
  }

  public void dfsVisit(Digraph G, int u) {
    time++;
    color[u] = GRAY;
    d[u] = time;

    if (hasCycle) return;
    for (int v : G.adj(u)) {
      if (color[v] == WHITE) {
        parent[v] = u;
        color[v] = GRAY;
        dfsVisit(G, v);
      }
      // Gray vertices are on the active DFS stack and yet not popped out
      // A GRAY edge suggest a back edge (Cormen)
      else if (color[v] == GRAY) {
        hasCycle = true;
        cycle = new ArrayDeque<Integer>();
        for (int x = u; x != v && x != -1; x = parent[x]) {
          cycle.push(x);
        }
        cycle.push(v);
        cycle.push(u);
      }
    }
    color[u] = BLACK;
    f[u] = ++time;
  }

  public boolean hasCycle() {
    return hasCycle;
  }

  public Iterable<Integer> cycle() {
    return cycle;
  }

  public static void main(String[] args) {
    Class<?> thisClass = null;
    try {
      thisClass = Class.forName("algo.ds.graph.DirectedCycle");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    InputStream in = thisClass
        .getResourceAsStream("/resources/data/tinyDG.txt");
    Digraph G = new Digraph(new Scanner(in));
    DirectedCycle dc = new DirectedCycle(G);
    Iterable<Integer> itr = dc.cycle();
    for (int x : itr)
      System.out.print(x + " ");
    System.out.println();

  }

}
