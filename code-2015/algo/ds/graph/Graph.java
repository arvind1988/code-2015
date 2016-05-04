package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * The Graph class represents an undirected with set of vertices and edges. The
 * number of vertices in the graph is <i>V</i> and number of edges are <i>E</i>
 * This particular represenation uses adjacency list <i>adj</i>. The main idea
 * keeping this simple is to decouple the graph processing with the graph repre-
 * sentation. We assume that the vertices are represented by integers from 0 to
 * V - 1. We can represent the vertices by integers in almost all the cases.
 * This gives us the ability to quickly go to a vertex in the graph using the
 * array index.
 * 
 * @author Arvind_Kumar2
 *
 */
public class Graph {
  private int             V;  // #vertices
  private int             E;  // #Edges
  private List<Integer>[] adj;

  // We create an empty graphs with no edges
  @SuppressWarnings("unchecked")
  public Graph(int V) {
    if (V < 0)
      throw new IllegalArgumentException(
          "Number of vertices can't be less than 0");
    this.V = V;
    this.E = 0;

    // No generic type array in java so we need cast.
    adj = (List<Integer>[]) new List[V];
    for (int i = 0; i < V; i++)
      adj[i] = new ArrayList<Integer>();
  }

  public Graph(Scanner in) {
    this(in.nextInt());
    System.out.println("Number of vertices = " + V);
    in.nextLine();

    int e = in.nextInt();
    System.out.println("Number of edges = " + e);
    if (e < 0)
      throw new IllegalArgumentException("Number of edges can't be negative");
    for (int i = 0; i < e; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      addEdge(u, v);
    }
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(int a, int b) {
    validateVertex(a);
    validateVertex(b);
    adj[a].add(b);
    adj[b].add(a);
    E++;
  }

  public int degree(int a) {
    validateVertex(a);
    return adj[a].size();
  }

  public void validateVertex(int a) {
    if (a < 0 || a >= V) throw new ArrayIndexOutOfBoundsException();
  }

  public Iterable<Integer> adj(int v) {
    validateVertex(v);
    return adj[v];
  }

  public String toString() {
    StringBuilder s = new StringBuilder();
    String NEWLINE = System.getProperty("line.separator");
    s.append(V + " vertices, " + E + " edges " + NEWLINE);
    for (int v = 0; v < V; v++) {
      s.append(v + ": ");
      for (int w : adj[v]) {
        s.append(w + " ");
      }
      s.append(NEWLINE);
    }
    return s.toString();
  }

  public static void main(String[] args) {

  }

}
