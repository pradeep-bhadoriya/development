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

    public static void main(String[] args){
        System.out.println("Pradeep");
    }
}