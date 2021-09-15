import java.util.*;

public class TressLevel2 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    public TreeNode constructPreIn(int[] preorder, int[] inorder, int preSt, int preEnd, int inSt, int inEnd) {
        // base case
        // if(preSt==preEnd){ work only if tree is complete tree
        // return new TreeNode(preorder[preSt]);
        // }
        if (preSt > preEnd) {
            return null;
        }

        int rootVal = preorder[preSt];

        TreeNode root = new TreeNode(preorder[preSt]);

        int indx = inSt;
        while (inorder[indx] != rootVal) {
            indx++;
        }
        int count = indx - inSt;
        root.left = constructTree(preorder, inorder, preSt + 1, preSt + count, inSt, indx - 1);
        root.right = constructTree(preorder, inorder, preSt + count + 1, preEnd, indx + 1, inEnd);

        return root;
    }

    public TreeNode constructPostIn(int[] postorder, int[] inorder, int postSt, int postEnd, int inSt, int inEnd) {
        // base case
        if (postEnd == -1) {
            return null;
        }

        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int indx = inSt;
        while (inorder[indx] != rootVal) {
            indx++;
        }
        int elementCount = indx - inSt;

        root.left = constructPostIn(postorder, inorder, postSt, postSt + elementCount - 1, inSt,
                inSt + elementCount - 1);
        root.right = constructPostIn(postorder, inorder, post + elementCount, postEnd, inSt + elementCount, inEnd);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode res=constructPostIn(postorder, inorder, postSt, postEnd, inSt, inEnd){
            return res;
        }
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        TreeNode res = buildPrePost(pre, post, 0, pre.length - 1, 0, post.length - 1);
        return res;
    }

    public TreeNode buildPrePost(int[] pre, int[] post, int preSt, int preEnd, int postSt, int postEnd) {
        // base case
        if (preSt == preEnd) {
            return new TreeNode(pre[preSt]);
        }
        if (preSt > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preSt]);
        int indx = postSt;
        while (preSt + 1 < pre.length && post[indx] != pre[preSt + 1]) {
            indx++;
        }
        int elementCount = indx - postSt;
        root.left = buildPrePost(pre, post, preSt + 1, preSt + 1 + elementCount, postSt, postSt + elementCount);
        root.right = buildPrePost(pre, post, preSt + 1 + elementCount + 1, preEnd, postSt + elementCount + 1, postEnd);

        return root;
    }

    public static TreeNode buildInLvl(int[] in, ArrayList<Integer> lvl, int inSt, int inEnd) {
        // base case
        if (lvl.size() == 0) {
            return null;
        }

        TreeNode root = new TreeNode(lvl.get(0));
        int indx = inSt;
        HashSet<Integer> hs = new HashSet<>();
        while (in[indx] != lvl.get(0)) {
            hs.add(in[indx]);
            indx++;
        }
        int elementCount = indx - inSt;
        ArrayList<Integer> lvlLeft = new ArrayList<>();
        ArrayList<Integer> lvlRight = new ArrayList<>();

        for (int i = 1; i < lvl.size(); i++) {
            if (hs.contains(lvl.get(i))) {
                lvlLeft.add(lvl.get(i));
            } else {
                lvlRight.add(lvl.get(i));

            }
        }
        root.left = buildInLvl(in, lvlLeft, inSt, indx - 1);
        root.right = buildInLvl(in, lvlRight, indx + 1, inEnd);

        return root;
    }

    public static TreeNode buildPre(int[] pre, int lo, int hi) {
        // base case
        if (indx >= pre.length || pre[indx] < lo || pre[indx] > hi) {
            return null;
        }

        TreeNode root = new TreeNode(pre[indx]);
        indx++;

        root.left = buildPre(pre, lo, root.val);
        root.right = buildPre(pre, root.val, hi);

        return root;
    }

    static int indx = 0;

    public static TreeNode bstFromPreorder(int[] preorder) {
        indx = 0;
        int hi = Integer.MAX_VALUE;
        int lo = Integer.MIN_VALUE;
        TreeNode res = buildPre(preorder, lo, hi);
        return res;
    }

    public static TreeNode buildPost(int[] post, int lo, int hi) {
        if (indx < 0 || post[indx] < lo || post[indx] > hi) {
            return null;
        }
        TreeNode root = new TreeNode(post[indx]);
        // display(root);
        indx--;
        root.right = buildPost(post, root.val, hi);
        root.left = buildPost(post, lo, root.val);

        return root;
    }

    public static TreeNode bstFromPostorder(int[] preorder) {
        int hi = Integer.MAX_VALUE;
        int lo = Integer.MIN_VALUE;
        indx = preorder.length - 1;
        TreeNode res = buildPost(preorder, lo, hi);
        return res;
    }

    static TreeNode prev = null, cur = null, a = null, b = null;

    public void recoverTree(TreeNode root) {
        prev = null;
        cur = null;
        a = null;
        b = null;
        recoverHelper(root);
        int temp = b.val;
        b.val = a.val;
        a.val = temp;

    }

    public void recoverHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverHelper(root.left);
        if (prev == null) {
            prev = root;
        } else {
            cur = root;
            if (prev.val > cur.val) {
                if (b == null) {
                    a = prev;
                    b = cur;
                } else {
                    b = cur;
                }
            }
            prev = root;
        }

        recoverHelper(root.right);
    }

    public static class Pair {
        TreeNode node;
        int lo;
        int hi;

        public Pair(TreeNode node, int lo, int hi) {
            this.node = node;
            this.lo = lo;
            this.hi = hi;
        }
    }

    public static TreeNode constructBSTFromLevelOrder(int[] LevelOrder) {
        indx = 0;
        Queue<Pair> qu = new LinkedList<>();
        TreeNode root = null;
        qu.add(new Pair(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
        while (indx < LevelOrder.length) {
            Pair rem = qu.remove();

            TreeNode cur = new TreeNode(LevelOrder[indx]);
            if (indx == 0) {
                root = cur;
                qu.add(new Pair(root, rem.lo, root.val));
                qu.add(new Pair(root, root.val, rem.hi));
                indx++;
            } else if (cur.val > rem.lo && cur.val < rem.hi) {
                if (rem.node.val > cur.val) {
                    rem.node.left = cur;
                } else {
                    rem.node.right = cur;
                }
                qu.add(new Pair(cur, rem.lo, cur.val));
                qu.add(new Pair(cur, cur.val, rem.hi));
                indx++;
            }
        }
        return root;
    }

    public static ArrayList<Integer> leftHelper(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while (qu.size() > 0) {
            int sz = qu.size();
            TreeNode temp = qu.peek();
            while (sz > 0) {
                sz = sz - 1;
                TreeNode top = qu.remove();
                if (top.left != null)
                    qu.add(top.left);
                if (top.right != null)
                    qu.add(top.right);
            }
            // System.out.println(top.val);
            list.add(temp.val);
        }
        return list;
    }

    static ArrayList<Integer> list;

    public static ArrayList<Integer> leftView(TreeNode root) {
        list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        rightHelper(root);
        return list;
    }

    public static ArrayList<Integer> rightHelper(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while (qu.size() > 0) {
            int sz = qu.size();
            TreeNode top = qu.peek();
            while (sz > 0) {
                sz = sz - 1;
                top = qu.remove();
                if (top.left != null)
                    qu.add(top.left);
                if (top.right != null)
                    qu.add(top.right);
            }
            // System.out.println(top.val);
            list.add(top.val);
        }
        return list;
    }

    public static ArrayList<Integer> rightView(TreeNode root) {
        list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        rightHelper(root);
        return list;
    }

    public static int camera = 0;

    public int minCameraCover(TreeNode root) {
        camera = 0;
        int res = minCameraHelper(root);
        if (res == 2) {
            camera++;
        }
        return camera;
    }

    public int minCameraHelper(TreeNode root) {
        // state-0->camera present, state-1->cover, state-2->unsafe
        if (root == null) {
            return 1;
        }
        int leftState = minCameraHelper(root.left);
        int rightState = minCameraHelper(root.right);

        if (leftState == 1 && rightState == 1) {
            return 2;
        } else if (leftState == 2 || rightState == 2) {
            camera++;
            return 0;
        } else {
            return 1;
        }
    }

    public class robPair {
        TreeNode node;
        int yes;
        int no;

        public robPair(TreeNode node, int yes, int no) {
            this.node = node;
            this.yes = yes;
            this.no = no;
        }
    }

    public int rob(TreeNode root) {
        robPair res = robHelper(root);
        return Math.max(res.no, res.yes);
    }

    public robPair robHelper(TreeNode root) {
        if (root == null) {
            return new robPair(root, 0, 0);
        }
        robPair left = robHelper(root.left);
        robPair right = robHelper(root.right);

        int yes = root.val + left.no + right.no;
        int no = Math.max(left.yes, left.no) + Math.max(right.yes, right.no);
        return new robPair(root, yes, no);
    }


    
    public static String sHelper(TreeNode root) {
        if (root == null) {
            s = s + "#";
            return s;
        }
        s = s + root.val + "@";
        sHelper(root.left);
        sHelper(root.right);
        return s;
    }
    // Encodes a tree to a single string.
    static String s = "";
    public static String serialize(TreeNode root) {
        s = "";
        String res = sHelper(root);
        // System.out.println(res);
        return res;
    }
    public static TreeNode dHelper(String str) {
        if (idx >= str.length()) {
            return null;
        }
        char ch = str.charAt(idx);
        if (ch != '#') {
            String temps = "";
            while (str.charAt(idx) != '@') {
                temps += str.charAt(idx);
                idx++;
            }
            TreeNode root = new TreeNode(Integer.parseInt(temps));
            idx++;
            root.left = dHelper(str);
            idx++;
            root.right = dHelper(str);
            return root;
        }
        else {
            return null;
        }
    }
    // Decodes your encoded data to tree.
    static int idx;
    public static TreeNode deserialize(String str) {
        idx=0;
        TreeNode root=dHelper(str);
        return root;
    }



    public static int widthHelper(TreeNode root, int val) {
        if (root == null) {

            // System.out.println("hii "+min+" "+ max);
            return 0;
        }
        min = Math.min(min, val);
        max = Math.max(max, val);
        widthHelper(root.left, val - 1);
        widthHelper(root.right, val + 1);
        return 0;
    }

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    public static int width(TreeNode root) {
        int res = widthHelper(root, 0);
        return max - min + 1;
    }

    public static class Pair2 {
        TreeNode node;
        int state;

        public Pair2(TreeNode node, int count) {
            this.node = node;
            this.state = count;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Queue<Pair2> qu = new LinkedList<>();
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        qu.add(new Pair2(root, 0));
        while (qu.size() > 0) {
            int sz = qu.size();
            Pair2 top = qu.remove();
            if (hm.containsKey(top.state)) {
                hm.get(top.state).add(top.node.val);
            } else {
                List<Integer> temp = new LinkedList<>();
                temp.add(top.node.val);
                hm.put(top.state, temp);
            }
            while (sz-- > 0) {
                if (top.node.left != null) {
                    qu.add(new Pair2(top.node.left, top.state - 1));
                }
                if (top.node.right != null) {
                    qu.add(new Pair2(top.node.right, top.state + 1));
                }
            }
        }
        int[] keys = new int[hm.size()];
        int i = 0;
        for (Integer key : hm.keySet()) {
            keys[i] = key;
            i++;
        }

        List<List<Integer>> list = new ArrayList<>();
        for (List<Integer> ls : hm.values()) {
            list.add(ls);
        }
        return list;
    }

    public static void demo() {

    }

    public static void main(String[] args) {
        demo();
    }
}
