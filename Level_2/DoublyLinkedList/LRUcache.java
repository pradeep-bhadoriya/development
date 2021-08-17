import java.util.*;
public class LRUcache{
    private static class LRUCache{
        class Node{
            int key;
            int data;
            Node next;
            Node prev;

            public Node(int key , int data){
                this.key=key;
                this.data=data;
                this.next=this.prev=null;
            }
        }

        private Node head;
        private Node tail;
        private int capacity;
        private int size;
        private HashMap<Integer,Node> hm;

        public void removeNodeAt(Node node){
            if(this.size==1){
                this.head=this.tail=null;
                this.size--;
            }
            else if(node==this.head){
                this.removeLast();
            }
            else if(node==this.tail){
                this.tail=this.tail.prev;
                this.tail.next=null;
                this.size--;
            }
            else{
                // System.out.println("head "+this.head);
                // System.out.println("tail "+this.tail);
                // System.out.println("node " +node.key);

                Node pre=node.prev;
                // System.out.println("pre "+ node.prev);
                Node nex=node.next;
                // System.out.println("nex "+nex);
                pre.next=nex;
                nex.prev=pre;
                this.size--;
            }
            
        }

        public void removeLast(){
            Node rem=this.head;
            if(this.size==1){
                this.head=this.tail=null;
                this.size--;
            }
            else{
                // System.out.println("removing "+this.head.data);
                this.head=this.head.next;
                this.head.prev.next=null;
                this.head.prev=null;
                this.size--;
            }
            this.hm.remove(rem.key);
        }

        public Node addFirst(int key , int value){
            // System.out.println(this.size);
            // System.out.println("key "+key);
            if(this.size==0){
                this.head=this.tail=new Node(key , value);
                this.size++;
                return head;
            }
            else{
                this.tail.next=new Node(key , value);
                this.tail.next.prev=tail;
                this.tail=this.tail.next;
                this.size++;
                return tail;
            }
        }

        public LRUCache(int capacity) {
            this.capacity=capacity;
            this.hm=new HashMap<>();
            System.out.println("Inside constructor");
        }
        
        public int get(int key) {
            if(this.hm.containsKey(key)==false){
                return -1;
            }
            else{
                // System.out.println("hm "+this.hm.get(key).data);
                Node val=this.hm.get(key);
                this.put(key , val.data);
                return val.data;
            }
        }
        
        public void put(int key, int value) {
            if(this.hm.containsKey(key)){
                // this.hm.put(key).data=value;
                // System.out.println("hm.getkey "+hm.get(key).data);
                this.removeNodeAt(hm.get(key));
                Node node=this.addFirst(key , value);
                this.hm.put(key , node);
            }
            else{
                Node node=this.addFirst(key , value);
                if(this.size>this.capacity){
                    this.removeLast();
                }
                this.hm.put(key , node);
            }
        }
    }
    public static void main(String[] args) {
        // LRUCache lru=new LRUCache(2);
        // System.out.println(lru.capacity);
        // // System.out.println(lru.capacity);
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));     // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));     // return -1 (not found)
        System.out.println(lRUCache.get(3));     // return 3
        System.out.println(lRUCache.get(4));     // return 4
    }
}

