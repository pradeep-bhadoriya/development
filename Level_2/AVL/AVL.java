public class AVL {
    public static class Node{
        int data;
        Node left;
        Node right;

        int balance;
        int height;

        public Node(int data){
            this.data=data;
        }
    }

    public static void heightAndBalance(Node node){
        int lh=node.left==null?-1:node.left.height;
        int rh=node.right==null?-1:node.right.height;

        node.balance=lh-rh;
        node.height=Math.max(lh , rh)+1;
    }

    public static Node rightRotation(Node node){

    }

    public static Node leftRotation(Node A){
        Node B=A.right;
        A.right=b.left;
        b.left=A;
        return B;
    }

    public static Node getRotation(Node node){
        update heightAndBalance(node);

        if(node.balance==2){
            if(node.left.balance==1){
                // call right rotation
                rightRotation(node);
            }
            else if(node.left.balance==-1){

            }
        }
        else if(node.balance==-2){
            if(node.left.balance==1){
                // call left rotation
                leftRotation(node);
            }
            else if(node.left.balance==-1){

            }
        }

    }
}
