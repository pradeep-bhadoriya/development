public class TressLevel2 {

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

    public static void demo() {

    }

    public static void main(String[] args) {
        demo();
    }
}
