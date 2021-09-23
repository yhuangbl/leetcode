/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution21 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode l1p = l1;
        ListNode l2p = l2;

        ListNode result = null;
        ListNode resultp = null;
        while (l1p != null || l2p != null) {
            ListNode newNode = null;
            if (l1p != null && l2p != null) {
                if (l1p.val < l2p.val) {
                    newNode = new ListNode(l1p.val);
                    l1p = l1p.next;
                }
                else {
                    newNode = new ListNode(l2p.val);
                    l2p = l2p.next;
                }
            }
            else if (l1p == null) {
                newNode = new ListNode(l2p.val);
                l2p = l2p.next;
            }
            else if (l2p == null) {
                newNode = new ListNode(l1p.val);
                l1p = l1p.next;
            }

            if (result == null) {
                result = newNode;
                resultp = newNode;
            }
            else {
                resultp.next = newNode;
                resultp = newNode;
            }
        }
        return result;
    }
}

class Solution22 {
    private List<String> generateParenthesisHelper(int n, int pairCount, int openCount, String curr) {
        int len = curr.length();
        if (len == n*2) {
            return Arrays.asList(curr);
        }
        boolean isFullyClosed = (pairCount*2 == len);

        List<String> result = new ArrayList<String>();
        char last = curr.charAt(len-1);
        if (openCount == n) {
            result.addAll(generateParenthesisHelper(n, pairCount+1, openCount, curr+")"));
        }
        else if (isFullyClosed) {
            result.addAll(generateParenthesisHelper(n, pairCount, openCount+1, curr+"("));
        }
        else {
            result.addAll(generateParenthesisHelper(n, pairCount+1, openCount, curr+")"));
            result.addAll(generateParenthesisHelper(n, pairCount, openCount+1, curr+"("));
        }
        return result;
    }

    public List<String> generateParenthesis(int n) {
        if (n >= 1) {
            return generateParenthesisHelper(n, 0, 1, "(");
        }
        return Arrays.asList("");
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution23 {
    private boolean notEnd(ListNode[] pointers, int len) {
        for (int i = 0; i < len; i++) {
            if (pointers[i] != null) {
                return true;
            }
        }
        return false;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        int len = lists.length;
        if (len == 0) {
            return null;
        }

        // init pointers
        ListNode[] pointers = new ListNode[len];
        for (int i = 0; i < len; i++) {
            pointers[i] = lists[i];
        }

        // maniuplate pointers
        ListNode root = null;
        ListNode curr = null;
        while (notEnd(pointers, len)) {
            int currMinVal = Integer.MAX_VALUE;
            int currMinNodeIdx = -1;
            for (int i = 0; i < len; i++) {
                if (pointers[i] != null && pointers[i].val < currMinVal) {
                    currMinVal = pointers[i].val;
                    currMinNodeIdx = i;
                }
            }
            if (root == null) { // root node
                root = new ListNode(currMinVal);
                curr = root;

            }
            else {
                curr.next = new ListNode(currMinVal);
                curr = curr.next;
            }
            pointers[currMinNodeIdx] = pointers[currMinNodeIdx].next;
        }
        return root;

    }
}

class Solution26 {
    /**
     * Time complexity: O(n); space complexity: O(1)
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = 1;
        int previousInt = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != previousInt) {
                len++;
                nums[len-1] = nums[i];
                previousInt = nums[i];
            }
        }
        return len;
    }
}

class Solution28 {
    public int strStr(String haystack, String needle) {
        int len = needle.length();

        for (int i = 0; i < haystack.length() - len + 1; i++) {
            int j = i + len;
            if (needle.equals(haystack.substring(i, j))) {
                return i;
            }
        }
        return -1;
    }
}

class Solution29 {
    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;

        // long to avoid overflow
        long dividendl = (long) dividend;
        long divisorl = (long) divisor;

        boolean neg = false;
        if ((dividendl < 0) ^ (divisorl < 0))
            neg = true;

        // need to operate on long; for example int.min * -1 will overflow
        if (dividendl < 0) dividendl *= -1;
        if (divisorl < 0) divisorl *= -1;

        long start = 0;
        long end = dividendl;
        while (start <= end) {
            long mid = (start + end) >> 1;
            long product = mid * divisorl;
            long diff = dividendl - product;
            if (diff >= 0 && diff < divisorl) {
                if (neg)
                    mid *= -1;
                if (mid > Integer.MAX_VALUE)
                    return Integer.MAX_VALUE;
                else if (mid < Integer.MIN_VALUE)
                    return Integer.MIN_VALUE;
                else
                    return (int) mid;
            }
            else if (diff < 0)
                end = mid - 1;
            else if (diff > 0)
                start = mid + 1;
        }
        return -1;
    }
}