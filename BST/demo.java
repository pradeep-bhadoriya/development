import java.util.*;

public class demo {
    static int sum = 0;

    public static void rwsol(Node node) {
        // write your code here
        if (node == null) {
            return;
        }
        // string=""
        rwsol(node.right);
        int val = node.data;
        node.data = sum;
        sum += val;
        rwsol(node.left);
    }

    public static void pir(Node node, int d1, int d2) {
        // write your code here
        if(node==null) return ;
        else if(node.data>=d1 && node.data<=d2){
            pir(node.left,d1,d2);
            System.out.println(node.data);//Inorder to follow ascending order.
            pir(node.right,d1,d2);
        }
        else if(node.data<d1){
            pir(node.right,d1,d2);
        }
        else{
            pir(node.left,d1,d2);
        }
    }

    public static boolean findSupplement(Node node,int data){
        if(node==null) return fasle;
        else if(node.data==data) return true;
        else if(node.data<data) return findSupplement(node.right, data);
        else if(node.data>data) return findSupplement(node.left, data);
    }

    public static void printTargetSumPair1(Node node, Node root,int target){
        if(node==null) return ;
        int n1=node.data;
        int n2=target-n1;
        if(n2<n1 && findSupplement(n2)){
            System.out.println(n1+" "+n2);;
        }
        printTargetSumPair1(node.left, root, target);
        printTargetSumPair1(node.right, root, target);
    }
    
    public static void printTargetSumPair2(Node node, Node root,int target){// using normal array method. first traverse inoreder then apply two pointer algo.
        if(node==null) return ;
        int n1=node.data;
        int n2=target-n1;
        if(n2<n1 && findSupplement(n2)){
            System.out.println(n1+" "+n2);;
        }
        printTargetSumPair1(node.left, root, target);
        printTargetSumPair1(node.right, root, target);
    }

    public static void printTargetSumPair3(Node node, Node root,int target){
        if(node==null) return ;
        int n1=node.data;
        int n2=target-n1;
        if(n2<n1 && findSupplement(n2)){
            System.out.println(n1+" "+n2);;
        }
        printTargetSumPair1(node.left, root, target);
        printTargetSumPair1(node.right, root, target);
    }


    public static void fun() {

    }

    public static void main(String[] args) {
        fun();
    }
}