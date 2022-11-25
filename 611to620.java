/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution617 { 
    // merge 2 binary trees 
    // time complexity: O(n) where n is #nodes in tree
    // O(V+E) = O(n + (n-1)) = O(n)
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return mergeBFS(root1, root2);
    }

    private TreeNode mergeBFS(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return null;
        }
        int val;
        if (node1 == null) {
            val = node2.val;
        } else if (node2 == null) {
            val = node1.val;
        } else {
            val = node1.val + node2.val;
        }
        TreeNode node = new TreeNode(val);
        node.left = mergeBFS(node1 == null ? null : node1.left, node2 == null ? null : node2.left);
        node.right = mergeBFS(node1 == null ? null : node1.right, node2 == null ? null : node2.right);
        return node;
    }
}