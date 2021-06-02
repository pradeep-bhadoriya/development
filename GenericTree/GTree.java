import java.util.*;

public class GTree {
    public static class Node {
        int data;
        ArrayList<Node> children;

        public Node() {
            this.data = 0;
            this.children = new ArrayList<>();
        }

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = null;
        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            int data = arr[i];
            Node nn = new Node(data);
            if (arr[i] != null) {
                if (st.size() == 0) {
                    root = nn;
                    st.push(nn);
                } else {
                    st.peek().children.add(nn);
                    st.push(nn);
                }
            } else {
                st.pop();
            }
        }
        return root;
    }

    public void display(Node root) {
        // base case

        // my work
        System.out.print("[" + root.data + "]" + " -> ");
        for (Node child : root.children) {
            System.out.print(child.data + " ,");
        }
        System.out.println(" .");
        // faith
        for (int i = 0; i < root.children.size(); i++) {
            display(root.children.get(i));
        }

    }

    public int size(Node root) {
        // base case

        // my work
        int size = 0;
        // faith
        for (Node child : root.children) {
            size += size(child);
        }
        return 1 + size;
    }

    public int Maximum(Node root) {
        // base case
        // mywork
        // faith
        int max = Integer.MIN_VALUE;
        for (Node child : root.children) {
            max = Math.max(max, Maximum(child));
        }
        return Math.max(max, root.data);
    }

    public void Traversal(Node node) {
        // Pre-order
        System.out.println("Node Pre -> " + node.data);
        for (Node child : node.children) {
            System.out.println("Edge Pre -> " + node.data + "-" + child.data);
            Traversal(child);
            System.out.println("Edge Post -> " + node.data + "-" + child.data);
        }
        System.out.println("Node Post -> " + node.data);

        // In-order

        // Post-order
    }

    public void LevelOrderTraversal(Node node) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while (qu.size() > 0) {
            Node temp = qu.remove();
            System.out.print(temp.data + " ");
            for (Node child : temp.children) {
                qu.add(child);
            }
        }
        System.out.println(".");
    }

    public void LevelOrderLineWise2Queue(Node node) {
        Queue<Node> mainQ = new ArrayDeque<>();
        Queue<Node> helperQ = new ArrayDeque<>();
        mainQ.add(node);
        while (mainQ.size() > 0) {
            while (mainQ.size() > 0) {
                Node temp = mainQ.remove();
                System.out.println(temp.data + " ");
                for (Node child : temp.children) {
                    helperQ.add(child);
                }
                Queue<Node> swap = mainQ;
                mainQ = helperQ;
                helperQ = swap;
            }
            System.out.println();
        }
    }

    public void LevelOrderLineWiseDelimiter(Node node) {
        Queue<Node> mainQ = new LinkedList<>();// creating queue using linked list because queue not support adding null
                                               // value
        // Queue<Node> helperQ=new ArrayDeque<>();
        mainQ.add(node);
        mainQ.add(null);
        // System.out.print("pradeep");
        while (mainQ.peek() != null) {
            Node temp = mainQ.peek();
            for (Node child : temp.children) {
                mainQ.add(child);
            }
            mainQ.remove();
            System.out.print(temp.data + " ");
            if (mainQ.peek() == null) {
                System.out.println();
                mainQ.remove();
                mainQ.add(null);
            }
        }
    }

    public void LevelOrderLineWise3(Node node) {
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while (qu.size() > 0) {
            int size = qu.size();
            while (size-- > 0) {
                Node temp = qu.remove();
                for (int i = 0; i < temp.children.size(); i++) {
                    qu.add(temp.children.get(i));
                }
                System.out.println(temp.data + " ");
            }
            System.out.println();
        }
    }

    public void LevelOrderZigZag(Node node) {
        Stack<Node> mainS = new Stack<>();
        Stack<Node> helperS = new Stack<>();
        mainS.push(node);
        int level = 1;
        while (mainS.size() > 0) {
            while (mainS.size() > 0) {
                if (level % 2 == 1) {
                    for (Node child : node.children) {
                        helperS.push(child);
                    }
                    System.out.print(mainS.peek().data);
                    mainS.pop();
                } else {
                    for (int i = node.children.size() - 1; i >= 0; i--) {
                        helperS.push(node.children.get(i));
                    }
                    System.out.print(mainS.peek().data);
                    mainS.pop();
                }
            }
            System.out.println();
            Stack<Node> temp = mainS;
            mainS = helperS;
            helperS = temp;
        }
    }

    public void mirror(Node node) {
        // base case

        // faith-call children and ask them to mirror
        for (Node child : node.children) {
            mirror(child);
        }
        // my work- Now I will reverse current level children
        int left = 0;
        int right = node.children.size() - 1;
        while (left < right) {
            Node temp = node.children.get(left);
            node.children.set(left, node.children.get(right));
            node.children.set(right, temp);
            left++;
            right--;
        }
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        // write your code here
        // base case
        if (node.data == data) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(node.data);
            return temp;
        }
        // faith
        // boolean res=false;
        ArrayList<Integer> ans = new ArrayList<>();
        for (Node child : node.children) {
            ans = nodeToRootPath(child, data);
            if (ans.size() > 0) {
                ans.add(node.data);
                return ans;
            }
        }
        return ans;
    }

    public static int nearestCommonAncester(Node node, int d1, int d2) {
        // write your code here
        ArrayList<Integer> ntrp1 = nodeToRootPath(node, d1);
        ArrayList<Integer> ntrp2 = nodeToRootPath(node, d2);

        int i = ntrp1.size() - 1;
        int j = ntrp1.size() - 1;
        res = -1;
        while (i > 0 && j > 0) {
            if (ntrp1.get(i) != ntrp2.get(j))
                break;
            else
                i--;
            j--;
        }
    }

    public static boolean isMirror(Node node1, Node node2) {
        // base case
        if (nod1.children.size() != node2.children.size()) {
            return false;
        }
        // faith
        boolean res = true;
        int i = 0;
        int j = node2.size() - 1;
        while (i <= j) {
            res = isMirror(node1.children.get(i), node2.children.get(j));
            if (res == false) {
                return false;
            }
            i++;
            j--;
        }
        return res;
    }

    public static boolean isSymmetric(Node node) {
        return isMirror(node, node);// A tree is symmetric if and only if it is mirror of itself.
    }

    static Node predecessor;
    static Node successor;
    static int count = 0;

    public static void predecessorAndSuccessor(Node node, int data) {
        // write your code here
        if (node.data == data) {
            count++;
            return;
        } else if (count == 1) {
            count++;
            successor = node;
            return;
        }
        predecessor = node;
        for (Node child : node.children) {
            if (count != 2) {
                predecessorAndSuccessor(child, data);
            }
        }
    }

    static int ceil = Integer.MIN_VALUE;
    static int floor = Integer.MAX_VALUE;

    public static void ceilAndFloor(Node node, int data) {
        // Write your code here
        if (node.data < data) {
            floor = Math.max(floor, node.data);
        } else if (node.data > data) {
            ceil = Math.min(ceil, node.data);
        }
        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    public static int KthLargest(Node node, int k) {
        if (node.data < floor) {
            if (node.data > ceil) {
                ceil = node.data;
            }
        }
        for (Node child : node.children) {
            LthLargest(child, k);
        }
    }

    public static void fun() {
        Integer[] data = { 1, 2 };
        Node root = construct(data);
        System.out.print(root);

    }

    public static void main(String[] args) {
        fun();
    }
}
