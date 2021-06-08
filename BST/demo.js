static sum=0;
public static void rwsol(Node node){
    // write your code here
    if(node==null){
        return;
    }
    // string=""
    rwsol(node.right);
    node.data=sum;
    sum+=

}