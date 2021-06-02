import java.io.*;
import java.util.*;

class linkedList {
    private class Node {
        private int data;
        private Node next;

        public Node() {
            this.data = 0;
            this.next = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public linkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private void handleAddWhenSize0(int val) {
        Node tempNode = new Node(val);
        head = tail = tempNode;
        this.size++;
    }

    public void addFirst(int val) {
        if (this.size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node tempNode = new Node(val);
            tempNode.next = head;
            this.head = tempNode;
            this.size++;
        }
    }

    public void addLast(int val) {
        if (this.size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node tempNode = new Node(val);
            this.tail.next = tempNode;
            this.tail = tempNode;
            this.size++;
        }
    }

    private Node getNthNode(int indx) {
        Node tempNode = this.head;
        for (int i = 0; i < indx; i++) {
            tempNode = tempNode.next;
        }
        return tempNode;
    }

    public void addAt(int val, int indx) {
        if (indx < 0 || indx > this.size) {
            System.out.println("Invalid Position");
        } else if (indx == 0) {
            addFirst(val);
        } else if (indx == this.size) {
            addLast(val);
        } else {
            Node tempNode = getNthNode(indx - 1);
            Node newNode = new Node(val);
            newNode.next = tempNode.next;
            tempNode.next = newNode;
            this.size++;
        }
    }

    public int getFirst() {
        if (this.size == 0) {
            return -1;
        } else {
            return this.head.data;
        }
    }

    public int getLast() {
        if (this.size == 0) {
            return -1;
        } else {
            return this.tail.data;
        }
    }

    public int getAt(int indx) {
        if (indx < 0 || indx > this.size) {
            return -1;
        } else if (indx == 0) {
            return getFirst();
        } else if (indx == this.size) {
            return getLast();
        } else {
            Node tempNode = getNthNode(indx - 1);
            return tempNode.next.data;
        }
    }

    public void display() {
        Node temp = this.head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null ");
    }

    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = null;
        this.tail = null;
        this.size--;
        return val;
    }

    public int removeFirst() {
        if (this.size == 0) {
            return -1;
        } else if (this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if (this.size == 0) {
            return -1;
        } else if (this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            Node tempNode = getNthNode(this.size - 2);
            int val = this.tail.data;
            tempNode.next = null;
            this.tail = tempNode;

            this.size--;
            return val;
        }
    }

    public int removeAt(int indx) {
        if (indx < 0 || indx >= this.size) {
            return -1;
        } else if (indx == 0) {
            return removeFirst();
        } else if (indx == this.size - 1) {
            return removeLast();
        } else {
            Node tempNode = getNthNode(indx - 1);
            int val = tempNode.next.data;
            tempNode.next = tempNode.next.next;
            // this.tail = tempNode;

            this.size--;
            return val;
        }
    }

    public int size() {
        return this.size;
    }

    // ----------------------Input Management---------
    public void reverseDI() {
        // write your code here
        int left = 0;
        int right = this.size - 1;
        while (left < right) {
            Node lnode = getNthNode(left);
            Node rnode = getNthNode(right);
            int temp = lnode.data;
            lnode.data = rnode.data;
            rnode.data = temp;

            left++;
            right--;
        }
    }

    public void reversePI() {
        // write your code here
        Node prev = null;
        Node cur = this.head;
        while (cur != null) {
            Node tempnext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = tempnext;
        }
        Node temp = head;
        head = tail;
        tail = temp;
    }

    public linkedList mergedwoSortedLinkedList(linkedList l1, linkedList l2) {
        linkedList l = new linkedList();
        l.addFirst(-1);
        System.out.print("pradeep");
        Node dummy = l1.head;
        dummy.data = -1;
        Node temp1 = l1.head;
        Node temp2 = l2.head;
        Node itr = dummy;
        while (temp1 != null && temp2 != null) {
            if (temp1.data > temp2.data) {
                itr.next = temp2;
                itr = temp2;
                temp2 = temp2.next;

            } else {
                itr.next = temp1;
                itr = temp1;
                temp1 = temp1.next;
            }
        }
        while (temp1 != null) {
            itr.next = temp1;
            itr = itr.next;
            temp1 = temp1.next;

        }
        while (temp2 != null) {
            itr.next = temp2;
            itr = itr.next;
            temp2 = temp2.next;
        }

        l.head = dummy.next;
        l.tail = dummy.next;
        l.size = 10;
        return l;
    }

    public linkedList mergeTwo(linkedList l1) {
        linkedList res = mergedwoSortedLinkedList(l1, this);
        return res;
    }

    public void kReverse(int k) {
        linkedList prev = null;
        while (this.size() > 0) {
            linkedList cur = new linkedList();
            if (this.size() >= k) {
                for (int i = 0; i < k; i++) {
                    cur.addFirst(this.getFirst());
                    this.removeFirst();
                }
            } else {
                while (this.size > 0) {
                    cur.addLast(this.getFirst());
                    this.removeFirst();
                }
            }
            if (prev == null) {
                prev = cur;
            } else {
                prev.tail.next = cur.head;
                prev.tail = cur.tail;
                prev.size += cur.size;
            }
        }
        this.head = prev.head;
        this.tail = prev.head;
        this.size = prev.size;
    }

    public void displayReverseHelper(Node node) {
        if (node == null) {
            return;
        }
        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }

    public void displayReversePointerRecursive(Node node) {
        // base case
        if (node.next == null) {
            // node.next.next=node;
            return;
        }
        // faith
        displayReversePointerRecursive(node.next);
        node.next.next = node;
    }

}

public class creation {
    public static void main(String[] args) {
        linkedList list1 = new linkedList();
        list1.addLast(1);
        list1.addLast(10);
        list1.addLast(20);
        list1.addLast(30);
        list1.addLast(40);
        list1.addLast(50);
        list1.display();
        linkedList list2 = new linkedList();
        list2.addLast(5);
        list2.addLast(15);
        list2.addLast(50);
        list2.addLast(60);
        list2.display();
        // list2.addLast(55);
        // linkedList list3;
        // list3 = list1.mergeTwo(list2);
        // list3.display();
        list1.kReverse(3);
        list1.display();

    }
}