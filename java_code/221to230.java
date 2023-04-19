class Solution224 {
    private boolean isDigit(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    public int calculate(String s) {
        Stack<String> numbers = new Stack<String>();
        String curr = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                // ignore
            }
            else if (isDigit(c)) {
                curr += c;
            }
            else {
                if (!"".equals(curr)) {
                    numbers.push(curr);

                    curr = "";
                }

                if (c == '-' || c == '(') {
                    numbers.push(c + "");
                }
                else if (c == ')') {
                    int result = 0;
                    while (true) {
                        int minusCnt = 0;
                        String numStr = numbers.pop();
                        if ("(".equals(numStr)) {
                            break;
                        }

                        int num = Integer.parseInt(numStr);
                        while ("-".equals(numbers.peek())) {
                            minusCnt++;
                            numbers.pop();
                        }
                        if (minusCnt % 2 == 1) {
                            num *= -1;
                        }
                        result += num;
                    }
                    String resultStr = String.valueOf(result);
                    if (result < 0) {
                        numbers.push("-");
                        numbers.push(resultStr.substring(1, resultStr.length()));
                    }
                    else {
                        numbers.push(resultStr);
                    }
                }
            }
        }

        if (!"".equals(curr)) {
            numbers.push(curr);
        }

        int len = numbers.size();
        String[] numberStr = numbers.toArray(new String[len]);
        int result = 0;
        int i = 0;
        int minusCnt = 0;
        while (i < len) {
            if ("-".equals(numberStr[i])) {
                minusCnt++;
            }
            else {
                boolean minus = (minusCnt % 2) == 1;
                int num = Integer.parseInt(numberStr[i]);
                if (minus) {
                    num *= -1;
                }
                result += num;
                minusCnt = 0;
            }
            i++;
        }
        return result;
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
class Solution230 {
    private int countTree(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;

        int leftCount = countTree(root.left);
        int rightCount = countTree(root.right);
        return leftCount + rightCount + 1; // + 1 for the root
    }

    /**
     * Make use of binary search tree property + recursion
     */
    public int kthSmallest1(TreeNode root, int k) {
        int rootCount = countTree(root.left) + 1;

        if (k == rootCount) {
            return root.val;
        }
        else if (k > rootCount) {
            return kthSmallest(root.right, k - rootCount);
        }
        else {
            return kthSmallest(root.left, k);
        }
    }

    /**
     * Stack approach: because the leftmost leaf node is the smallest, and it is
     * the last to be traversed the first to be examined, we should use a stack.
     */
    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> nodes = new Stack<TreeNode>();
        TreeNode curr = root;
        int cnt = 0;
        while (curr != null || !nodes.empty()) {
            while (curr != null) {
                nodes.push(curr);
                curr = curr.left;
            }

            while (true) {
                TreeNode top = nodes.pop();
                cnt++;
                if (cnt == k) {
                    return top.val;
                }
                else if (cnt < k) {
                    if (top.right != null) {
                        curr = top.right;
                        break;
                    }
                }
            }
        }
        return -1;
    }
}

class Solution227 {
    /**
     * 有先後順序的calculators (e.g. 加了括號或乘除) -> 用stack
     */
    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isSpace(char c) {
        return c == ' ';
    }

    public int calculate(String s) {
        if (s == null) {
            return 0;
        }

        int len = s.length();
        if (len == 0) {
            return 0;
        }

        Stack<Integer> nums = new Stack<Integer>();
        boolean minus = false;
        String numStr = "";
        int i = 0;
        while (i < len) {
            char currC = s.charAt(i++);
            if (isDigit(currC)) {
                numStr += currC;
            }
            else {
                if (!"".equals(numStr)) {
                    Integer num = Integer.parseInt(numStr);
                    if (minus) {
                        num *= -1;
                    }
                    nums.push(num);
                    numStr = "";
                    minus = false;
                }
                if (isSpace(currC)) {
                    // ignore spaces
                }
                else if (currC == '-') {
                    minus = true;
                }
                else if (currC == '+') {
                    minus = false;
                }
                else { // + or /
                    Integer left = nums.pop();
                    String rightStr = "";
                    while (i < len && (isSpace(s.charAt(i)) || isDigit(s.charAt(i)))) {
                        char currR = s.charAt(i++);
                        if (isDigit(currR)) {
                            rightStr += currR;
                        }
                    }
                    Integer right = Integer.parseInt(rightStr);
                    Integer result = (currC == '*')? left * right: left / right;
                    nums.push(result);
                }
            }
        }

        if (!"".equals(numStr)) {
            Integer num = Integer.parseInt(numStr);
            if (minus) {
                num *= -1;
            }
            nums.push(num);
            numStr = "";
            minus = false;
        }


        int stackSize = nums.size();
        Integer[] numArr = nums.toArray(new Integer[stackSize]);
        int result = 0;
        for (i = 0; i < stackSize; i++) {
            result += numArr[i];
        }
        return result;
    }
}