package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * This is BFS on a directed graph. The constructor does the processing and
 * builds up the data structures which will be helpful in client query. The code
 * is exactly similar to BFS on undirected graph.
 * 
 * @author Arvind_Kumar2
 *
 */
public class BreadFirstSearchDigraph {

  private int[] parent;
  private int[] distTo;
  private boolean[] color;
  private static final boolean WHITE = false;
  private static final boolean BLACK = true;
  
  public BreadFirstSearchDigraph(Digraph g) {
    int V = g.V();
    parent = new int[V];
    distTo = new int[V];
    color = new boolean[V];
    bfs(g, 0);
  }

  private void bfs(Digraph g, int s) {
    distTo[s] = 0;
    parent[s] = -1;
    color[s] = BLACK;
    Deque<Integer> queue = new ArrayDeque<>();
    queue.add(s);
    while (!queue.isEmpty()) {
      int v = queue.remove();
      for (int w : g.adj(v)) {
        if (color[w] == WHITE) {
          queue.add(w);
          parent[w] = v;
          distTo[w] = distTo[v] + 1;
          color[w] = BLACK;
        }
      }
    }
  }

  public void path(int v, int w) {
    if (v == w) {
      System.out.print(v + " -> ");
      return;
    }else if (color[w] == WHITE) {
      System.out.println("No path to " + w + " from " + v);
      return;
    }else{
      path(v, parent[w]);
      // LIFO order will from path from source to vertex in order.
      System.out.print(w + " -> ");
    }
  }

  public static void main(String[] args) {
    Class<?> thisClass = null;
    try {
      thisClass = Class.forName("algo.ds.graph.Digraph");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    InputStream in = thisClass
        .getResourceAsStream("/resources/directedGraph.txt");
    Digraph digraph = new Digraph(new Scanner(in));
    System.out.println(digraph.toString());
    BreadFirstSearchDigraph bfsDigraph = new BreadFirstSearchDigraph(digraph);
    bfsDigraph.path(0, 4);

  }

}
