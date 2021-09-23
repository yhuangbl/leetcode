class Solution91 {
    /**
     * Dynamic programming
     * Time: O(n); Space: O(n)
     * Analysis: https://github.com/grandyang/leetcode/issues/91
     * 注意一大堆的corner cases....
     */
    public int numDecodings(String s) {
        if (s == null)
            return 0;
        int len = s.length();
        if (len == 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;

        int[] ways = new int[len];
        ways[0] = 1;
        if (len >= 2) {
            if (s.charAt(1) == '0') {
                char firstC = s.charAt(0);
                if (firstC == '1' || firstC == '2')
                    ways[1] = 1;
                else
                    ways[1] = 0;
            }
            else {
                String firstTwo = s.substring(0, 2);
                ways[1] = (firstTwo.charAt(0) == '1' ||
                        (firstTwo.charAt(0) == '2' && firstTwo.charAt(1) <= '6'))? 2: 1;
            }

            for (int i = 2; i < len; i++) {
                if (s.charAt(i) == '0') {
                    char firstC = s.charAt(i-1);
                    if (firstC == '1' || firstC == '2')
                        ways[i] = ways[i-2];
                    else
                        return 0;
                }
                else {
                    String lastTwo = s.substring(i-1, i+1);
                    ways[i] = ways[i-1];
                    if (lastTwo.charAt(0) == '1' ||
                            (lastTwo.charAt(0) == '2' && lastTwo.charAt(1) <= '6'))
                        ways[i] += ways[i-2];
                }
            }
        }

        return ways[len-1];
    }

    /**
     * Cleaner version of dp
     */
    public int numDecodings2(String s) {
        if (s == null)
            return 0;
        int len = s.length();
        if (len == 0)
            return 0;
        if (s.charAt(0) == '0')
            return 0;

        int[] ways = new int[len];
        ways[0] = 1;
        for (int i = 1; i < len; i++) {
            char lastC = s.charAt(i);
            ways[i] = (lastC == '0')? 0: ways[i-1];
            char last2C = s.charAt(i-1);
            if (last2C == '1' || (last2C == '2' && lastC <= '6')) {
                if (i >= 2)
                    ways[i] += ways[i-2];
                if (i == 1)
                    ways[i]++;
            }

        }
        return ways[len-1];
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
 *
 * About tree traversal:
 * - DFS: (記root在哪)
 *       inorder: left, root, right
 *       preorder: root, left, right
 *       postorder: left, right, root
 * - BFS:
 *       level-order
 */
class Solution94 {
    /**
     * Time complexity: O(n); Space complexity: O(n)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> inorder = new ArrayList<Integer>();
        if (root == null) {
            return inorder;
        }

        // addAll: add elements in a list into a list
        inorder.addAll(inorderTraversal(root.left));
        inorder.add(root.val);
        inorder.addAll(inorderTraversal(root.right));

        return inorder;
    }

    public List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> inorder = new ArrayList<Integer>();
        if (root == null) {
            return inorder;
        }

        // the leaf node is the first one to print: LIFO -> stack
        Stack<TreeNode> nodeStack = new Stack<TreeNode>();
        TreeNode curr = root;
        while (curr != null || !nodeStack.empty()) {
            while (curr != null) {
                nodeStack.push(curr);
                curr = curr.left;
            }

            curr = nodeStack.pop();
            inorder.add(curr.val);
            curr = curr.right;
        }

        return inorder;
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
class Solution98 {
    /**
     * Time & Space complexity: O(n)
     */
    private boolean isValidBSTInfo(TreeNode root, double lower, double upper) {
        if (root.val > lower && root.val < upper) {
            if (root.left != null && root.right != null) {
                return isValidBSTInfo(root.left, lower, root.val) && isValidBSTInfo(root.right, root.val, upper);
            }
            else if (root.left == null && root.right != null) {
                return isValidBSTInfo(root.right, root.val, upper);
            }
            else if (root.right == null && root.left != null) {
                return isValidBSTInfo(root.left, lower, root.val);
            }
            else if (root.left == null && root.right == null) {
                return true;
            }
        }
        else {
            return false;
        }
        return false;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Why double but not Integer.MAX_VALUE here: if value == MAX it's acceptable, but because we set upper bound to int.max, it thought it's not acceptable
        // note Double.MIN_VALUE is the smallest nonegative value; use NEGATIVE_INFINITY instead
        double lower = Double.NEGATIVE_INFINITY;
        double upper = Double.POSITIVE_INFINITY;

        if (root.left != null && root.right != null) {
            return isValidBSTInfo(root.left, lower, root.val) && isValidBSTInfo(root.right, root.val, upper);
        }
        else if (root.left == null && root.right != null) {
            return isValidBSTInfo(root.right, root.val, upper);
        }
        else if (root.right == null && root.left != null) {
            return isValidBSTInfo(root.left, lower, root.val);
        }
        else if (root.left == null && root.right == null) {
            return true;
        }
        return false;
    }

    /**
     * 我是分隔線！
     */

    private boolean isValidBSTInfoClean(TreeNode root, double lower, double upper) {
        if (root.val > lower && root.val < upper) {
            boolean result = true;
            if (root.left != null)
                result = result && isValidBSTInfo(root.left, lower, root.val);
            if (root.right != null)
                result = result && isValidBSTInfo(root.right, root.val, upper);
            return result;
        }
        return false;
    }

    public boolean isValidBSTClean(TreeNode root) {
        if (root == null) {
            return true;
        }

        // Why double but not Integer.MAX_VALUE here: if value == MAX it's
        // acceptable, but because we set upper bound to int.max, it thought
        // it's not acceptable
        // note: Double.MIN_VALUE is the smallest nonegative value;
        // use NEGATIVE_INFINITY instead
        double lower = Double.NEGATIVE_INFINITY;
        double upper = Double.POSITIVE_INFINITY;


        boolean result = true;
        if (root.left != null)
            result = result && isValidBSTInfo(root.left, lower, root.val);
        if (root.right != null)
            result = result && isValidBSTInfo(root.right, root.val, upper);
        return result;
    }
}