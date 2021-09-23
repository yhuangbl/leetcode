/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution101 {
    private boolean isSubtreeSymmetric(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree.val == rightTree.val) {
            if (leftTree.left == null && rightTree.right == null &&
                    leftTree.right == null && rightTree.left == null) {
                return true;
            }
            else if (leftTree.left == null && rightTree.right == null) {
                return isSubtreeSymmetric(leftTree.right, rightTree.left);

            }
            else if (leftTree.right == null && rightTree.left == null) {
                return isSubtreeSymmetric(leftTree.left, rightTree.right);
            }
            else if (leftTree.left == null || rightTree.right == null ||
                    leftTree.right == null || rightTree.left == null) {
                return false;
            }
            else {
                return isSubtreeSymmetric(leftTree.left, rightTree.right) &&
                        isSubtreeSymmetric(leftTree.right, rightTree.left);
            }
        }
        return false;
    }

    /**
     * Time complexity : O(n).
     * Because we traverse the entire input tree once, the total run time is
     * O(n), where nn is the total number of nodes in the tree.
     * Space complexity : The number of recursive calls is bound by the height
     * of the tree. In the worst case, the tree is linear and the height is in
     * O(n). Therefore, space complexity due to recursive calls on the stack is
     * O(n) in the worst case.
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {
            return true;
        }
        else if (root.left == null || root.right == null) {
            return false;
        }

        if (root.left.val != root.right.val) {
            return false;
        }
        else {
            return isSubtreeSymmetric(root.left, root.right);
        }

    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderList = new ArrayList<List<Integer>>();
        if (root == null) {
            return levelOrderList;
        }
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        int numNodes = 1;
        int numNextNodes = 0;

        List<Integer> thisLevel = new ArrayList<Integer>();
        while (numNodes > 0) {
            TreeNode curr = nodes.remove();
            numNodes--;
            thisLevel.add(curr.val);
            if (curr.left != null) {
                nodes.add(curr.left);
                numNextNodes++;
            }
            if (curr.right != null) {
                nodes.add(curr.right);
                numNextNodes++;
            }
            if (numNodes == 0) {
                levelOrderList.add(thisLevel);
                numNodes = numNextNodes;
                numNextNodes = 0; // 記得歸零所有變量
                thisLevel = new ArrayList<Integer>();
            }
        }
        return levelOrderList;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> zigzagList = new ArrayList<List<Integer>>();
        if (root == null) {
            return zigzagList;
        }

        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        int numNodes = 1;
        int numNextNodes = 0;
        boolean leftToRight = true;

        List<Integer> thisLevel = new ArrayList<Integer>();
        while (numNodes > 0) {
            TreeNode curr = nodes.remove();
            thisLevel.add(curr.val);
            numNodes--;

            if (curr.left != null) {
                nodes.add(curr.left);
                numNextNodes++;
            }
            if (curr.right != null) {
                nodes.add(curr.right);
                numNextNodes++;
            }
            // why switching add order won't work?
            // 因為假如先加right subtree 再加 left subtree 這樣abcd就會變成cdab

            if (numNodes == 0) {
                if (!leftToRight) {
                    Collections.reverse(thisLevel); // not thisLevel.reverse();
                }
                zigzagList.add(thisLevel);
                thisLevel = new ArrayList<Integer>();
                numNodes = numNextNodes;
                numNextNodes = 0;
                leftToRight = !leftToRight;
            }
        }
        return zigzagList;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution104 {
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        int leftDep = 1 + maxDepth(root.left);
        int rightDep = 1 + maxDepth(root.right);
        return (leftDep > rightDep)? leftDep: rightDep;

        // return 1 + Math.max(maxDepth(root.left), maxDepth(root.right))
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution105 {
    private int findIdx(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }

    // Java.util.Arrays.copyOfRange() 可以取代 快很多！
    private int[] copyArr(int[] arr, int startIdx, int endIdx) {
        if (startIdx > endIdx)
            return null;
        int[] result = new int[endIdx - startIdx + 1];
        for (int i = startIdx; i <= endIdx; i++)
            result[i - startIdx] = arr[i];
        return result;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null ||
            preorder.length == 0 || inorder.length == 0)
            return null;
        int len = preorder.length;
        if (inorder.length != len)
            return null;

        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        int rootIdx = findIdx(inorder, rootVal);
        if (rootIdx != -1) {
            TreeNode leftTree = buildTree(copyArr(preorder, 1, rootIdx),
                    copyArr(inorder, 0, rootIdx - 1));
            TreeNode rightTree = buildTree(copyArr(preorder, rootIdx + 1, len - 1),
                    copyArr(inorder, rootIdx + 1, len - 1));
            root.left = leftTree;
            root.right = rightTree;
        }

        return root;
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;

        int len = nums.length;
        int mid = len / 2;

        TreeNode root = new TreeNode(nums[mid]);
        TreeNode left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
        TreeNode right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, len));
        root.left = left;
        root.right = right;
        return root;
    }
}