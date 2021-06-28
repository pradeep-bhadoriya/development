import java.lang.reflect.Array;
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

        Pair(int wsf, String psf) {
            this.wsf = wsf;
            this.psf = psf;
        }

        public int compareTo(Pair o) {
            return this.wsf - o.wsf;
        }
    }

    public static void multisolver(ArrayList<Edge>[] graph, int src, int dst, boolean[] visited, int criteria, int k,
            String psf, int wsf) {
        // System.out.println(psf);
        if (src == dst) {
            if (wsf < spathwt) {
                spath = psf;
                spathwt = wsf;
            }
            if (wsf > spathwt) {
                lpath = psf;
                lpathwt = wsf;
            }
            if (wsf < cpathwt && wsf > criteria) {
                cpathwt = wsf;
                cpath = psf;
            }
            if (wsf > fpathwt && wsf < criteria) {
                fpathwt = wsf;
                fpath = psf;
            }
            if (pq.size() < k) {
                pq.add(new Pair(wsf, psf));
            } else {
                if (pq.peek().wsf < wsf) {
                    pq.remove();
                    pq.add(new Pair(wsf, psf));
                }
            }
            return;
        }
        visited[src] = true;
        for (Edge e : graph[src]) {
            int nbr = e.nbr;
            int wt = e.wt;
            if (visited[nbr] == false) {
                multisolver(graph, nbr, dst, visited, criteria, k, psf + nbr, wsf + wt);
            }
        }
        visited[src] = false;
    }

    public static void getConnedtedComponent(ArrayList<Edge>[] graph, int src, ArrayList<Integer> comp,boolean[] vis) {
        if(graph[src].size()==0){
            comp.add(src);
            return;
        }
        comp.add(src);
        vis[src]=true;
        for(Edge e:graph[src]){
            if(vis[e.nbr]==false){
                getConnedtedComponent(graph, e.nbr, comp, vis); 
            }
        }
        return;
    }

    public static void getConnectedComponents(ArrayList<Edge>[] graph){
        ArrayList<ArrayList<Integer>> comps=new ArrayList<>();
        boolean[] vis=new boolean[graph.length];
        
        for (int src = 0; src < graph.length; src++) {
            ArrayList<Integer> comp=new ArrayList<>();
            if(vis[src]==false){
                getConnedtedComponent(graph,src,comp,vis);
                comps.add(comp);
            }
        }
        System.out.println(comps);
    }

    public static void getConnectedComponentsForIsland(int[][] graph, int i, int j){
        // vis[i][j]=true;
        graph[i][j]=1;
        int[] xdir={-1,0,1,0};
        int[] ydir={0,-1,0,1};
        for(int dir=0;dir<4;dir++){
            int x=i+xdir[dir];
            int y=j+ydir[dir];

            if(0<=x && x<graph.length && 0<=y && y<graph[i].length && graph[x][y]==0){
                getConnectedComponentsForIsland(graph,x,y);
            }
        }
    }

    public static int countIsland(int[][] graph){
        int m=graph.length;
        int n=graph[0].length;
        int count=0;
        // boolean[][] vis=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(graph[i][j]==0){
                    getConnectedComponentsForIsland(graph, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void hamiltonianPath(ArrayList<Edge>[] graph, int src,int root, boolean[] vis, String psf){
        if(psf.length()==graph.length-1){
            psf+=src;
            for(Edge e:graph[src]){
                if(e.nbr==root){
                    System.out.println(psf+"*");
                    return;
                }
            }
            System.out.println(psf+".");
            return;
        }
        vis[src]=true;
        for(Edge e:graph[src]){
            if(vis[e.nbr]==false){
                hamiltonianPath(graph, e.nbr, root, vis, psf+src);
            }
        }
        vis[src]=false;
    }

    public static class bfsPair{
        int vtx;
        String psf;

        public bfsPair(int vtx, String psf){
            this.vtx=vtx;
            this.psf=psf;
        }
    }

    public static void bfsGraph(ArrayList<Edge>[] graph, int src){
        Queue<bfsPair> qu=new ArrayDeque<>();
        boolean[] vis=new boolean[graph.length];
        qu.add(new bfsPair(src, ""+src));
        while(qu.size() >0){
            vis[qu.peek().vtx]=true;
            for(Edge e:graph[qu.peek().vtx]){
                if(vis[e.nbr]==false){
                    qu.add(new bfsPair(e.nbr, qu.peek().psf+e.nbr));
                    vis[e.nbr]=true;
                }
            }
            System.out.println(""+qu.peek().vtx+"@"+qu.peek().psf);
            qu.remove();
        }     
    }

    public static boolean bfsForCycle(ArrayList<Edge>[] graph, int src, boolean[] vis){
        Queue<Integer> qu=new ArrayDeque<>();
        qu.add(src);
        // vis[src]=true;
        // int sz=qu.size();
        while(qu.size()>0){
            int rem=qu.remove();
            if(vis[rem]==true){
                //cycle is present
                return true;
            }
            else{
                vis[rem]=true;
            }

            for(Edge e:graph[rem]){
                if(vis[e.nbr]==false){
                    qu.add(e.nbr);
                }
            }
        }
        return false;
    }

    public static boolean dfsForCycle(ArrayList<Edge>[] graph, int src,int prt, boolean[] vis) {
        if(vis[src]==true){
            //cycle is present
            return true;
        }
        vis[src]=true;
        for(Edge e:graph[src]){
            if(e.nbr!= prt){
                boolean ans=dfsForCycle(graph, e.nbr, src, vis);
                if(ans){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isGraphCycle(ArrayList<Edge>[] graph){
        boolean[] vis=new boolean[graph.length];
        for(int i=0;i<graph.length;i++){
            if(vis[i]==false){
                boolean isCycle=dfsForCycle(graph, i, -1,  vis);
                if(isCycle){
                    return true;
                }
            }
        }
        return false;
    }

    public static class bfsPairForBipartite{
        int vtx;
        int lvl;

        public bfsPairForBipartite(int vtx, int lvl){
            this.lvl=lvl;
            this.vtx=vtx;
        }
    }

    public static boolean bfsForBipartite(ArrayList<Edge>[] graph, int src, int[] vis){
        Queue<bfsPairForBipartite> qu=new ArrayDeque<>();
        qu.add(new bfsPairForBipartite(src, 0));
        while(qu.size()>0){
            bfsPairForBipartite rem=qu.remove();
            if(vis[rem.vtx]!=-1){
                System.out.println(rem.lvl);
                if(rem.lvl==vis[rem.vtx]){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                vis[rem.vtx]=rem.lvl;
            }

            for(Edge e:graph[rem.vtx]){
                if(vis[e.nbr]==-1){
                    qu.add(new bfsPairForBipartite(e.nbr, rem.lvl+1));
                }
            }
        }
        return true;
    }

    public static boolean isBapartite(ArrayList<Edge>[] graph){
        int[] vis=new int[graph.length];
        Arrays.fill(vis,-1);

        for(int i=0;i<graph.length;i++){
            if(vis[i]==-1){
                boolean ans=bfsForBipartite(graph, i, vis);
                if(ans==false){
                    return false;
                }
            }
        }
        return true;
    }

    public static class bfsPairForInfection{
        int vtx;
        int lvl;

        public bfsPairForInfection(int vtx, int lvl){
            this.lvl=lvl;
            this.vtx=vtx;
        }
    }

    public static int spreadInfection(ArrayList<Edge>[] graph, int t, int src, boolean[] vis, int count){
        Queue<bfsPairForInfection> qu=new ArrayDeque<>();
        if(t==0) return 1;
        qu.add(new bfsPairForInfection(src, 1));
        while(qu.size()>0){
            bfsPairForInfection rem=qu.remove();
            
            
            if(vis[rem.vtx]==true){
                continue;
            }
            else{
                vis[rem.vtx]=true;
                count++;
            }
            if(rem.lvl==t+1) break;

            for(Edge e:graph[rem.vtx]){
                if(vis[e.nbr]==false){
                    qu.add(new bfsPairForInfection(e.nbr, rem.lvl+1));
                }
            }
        }
        // base case
        return qu.size()==0?count:count-1;
        // faith

    }

    public static class DPair implements Comparable<DPair>{
        int vtx;
        String psf;
        int wsf;

        public DPair(int vtx, String psf, int wsf){
            this.vtx=vtx;
            this.psf=psf;
            this.wsf=wsf;
        }

        @Override
        public int compareTo(DPair other){
            return this.wsf-other.wsf;
        }

    }

    public static void Dijkstras(ArrayList<Edge>[] graph, int src){
        PriorityQueue<DPair> pq=new PriorityQueue<>();
        pq.add(new DPair(src, ""+src, 0));
        boolean[] vis=new boolean[graph.length];
        while(pq.size()>0){
            DPair rem=pq.remove();
            if(vis[rem.vtx]==true){
                continue;
            }
            else{
                vis[rem.vtx]=true;
                System.out.println(rem.vtx + " via " + rem.psf + " @ " + rem.wsf);
            }
            for(Edge e:graph[rem.vtx]){
                if(vis[e.nbr]==false){
                    pq.add(new DPair(e.nbr, rem.psf+e.nbr, rem.wsf+e.wt));
                }
            }
        }
    }

    public Phelper(int vtx, int parent, int wt) {
        this.vtx = vtx;
        this.parent = parent;
        this.wt = wt;
    }

    public int compareTo(Phelper o) {
        return this.wt - o.wt;
    } 
}

public static void prims(ArrayList<Edge>[] graph) {
    PriorityQueue<Phelper> pq = new PriorityQueue<>();
    pq.add(new Phelper(3, -1, 0));

    int n = graph.length;
    ArrayList<Edge>[] mst = new ArrayList[n];
    for(int v = 0; v < n; v++) {
        mst[v] = new ArrayList<>();
    }

    boolean[] vis = new boolean[n];

    while(pq.size() > 0) {
        // 1. get + rem
        Phelper rem = pq.remove();
        // 2. mark
        if(vis[rem.vtx] == true) continue;
        vis[rem.vtx] = true;
        // 3. work -> print(for question) + add edge(mst graph)
        if(rem.parent != -1) {
            System.out.println("[" + rem.vtx + "-" + rem.parent + "@" + rem.wt + "]");
            addEdge(mst, rem.vtx, rem.parent, rem.wt);
        }

        // 4. add neighbour
        for(Edge e : graph[rem.vtx]) {
            if(vis[e.nbr] == false) {
                pq.add(new Phelper(e.nbr, rem.vtx, e.wt));
            }
        }
    }
    display(mst);
}



    public static void fun() {
        ArrayList<Edge>[] graph = createGraph();
        boolean[] vis = new boolean[graph.length];
        int[][] arr = {
                {0, 1, 1, 0, 0},                                                                                                                
                {0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
            };
        // int count1=countIsland(arr);
        // System.out.println(count1);
        // System.out.println(hasPath(graph, 0, 5, vis));
        // multisolver(graph, 0, 6, vis, 100, 5, "", 0);
        // System.out.println("Smallest path = " + spath + "@" + spathwt);
        // System.out.println("longest path = " + lpath + "@" + lpathwt);
        // System.out.println("ceil path = " + cpath + "@" + cpathwt);
        // System.out.println("floor path = " + fpath + "@" + fpathwt);
        display(graph);
        // hamiltonianPath(graph, 0, 0, vis,"");
        // bfsGraph(graph,2);
        // boolean ans=isBapartite(graph);
        // int ans=spreadInfection(graph, 3, 6, vis, 0);
        // System.out.println(ans);
        Dijkstras(graph, 0);

    }

    public static ArrayList<Edge>[] createGraph() {
        // n-> number of vertices
        int n = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        // int[][] data = {{0, 1, 10},{0, 3, 40},{1, 2, 10},{2, 3, 10},{3, 4, 2},{4, 5, 3},{4, 6, 8},{5, 6, 3}};
        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 10);
        addEdge(graph, 0, 3, 40);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 3);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 4, 6, 8);
        // addEdge(graph, 7, 7, 90);
        addEdge(graph, 2, 5, 5);

        // for(int i = 0; i < data.length; i++) {
        // addEdge(graph, data[i][0], data[i][1], data[i][2]);
        // }
        return graph;
    }

    public static void main(String[] args) {
        fun();
    }
}
