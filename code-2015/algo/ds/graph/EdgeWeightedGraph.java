package algo.ds.graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EdgeWeightedGraph {

  private List<Edge>[] adj;
  private int          V;
  private int          E;

  @SuppressWarnings("unchecked")
  public EdgeWeightedGraph(int V) {
    if (V <= 0) throw new IllegalArgumentException();
    this.V = V;
    // No generic array creation in java and this ugly cast;
    adj = (List<Edge>[]) new List[V];
    this.V = V;
    E = 0;
    // Allocate memory for each of the lists;
    for (int i = 0; i < V; i++)
      adj[i] = new ArrayList<Edge>();

  }

  public EdgeWeightedGraph(InputStream in) {
    try {
      int b = in.read();
      System.out.println((char)b);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    

  }
  public Iterable<Edge> adj(int u) {
    return adj[u];
  }

  public int V() {
    return V;
  }

  public int E() {
    return E;
  }

  public void addEdge(Edge e) {
    int u = e.either();
    int v = e.other(u);
    adj[u].add(e);
    adj[v].add(e);
    E++;
  }



  private static class Edge {
    private int   u;
    private int   v;
    private float w;

    private Edge(int u, int v, float w) {
      this.u = u;
      this.v = v;
      this.w = w;
    }

    // Returns any vertex of edge e
    public int either() {
      return v;
    }

    // We can throw a better expection here.
    public int other(int vertex) {
      if (vertex == v) return u;
      if (vertex == u) return u;
      throw new IllegalArgumentException("Vextex not valid");
    }
    
    public float weight(){
      return w;
    }

  }

  public static void main(String[] args) throws FileNotFoundException {
    EdgeWeightedGraph g = new EdgeWeightedGraph(new FileInputStream("src/resources/data/stream.txt"));

  }

}
