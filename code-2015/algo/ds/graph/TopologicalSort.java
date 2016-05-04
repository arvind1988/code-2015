package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class TopologicalSort {

  private static int       time;
  private static final int WHITE = 0;
  private static final int GRAY  = 1;
  private static final int BLACK = 2;

  private int[]            color;
  private int[]            parent;
  private int[]            d;
  private int[]            f;

  private Deque<Integer>   reversePost;

  public TopologicalSort(Digraph G) {
    int V = G.V();
    color = new int[V];
    parent = new int[V];
    d = new int[V];
    f = new int[V];
    reversePost = new ArrayDeque<>();
    dfs(G);
  }

  public void dfs(Digraph G) {
    if (isDAG(G)) return;
    int V = G.V();
    Arrays.fill(color, WHITE);
    Arrays.fill(parent, -1);
    for (int u = 0; u < V; u++) {
      if (color[u] == WHITE) {
        dfsVisit(G, u);
      }
    }
  }

  public boolean isDAG(Digraph G) {
    DirectedCycle dc = new DirectedCycle(G);
    return dc.hasCycle();
  }

  public void dfsVisit(Digraph G, int u) {
    time++;
    color[u] = GRAY;
    d[u] = time;
    for (int v : G.adj(u)) {
      if (color[v] == WHITE) {
        parent[v] = u;
        color[v] = GRAY;
        dfsVisit(G, v);
      }
    }
    // U is visited now put that on the stack
    reversePost.push(u);
    color[u] = BLACK;
    f[u] = ++time;
  }

  public Iterable<Integer> topologicalOrder() {
    return reversePost;
  }

  public static void main(String[] args) {
    Class<?> thisClass = null;
    try {
      thisClass = Class.forName("algo.ds.graph.DirectedCycle");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    InputStream in = thisClass
        .getResourceAsStream("/resources/data/tinyDAG.txt");
    Digraph G = new Digraph(new Scanner(in));
    TopologicalSort ts = new TopologicalSort(G);
    Iterable<Integer> itr = ts.topologicalOrder();
    for (int x : itr)
      System.out.print(x + " ");
    System.out.println();

  }

}
