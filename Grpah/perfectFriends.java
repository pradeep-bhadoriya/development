import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthStyleFactory;

public class perfectFriends {
    public static void main(String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int k=Integer.parseInt(br.readLine());

        ArrayList<Integer>[] graph=new ArrayList[n];
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();
        }
        for(int i=0;i<k;i++){
            String s=br.readLine();
            System.out.println(s);
            int src=Integer.parseInt(s.split(" ")[0]);
            int nbr=Integer.parseInt(s.split(" ")[1]);

            graph[src].add(nbr);
            graph[nbr].add(src);
        }

        countPerfectFriends(graph);
        // System.out.println(count);
    }

    public static void GCCforPerfectFriends(ArrayList<Integer>[] graph,int src,boolean[] vis,ArrayList<Integer> l2) {
        vis[src]=true;
        l2.add(src);
        for(Integer nbr:graph[src]){
            if(vis[nbr]==false){
                GCCforPerfectFriends(graph, nbr, vis, l2);
            }
        }
    }

    public static void countPerfectFriends(ArrayList<Integer>[] graph) {
        boolean[] vis=new boolean[graph.length];
        // int count1=0;
        ArrayList<Integer> arl=new ArrayList<>();
        for(int i=0;i<graph.length;i++){
            ArrayList<Integer> l2=new ArrayList<>();
            if(vis[i]==false){
                GCCforPerfectFriends(graph,i,vis,l2);
                arl.add(l2.size());
            }
        }
        System.out.println(arl);
        int prod=0;
        int sum=arl.get(arl.size()-1);
        for(int i=arl.size()-2;i>=0;i--){
            prod+=arl.get(i)*sum;
            sum+=arl.get(i);
        }
        System.out.println(prod);

    }
}
