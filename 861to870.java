/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int getDeepestDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(getDeepestDepth(root.left), getDeepestDepth(root.right)) + 1;
    }

    /**
     * Time: O(n^2) (assume n is the number of node)
     * Space: O(1) excluding the reucrsion stack
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }

        int leftDepth = getDeepestDepth(root.left);
        int rightDepth = getDeepestDepth(root.right);
        if (leftDepth == rightDepth) {
            return root;
        }
        return (leftDepth > rightDepth)? subtreeWithAllDeepest(root.left): subtreeWithAllDeepest(root.right);
    }
}