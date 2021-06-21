import java.util.*;

public class graph {

    public static class Edge {
        int src;
        int nbr;
        int wt;

        public Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int src, int nbr, int wt) {
        graph[src].add(new Edge(src, nbr, wt));
        graph[nbr].add(new Edge(nbr, src, wt));
    }

    public static ArrayList<Edge>[] createGraph() {
        // n-> number of vertices
        int n = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // int[][] data = {
        // {0, 1, 10},
        // {0, 3, 40},
        // {1, 2, 10},
        // {2, 3, 10},
        // {3, 4, 2},
        // {4, 5, 3},
        // {4, 6, 8},
        // {5, 6, 3}
        // };

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);
        // addEdge(graph, 2, 5, 0);

        // for(int i = 0; i < data.length; i++) {
        // addEdge(graph, data[i][0], data[i][1], data[i][2]);
        // }

        return graph;
    }

    public static void display(ArrayList<Edge>[] graph) {
        for (int v = 0; v < graph.length; v++) {
            System.out.print("[" + v + "] -> ");
            for (int n = 0; n < graph[v].size(); n++) {
                Edge e = graph[v].get(n);
                System.out.print("[" + e.src + "-" + e.nbr + " @ " + e.wt + "], ");
            }
            System.out.println();
        }
    }

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dst, boolean[] vis) {
        if (src == dst) {
            return true;
        }
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (vis[e.nbr] == false)
                return hasPath(graph, e.nbr, dst, vis);
        }
        return false;
    }

    static String spath;
    static Integer spathwt = Integer.MAX_VALUE;
    static String lpath;
    static Integer lpathwt = Integer.MIN_VALUE;
    static String cpath;
    static Integer cpathwt = Integer.MAX_VALUE;
    static String fpath;
    static Integer fpathwt = Integer.MIN_VALUE;
    static PriorityQueue<Pair> pq = new PriorityQueue<>();

    static class Pair implements Comparable<Pair> {
        int wsf;
        String psf;
  
        Pair(int wsf, String psf){
           this.wsf = wsf;
           this.psf = psf;
        }
  
        public int compareTo(Pair o){
           return this.wsf - o.wsf;
        }
     }

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited, int criteria, int k,
            String psf, int wsf) {
                // System.out.println(psf);
                if(src==dst){
                    if(wsf<spathwt){
                        spath=psf;
                        spathwt=wsf;
                    }
                    if(wsf>spathwt){
                        lpath=psf;
                        lpathwt=wsf;
                    }
                    if(wsf<cpathwt && wsf>criteria){
                        cpathwt=wsf;
                        cpath=psf;
                    }
                    if(wsf>fpathwt && wsf<criteria){
                        fpathwt=wsf;
                        fpath=psf;
                    }
                    if(pq.size() < k) {
                        pq.add(new Pair(wsf, psf));
                    } else {
                        if(pq.peek().wsf < wsf) {
                            pq.remove();
                            pq.add(new Pair(wsf, psf));
                        }
                    }
                    return;
                }
                visited[src]=true;
                for(Edge e:graph[src]){
                    int nbr=e.nbr;
                    int wt=e.wt;
                    if(visited[nbr]==false){
                        multisolver(graph, nbr, dst, visited, criteria, k, psf+nbr, wsf+wt);
                    }
                }
                visited[src]=false;
    }


    public static void getConnedtedComponent(ArrayList<Edge>[] graph){
        
    }

    public static getConnectedComponents(ArrayList<Edge>[] graph){
        ArrayList<ArrayList<Integer>> comps=new ArrayList<>();
        boolean[] vis=new boolean[graph.length];
        ArrayList<Integer> comp=new ArrayList<>();
        for (int src = 0; src < graph.length; src++) {
            if(vis[src]==false){
                getConnedtedComponent(graph,src,comp,vis);
                comps.add(comp);
            }
        }
    }

    public static void fun() {
        int n = 7;
        ArrayList<Edge>[] graph = createGraph();
        boolean[] vis = new boolean[7];
        // System.out.println(hasPath(graph, 0, 5, vis));
        multisolver(graph, 0, 6, vis, 100, 5, "", 0);
        System.out.println("Smallest path = "+spath+"@"+spathwt);
        System.out.println("longest path = "+lpath+"@"+lpathwt);
        System.out.println("ceil path = "+cpath+"@"+cpathwt);
        System.out.println("floor path = "+fpath+"@"+fpathwt);

    }

    public static void main(String[] args) {
        fun();
    }
}
