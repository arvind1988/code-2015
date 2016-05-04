package algo.ds.graph;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Digraph {

  private int V;
  private int E;
  private List<Integer>[] adj;
  
  /**
   * Create an empty graph with V vertices and no edges. This is useful when
   * client want to use addEdge method to add edges once the graph is created.
   * 
   * @param V
   */
  @SuppressWarnings("unchecked")
  public Digraph(int V) {
    if (V <= 0)
      throw new IllegalArgumentException("#Verices -ve");
    this.V = V;
    this.E = 0;

    // Create a array of List of raw type and cast it back into generic type
    adj = (List<Integer>[]) new List[V];
    for (int v = 0; v < V; v++)
      adj[v] = new ArrayList<Integer>();
  }

  public Digraph(Scanner in){
    this(in.nextInt());
    int e = in.nextInt();
    //System.out.println("#Edges = " + e);
    while(e != 0 && in.hasNextInt()){
      addEdge(in.nextInt(), in.nextInt());
      e--;
    }
  }
  
  /**
   * Adds directed edge from v to w. It performs validation before adding any edge to 
   * the graph.
   * @param v
   * @param w
   */
  public void addEdge(int v, int w){
    validateVertex(v);
    validateVertex(w);
    adj[v].add(w);
    E++;
  }
  
  public void validateVertex(int v) {
    if (v < 0 || v >= V)
      throw new IllegalArgumentException("Vertex id not allowed");
  }
  
  public int V(){
    return V;
  }
  public int E(){
    return E;
  }
  public Iterable<Integer> adj(int v) {
    validateVertex(v);
    return adj[v];
  }
  
  public String toString() {
    String NEWLINE = System.getProperty("line.separator");
    System.out.print("#Vertices = " + V + " #Edges = " + E + NEWLINE);
    StringBuilder sb = new StringBuilder();
    for (int v = 0; v < V; v++) {
      sb.append(v + ": ");
      for (int w : adj[v]) {
        sb.append(w + " ");
      }
      sb.append(NEWLINE);
    }
    return sb.toString();

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
    bfsDigraph.path(2, 12);
  }

}
