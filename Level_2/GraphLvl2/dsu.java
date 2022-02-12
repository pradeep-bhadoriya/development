import java.util.*;

public class dsu {
    public static int find(int[] parent, int val){
        if(parent[val]==val){
            return val;
        }
        parent[val]=find(parent, parent[val]);
        return parent[val];
    }

    public static int union(int[][] pairs, int n){
        int[] parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
        int[] rank=new int[n];
        for(int[] pair:pairs){
            int val1=pair[0];
            int val2=pair[1];
            int slov1=find(parent, val1);
            int slov2=find(parent, val2);
            if(rank[slov1]>rank[slov2]){
                parent[slov2]=slov1;
            }
            else if(rank[slov2]>rank[slov1]){
                parent[slov1]=slov2;
            }
            else{
                parent[slov1]=slov2;
                rank[slov2]++;
            }
        }
        int count=0;
        for(int i=0;i<n;i++){
            if(i==parent[i])
                count++;
        }
        return count;
    }

    public static void main(String[] args){
        int[][] pairs = {
            {1, 2},
            {3, 4},
            {2, 3},
            {1, 4},
            {5, 6},
            {7, 8},
            {5, 7},
            {9, 10},
            {1, 3},
            {1, 5}
        };

        int n=11;
        int count=union(pairs, n);
        System.out.println(count);
    }    
}
