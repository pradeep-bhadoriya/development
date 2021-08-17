public class doublyLinkedList {
    private class Node {
        int data;
        Node next;
        Node prev;

        public Node() {
            this.data = 0;
            this.next = this.prev = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = this.prev = null;
        }

        public Node(int data, Node next, Node prev) {
            this.data = 0;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public void addFirst(int data) {
        if (this.size == 0) {
            this.head = this.tail = new Node(data);
        } else {
            Node temp = tail;
            tail.next = new Node(data);
            tail = tail.next;
            tail.prev = temp;
        }
        this.size++;
    }

    public void addLast(int data) {
        if (this.head == null) {
            this.head = new Node(data);
        } else {
            Node temp = head;
            head.prev = new Node(data);
            head = head.prev;
            head.next = temp;
        }
        this.size++;
    }

    public void addAt(int idx, int data) {
        if (idx < 0 || idx > this.size) {
            System.out.println("IndexIsInValid: -1");
            // return ;
        } else if (idx == 0) {
            addFirst(data);
        } else if (idx == this.size) {
            addLast(data);
        } else {
            Node itr = head;
            while (idx > 1) {
                itr = itr.next;
                idx--;
            }
            Node temp1 = itr;
            Node temp2 = itr.next;
            itr.next = new Node(data);
            itr = itr.next;
            itr.prev = temp1;
            itr.next = temp2;
            temp2.prev = itr;
            this.size++;
        }
    }

    public int size() {
        return this.size;
    }

    public int getFirst() {
        return this.head.data;
    }

    public int getLast() {
        return this.tail.data;
    }

    public int getAt(int pos) {
        if (pos < 0 || pos > this.size) {
            return -1;
        } else if (pos == 0) {
            return getFirst();
        } else if (pos == this.size) {
            return getLast();
        } else {
            Node itr = this.head;
            while (pos > 0) {
                itr = itr.next;
                pos--;
            }
            return itr.data;
        }
    }

    public Node getNodeAt(int idx){
        if (pos < 0 || pos > this.size) {
            return null;
        } else if (pos == 0) {
            return this.head;
        } else if (pos == this.size) {
            return this.tail;
        } else {
            Node itr = this.head;
            while (pos > 0) {
                itr = itr.next;
                pos--;
            }
            return itr;
        }
    }

    public void displayForw() {
        // write your code
        Node itr = this.head;
          System.out.print("[");
          while (itr.next != null) {
              System.out.print(itr.data + ", ");
              itr=itr.next;
          }
          if (itr != null) {
              System.out.print(itr.data);
          }
          System.out.println("]");
  
      }
  
      public void displayBack() {
        // write your code
        Node itr = this.tail;
          System.out.print("[");
          while (itr.prev != null) {
              System.out.print(itr.data + ", ");
              itr=itr.prev;
          }
          if (itr != null) {
              System.out.print(itr.data);
          }
          System.out.println("]");
      }

    private boolean ListIsEmptyException() {
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return true;
        }
        return false;
    }

    public int removeFirst() {
        if (ListIsEmptyException()) {
            return -1;
        } else if (this.size == 1) {
            int data = this.head.data;
            this.head = null;
            this.size--;
            return data;
        } else {
            int data = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return data;
        }
    }

    public int removeLast() {
        if (ListIsEmptyException()) {
            return -1;
        } else if (this.size == 1) {
            int data = this.head.data;
            this.head = this.tail=null;
            this.size--;
            return data;
        } else {
            int data = this.tail.data;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return data;
        }
    }

    public int removeAt(int idx){
        if (ListIsEmptyException()) {
            return -1;
        } else if (this.size == 1) {
            int data = this.head.data;
            this.head = this.tail=null;
            this.size--;
            return data;
        } else {
            Node node=getNodeAt(idx);
            Node pre=getNodeAt(idx-1);
            Node nex=getNodeAt(idx+1);
            pre.next=nex;
            nex.prev=pre;
            return node.data;
        }
    }

}
