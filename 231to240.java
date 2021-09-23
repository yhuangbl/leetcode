/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution234 {
    /**
     * Time: O(n); Space: O(n)
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> record = new ArrayList<Integer>();

        ListNode curr = head;
        while (curr != null) {
            record.add(curr.val);
            curr = curr.next;
        }

        int i = 0;
        int j = record.size() - 1;
        while (i <= j) {
            // java objects (e.g. Integer) should not compare through ==; should use .equal
            if (!record.get(i).equals(record.get(j))) return false;
            i++;
            j--;
        }
        return true;
    }
    /** 我是分隔線 **/
    private ListNode reverse(ListNode head) {
        ListNode reversedHead = new ListNode(-1);
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = reversedHead.next;
            reversedHead.next = curr;
            curr = nextNode;
        }
        return reversedHead.next;
    }

    /**
     * Time: O(n); Space: O(1)
     * Two pointers: a fast pointer & a slow pointer
     * reverse the part that is after the middle and check whether they are
     * the same
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;

        ListNode fast = head.next; // head.next因為不然slow.next不在中間後一個
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode reversedHead = reverse(slow.next);
        ListNode curr = head;
        ListNode reversedCurr = reversedHead;

        while (reversedCurr != null && curr != null) {
            if (reversedCurr.val != curr.val)
                return false;
            reversedCurr = reversedCurr.next;
            curr = curr.next;
        }
        return true;
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
class Solution236 {
    private TreeNode lca;

    private boolean getSubtree(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }

        int mid = (root.val == p.val || root.val == q.val)? 1:0;
        int left = (getSubtree(root.left, p, q))? 1:0;
        int right = (getSubtree(root.right, p, q))? 1:0;

        if (mid + left + right == 2) {
            this.lca = root;
            return true;
        }

        return (mid + left + right) > 0;
    }

    /**
     * Recursion method
     * Time: O(n)
     * Space: O(1) // not counting recursion stack
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        this.lca = null;
        getSubtree(root, p, q);
        return this.lca;
    }

    /**
     * 我是分隔線
     */
    private void getAncestors(TreeNode root, TreeNode p, TreeNode q, Map<TreeNode, TreeNode> parent) {
        parent.put(root, null);

        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        while (!(parent.containsKey(p) && parent.containsKey(q))) {
            TreeNode front = nodes.remove();
            if (front.left != null) {
                parent.put(front.left, front);
                nodes.add(front.left);
            }

            if (front.right != null) {
                parent.put(front.right, front);
                nodes.add(front.right);
            }
        }
    }

    private Set<TreeNode> getPAncestor(TreeNode p, Map<TreeNode, TreeNode> parent) {
        Set<TreeNode> ancestors = new HashSet<TreeNode>();
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        return ancestors;
    }

    private TreeNode findLca(Set<TreeNode> pAncestor, TreeNode q, Map<TreeNode, TreeNode> parent) {
        while (!pAncestor.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

    /**
     * Iterative method with parent pointer
     * Time: O(n); Space: O(n)
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>();
        getAncestors(root, p, q, parent);
        Set<TreeNode> pAncestor = getPAncestor(p, parent);
        return findLca(pAncestor, q, parent);
    }
}

class Solution238 {
    /**
     * Trick: prefix & suffix array
     * Time complexity: O(n); Space complexity: O(n)
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            return new int[0];
        }

        int len = nums.length;
        if (len <= 1) {
            return new int[0];
        }

        int[] left = new int[len];
        left[0] = 1;
        int[] right = new int[len];
        right[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }

    /**
     * Time compleixty: O(n); Space complexity: O(1)
     * trick: compute one of the arraies on the fly
     */
    public int[] productExceptSelf2(int[] nums) {
        if (nums == null) {
            return new int[0];
        }

        int len = nums.length;
        if (len <= 1) {
            return new int[0];
        }

        int[] result = new int[len];
        result[len - 1] = 1;

        for (int i = len - 2; i >= 0; i--) {
            result[i] = result[i + 1] * nums[i + 1];
        }

        int left = 1;
        for (int i = 0; i < len; i++) {
            result[i] *= left;
            left *= nums[i];
        }
        return result;
    }
}

class Solution240 {
    /**
     * Use divide & conquer (split into submatrixes) to make it faster
     */
    private boolean searchMatrixHelper(int[][] matrix, int rowIdx, int colIdx,
                                       int target, int m, int n) {
        if (rowIdx > m || colIdx > n)
            return false;

        if (matrix[rowIdx][colIdx] == target)
            return true;
        else if (matrix[rowIdx][colIdx] > target)
            return false;
        else {
            return searchMatrixHelper(matrix, rowIdx+1, colIdx, target, m, n) ||
                    searchMatrixHelper(matrix, rowIdx, colIdx+1, target, m, n);
        }
    }

    private boolean searchMatrixSplit(int[][] matrix, int startRow, int endRow,
                                      int startCol, int endCol, int target) {
        int m = (endRow - startRow + 1);
        int n = (endCol - startCol + 1);
        if (m * n <= 10) {
            if (matrix[startRow][startCol] <= target) {
                return searchMatrixHelper(matrix, startRow, startCol, target, endRow, endCol);
            }
        }
        else {
            int halfM = (startRow + endRow) / 2;
            int halfN = (startCol + endCol) / 2;
            return searchMatrixSplit(matrix, startRow, halfM, startCol, halfN, target) || searchMatrixSplit(matrix, halfM+1, endRow, startCol, halfN, target) ||
                    searchMatrixSplit(matrix, startRow, halfM, halfN+1, endCol, target) || searchMatrixSplit(matrix, halfM+1, endRow, halfN+1, endCol, target);
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null)
            return false;
        int m = matrix.length;
        if (m == 0)
            return false;
        int n = matrix[0].length;
        if (n == 0)
            return false;
        if (m * n <= 10) {
            return searchMatrixHelper(matrix, 0, 0, target, m-1, n-1);
        }

        int halfM = m/2;
        int halfN = n/2;
        return searchMatrixSplit(matrix, 0, m/2, 0, n/2, target) || searchMatrixSplit(matrix, m/2+1, m-1, 0, n/2, target)  ||
                searchMatrixSplit(matrix, 0, m/2, n/2+1, n-1, target) || searchMatrixSplit(matrix, m/2+1, m-1, n/2+1, n-1, target);
    }
}