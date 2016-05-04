package algo.ds.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {
    private final boolean WHITE = false; // An undiscovered vertex
    private final boolean BLACK = true; // A discovered vertex
    private boolean[]     color;
    private int[]         pre;
    private int[]         d;

    /**
     * @param V Number of vertices
     * @param E Number of edges
     */
    public BreadthFirstSearch(int V) {
        color = new boolean[V];
        pre = new int[V];
        d = new int[V];
    }

    public void bfs(Graph g, int s) {
        Queue<Integer> queue = new LinkedList<>();
        g.validateVertex(s);
        color[s] = BLACK;
        pre[s] = -1;
        d[s] = 0;
        queue.add(0);
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int v : g.adj(u)) {
                if (color[v] == WHITE) {
                    queue.add(v);
                    pre[v] = u;
                    d[v] = d[u] + 1;
                    color[v] = BLACK;
                }
            }
        }
    }

    public void printPath(int s, int v) {
        if (color[v] == WHITE) {
            System.out.println("No path to " + v + " " + "from" + s);
            return;
        } else if (s == v) System.out.print(s + " -> ");
        else {
            printPath(s, pre[v]);
            System.out.print(v + " -> ");
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(new Scanner(System.in));
        System.out.println(g.toString());
        BreadthFirstSearch bfsObj = new BreadthFirstSearch(g.V());
        bfsObj.bfs(g, 0);
        bfsObj.printPath(0, 6);

    }

}
