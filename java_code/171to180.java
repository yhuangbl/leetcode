/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Problem num: 173
 * Key: binary search tree = in-order traversal
 * Time Complexity: O(n)
 * Space Copmlexity: O(n)
 */
class BSTIterator {

    List<TreeNode> nodes;
    int idx;
    int size;

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        this.nodes.add(root);
        traverse(root.right);
    }

    public BSTIterator(TreeNode root) {
        this.idx = -1;
        this.nodes = new ArrayList<TreeNode>();
        traverse(root);
        this.size = nodes.size();
    }

    /** @return the next smallest number */
    public int next() {
        this.idx++;
        return this.nodes.get(this.idx).val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return this.idx != this.size - 1;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */