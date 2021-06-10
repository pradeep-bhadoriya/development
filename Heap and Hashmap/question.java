import java.util.PriorityQueue;

public class question {
    public static void printKLargest(int[] arr,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        // add k element
        for(int i=0;i<k;i++){
            pq.add(arr[i]);
        }
        //process remaining element
        for(int i=k;i<arr.length;i++){
            if(arr[i]>pq.peek()){
                pq.remove();
                pq.add(arr[i]);
            }
        }
        //print by removing 
        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }

    

    public static void Question(){
        int[] arr={10,20,9,30,1,15,42,100,45};
        printKLargest(arr,3);
    }

    // public static void demo(){
    //     //default priority -> min
    //     PriorityQueue<Integer> pq=new PriorityQueue<>();
    //     pq.add(10);
    //     pq.add(20);
    //     pq.add(9);
    //     pq.add(10);
    //     pq.add(10);
    //     // max priority
    //     PriorityQueue<Integer> pq1=new PriorityQueue<>(Collections.reverseOrder());
    // }
    public static void main(String[] args){
        // demo();
        Question();
    }
}
