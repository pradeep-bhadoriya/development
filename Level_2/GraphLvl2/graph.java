import java.util.*;

public class graph{
    class Edge{
        int nb;
        ArrayList<Edge> arr;
    }

    // Rotting Oranges
    static int res;
    public int orangesRotting(int[][] grid) {
        res=0;
        helperOrangesRot(grid);
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    // System.out.println(i+" "+j);
                    return -1;
                }
            }
        }
        return res ;
    }
    public int helperOrangesRot(int[][] grid){
        Queue<int[]> qu=new ArrayDeque<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==2){
                    // System.out.println(i+" "+j);
                    int[] temp={i,j,0};
                    qu.add(temp);
                }
            }
        }
        
        ansHelper(grid,qu);
        return res;
    } 
    public int ansHelper(int[][] grid , Queue<int[]> qu ){
        while(qu.size()>0){
            int[] rem=qu.remove();
            incOranges(grid, rem, qu);
        }
        return res;
    }
    public void incOranges(int[][] grid , int[] pair, Queue<int[]> qu){
        int x=pair[0];
        int y=pair[1];
        // System.out.println(x+"-"+y);
        int[] xdir={-1,0,1,0};
        int[] ydir={0,-1,0,1};
        for(int d=0;d<4;d++){
            int xnew=x+xdir[d];
            int ynew=y+ydir[d];
            if(xnew>=0 && xnew<grid.length && ynew>=0 && ynew<grid[0].length && grid[xnew][ynew]==1){
                grid[xnew][ynew]+=1;
                
                int[] temp={xnew, ynew, pair[2]+1};
                qu.add(temp);
                res=Math.max(res,temp[2]);
                
            }
        }
    }

    //542. 01 Matrix
    public int[][] updateMatrix(int[][] mat) {
        Queue<int[]> qu=new LinkedList<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0){
                    int[] temp={i,j};
                    qu.add(temp);
                }
            }
        }
        int lvl=0;
        while(qu.size()>0){
            int sz=qu.size();
            lvl++;
            while(sz-- >0){
                int[] rem=qu.remove();
                bfs_01Matrix(mat, qu, rem[0], rem[1], lvl);
            }
        } 
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                mat[i][j]*=-1;
            }
        }
        return mat;
    }
    public void bfs_01Matrix(int[][] mat, Queue<int[]> qu, int x, int y, int lvl){
            for(int d=0;d<4;d++){
                int xnew=x+xdir[d];
                int ynew=y+ydir[d];
                
                if(xnew>=0 && xnew<mat.length && ynew>=0 && ynew<mat[0].length && mat[xnew][ynew]==1){
                  mat[xnew][ynew]=-1*lvl;
                    int[] temp={xnew, ynew};
                    qu.add(temp);
                }
            }
        }


    //1162. As Far from Land as Possible
    public int maxDistance(int[][] grid) {
        Queue<int[]> qu=new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                   int[] temp={i,j};
                    qu.add(temp);
                }
            }
        }
        // if(qu.size()==)
        int level=-1;
        while(qu.size()>0){
            level++;
            int sz=qu.size();
            while(sz-- >0){
                int[] pair=qu.remove();
                markNeighbour(grid, pair[0], pair[1], qu);
            }
            
        }
        return level==0?-1:level;
    }
    static int[] xdir={-1,0,1,0};
    static int[] ydir={0,-1,0,1};
    public void markNeighbour(int[][] grid, int x, int y, Queue<int[]> qu){
        for(int d=0;d<4;d++){
            int xnew=x+xdir[d];
            int ynew=y+ydir[d];
            
            if(xnew>=0 && xnew<grid.length && ynew>=0 && ynew<grid[0].length && grid[xnew][ynew]==0){
                grid[xnew][ynew]=1;
                int[] temp={xnew,ynew};
                qu.add(temp);
            }
        }
    }


    static int time=0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // making graph
        if(connections.size()==0 || connections.size()==1 || connections.size()==2){
            return connections;
        }
        time=-1;
        List<Integer>[] graph=new ArrayList[n];
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<n;i++){
            graph[i]=new ArrayList<>();
        }
        for(List<Integer> list:connections){
            graph[list.get(0)].add(list.get(1));
            graph[list.get(1)].add(list.get(0));
        }
        int[] parent=new int[n];
        boolean[] arti=new boolean[n];
        int[] disc=new int[n];
        int[] low=new int[n];
        boolean[] vis=new boolean[n];
        parent[0]=-1;
        Articulation_dfs(0,graph, parent, arti, disc, low, vis,res);
        int count=0;
        // for(boolean val:arti){
        //     if(val==true){
        //         count++;
        //     }
        // }
        return res;
    }
    public void Articulation_dfs(int src, List<Integer>[] graph, int[] parent, boolean[] arti, int[] disc, int[] low, boolean[] vis, List<List<Integer>> res){
            int count=0;
            time++;
            disc[src]=time;
            low[src]=time;
            vis[src]=true;
            for(int nbr:graph[src]){
                if(nbr==parent[src]){
                    continue;
                }
                else if(vis[nbr]==true){
                    low[src]=Math.min(low[src], disc[nbr]);
                }
                else{
                    parent[nbr]=src;
                    Articulation_dfs(nbr, graph, parent, arti, disc, low, vis, res);
                    count++;
                    low[src]=Math.min(low[src],low[nbr]);
                    // if(parent[src]==-1){
                    //     if(count>1){
                    //         ArrayList<Integer> temp=new ArrayList<>();
                    //         temp.add(src);
                    //         temp.add(nbr);
                    //         res.add(temp);
                    //     }
                    // }
                    // else{
                        if(low[nbr]>disc[src]){
                            ArrayList<Integer> temp=new ArrayList<>();
                            temp.add(src);
                            temp.add(nbr);
                            res.add(temp);
                        }
                    // }    
                }
            }
    }


    



    public static void main(String[] args){
        System.out.println("Pradeep");
    }
}