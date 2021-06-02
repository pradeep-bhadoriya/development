import java.util.*;

public class BinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node() {

        }

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

    }

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer arr[]) {
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));
        int indx = 0;
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 0) {
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn, 0));
                }

                p.state += 1;

            } else if (p.state == 1) {
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state += 1;

            } else {
                st.pop();
            }
        }

        return root;

    }

    public static void display(Node root) {
        // faith
        if (root == null) {
            return;
        }
        // base case
        System.out.println((root.left == null ? "." : root.left.data) + " <- " + root.data + " -> "
                + (root.right == null ? "." : root.right.data));
        display(root.left);
        display(root.right);
    }

    public static int size(Node node) {
        // write your code here
        // base case
        // if (node.left == null && node.right == null) {
        // return 1;
        // }
        // faith
        int leftsize = 0;
        int rightsize = 0;
        if (node.left != null)
            leftsize = size(node.left);
        if (node.right != null)
            rightsize = size(node.right);

        return leftsize + rightsize + 1;
    }

    public static int sum(Node node) {
        // write your code here
        // base case
        // if (node.left == null && node.right == null) {
        // return node.data;
        // }
        // faith
        int leftsum = 0;
        int rightsum = 0;
        if (node.left != null)
            leftsum = sum(node.left);
        if (node.right != null)
            rightsum = sum(node.right);

        return leftsum + rightsum + node.data;

    }

    public static int max(Node node) {
        // write your code here
        // base case
        // faith
        int leftmax = Integer.MIN_VALUE;
        int rightmax = Integer.MIN_VALUE;
        if (node.left != null)
            leftmax = Math.max(leftmax, max(node.left));
        if (node.right != null)
            rightmax = Math.max(rightmax, max(node.right));

        return Math.max(node.data, Math.max(leftmax, rightmax));
    }

    public static int height(Node node) {
        // write your code here
        // base case
        // faith
        int leftheight = -1;
        int rightheight = -1;
        if (node.left != null)
            leftheight = Math.max(leftheight, height(node.left));
        if (node.right != null)
            rightheight = Math.max(rightheight, height(node.right));

        return 1 + Math.max(leftheight, rightheight);
    }

    public static void levelOrder(Node node) {
        // write your code here
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                Node temp = qu.remove();
                System.out.print(temp.data + " ");
                if (temp.left != null)
                    qu.add(temp.left);
                if (temp.right != null)
                    qu.add(temp.right);
            }
            System.out.println();
        }
    }

    public static void preOrder(Node node) {
        // base case
        // my work
        System.out.print(node.data + " ");
        // faith
        if (node.left != null)
            preOrder(node.left);
        if (node.right != null)
            preOrder(node.right);

    }

    public static void inOrder(Node node) {
        // base case
        // my work

        // faith
        if (node.left != null) {
            inOrder(node.left);
        }
        System.out.print(node.data + " ");
        if (node.right != null) {
            inOrder(node.right);
        }

    }

    public static void postOrder(Node node) {
        // base case
        // my work
        // faith
        if (node.left != null)
            postOrder(node.left);
        if (node.right != null)
            postOrder(node.right);
        System.out.print(node.data + " ");

    }

    public static void iterativePrePostInTraversal(Node node) {
        // write your code here
        ArrayList<Node> pre = new ArrayList<>();
        ArrayList<Node> in = new ArrayList<>();
        ArrayList<Node> post = new ArrayList<>();
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 0));
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 0) {
                pre.add(top.node);
                top.state++;
                if (top.node.left != null) {
                    st.push(new Pair(top.node.left, 0));
                }
            } else if (top.state == 1) {
                in.add(top.node);
                top.state++;
                if (top.node.right != null) {
                    st.push(new Pair(top.node.right, 0));
                }
            } else {
                post.add(top.node);
                st.pop();
            }
        }

        for (Node n : pre) {
            System.out.print("" + n.data + " ");
        }
        System.out.println();
        for (Node n : in) {
            System.out.print("" + n.data + " ");
        }
        System.out.println();
        for (Node n : post) {
            System.out.print("" + n.data + " ");
        }

    }

    public static boolean find(Node node, int data) {
        // write your code here
        // base case
        if (node.data == data)
            return true;
        // faith
        boolean ans = false;
        if (node.left != null) {
            ans = find(node.left, data);
            if (ans == true)
                return true;
        }
        if (node.right != null) {
            ans = find(node.right, data);
            if (ans == true)
                return true;
        }
        return ans;
    }

    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        // write your code here
        // base case
        if (node.data == data) {
            ArrayList<Node> tempres = new ArrayList<>();
            // tempres.add(node.data);
            tempres.add(node);
            return tempres;
        }
        // faith
        ArrayList<Node> ans = new ArrayList<>();
        if (node.left != null) {
            ans = nodeToRootPath(node.left, data);
            if (ans.size() > 0) {
                // ans.add(node.data);
                ans.add(node);
                return ans;
            }
        }
        if (node.right != null) {
            ans = nodeToRootPath(node.right, data);
            if (ans.size() > 0) {
                // ans.add(node.data);
                ans.add(node);
                return ans;
            }
        }
        return ans;
    }

    public static void printKLevelsDownIterattive(Node node, int k) {
        // write your code here
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(node);
        int level = 0;
        while (qu.size() > 0) {
            int sz = qu.size();
            while (sz-- > 0) {
                Node tempNode = qu.peek();
                if (level == k) {
                    if (tempNode.left != null)
                        qu.add(tempNode.left);
                    if (tempNode.right != null)
                        qu.add(tempNode.right);
                    System.out.print(qu.remove().data + " ");
                } else {
                    if (tempNode.left != null)
                        qu.add(tempNode.left);
                    if (tempNode.right != null)
                        qu.add(tempNode.right);
                    qu.remove();
                }
            }
            level++;
        }
    }

    public static void printKLevelsDownRecursive(Node node, int k) {
        // base case
        if (node == null)
            return;
        else if (k == 0) {
            System.out.print(node.data + " ");
            return;
        }
        // faith
        printKLevelsDownRecursive(node.left, k - 1);
        printKLevelsDownRecursive(node.right, k - 1);
    }

    public static void printKLevelDownForKNodeSoFar(Node node, int k, Node prev) {
        // base case
        if (node == null || node == prev)
            return;
        else if (k == 0) {
            System.out.print(node.data + " ");
            return;
        }

        // faith
        printKLevelDownForKNodeSoFar(node.left, k - 1, prev);
        printKLevelDownForKNodeSoFar(node.right, k - 1, prev);
    }

    public static void printKNodesFar(Node node, int data, int k) {
        // write your code here
        ArrayList<Node> list = nodeToRootPath(node, data);
        // for (Node tempnode : list) {
        // System.out.println(tempnode.data);
        // }
        // System.out.println("====================================");
        // System.out.print(list);
        Node prev = null;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0)
                printKLevelDownForKNodeSoFar(list.get(i), k, prev);
            else
                printKLevelDownForKNodeSoFar(list.get(i), k, prev);
            k--;
            prev = list.get(i);
            // System.out.println(prev.data);
        }
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        // write your code here
        // base case
        if (node == null) {
            return;
        }
        if (sum >= lo && sum <= hi) {
            System.out.println(path);
            return;
        }
        // faith
        pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
        pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
    }

    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(arr);
        System.out.println("PradeepDon");
        display(root);
        // int size = height(root);
        // System.out.println(size);
        // levelOrder(root);
        // preOrder(root);
        // System.out.println();
        // inOrder(root);
        // System.out.println();
        // postOrder(root);
        // System.out.println();
        // System.out.println(find(root, 70));
        // System.out.println();
        // System.out.println(find(root, 70));
        // ArrayList<Integer> ans = nodeToRootPath(root, 70);
        // System.out.println(ans);
        printKNodesFar(root, 87, 3);
        // pathToLeafFromRoot(root, "", 0, 70, 150);
        // iterativePrePostInTraversal(root);
        // printKLevelsDownRecursive(root, 0);

    }

    public static void main(String[] args) {
        fun();
    }
}
