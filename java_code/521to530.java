class Solution523 {
    /**
     * Dynamic programming
     * Time: O(n^2)
     * Space: O(n^2)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return false;
        }
        int[][] dp = new int[len][len];

        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = dp[i][j - 1] + nums[j];
                if (k != 0 && dp[i][j] % k == 0) {
                    return true;
                }
                else if (k == 0 && dp[i][j] == k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Space optimized Dynamic progrmaming
     * Time: O(n^2)
     * Space: O(n)
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 1) {
            return false;
        }
        int[] dp = new int[len];

        for (int i = 0; i < len - 1; i++) {
            dp[i] = nums[i];
            for (int j = i + 1; j < len; j++) {
                dp[j] = dp[j - 1] + nums[j];
                if (k != 0 && dp[j] % k == 0) {
                    return true;
                }
                else if (k == 0 && dp[j] == k) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Solution525 {
    /**
     * tips: 累積和是一個很好的工具，這裏遇0 -1遇1 +1
     * <key> same count: same number of zeros & ones between the indices
     * https://leetcode.com/articles/contiguous-array/
     */
    public int findMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        HashMap<Integer, Integer> sum = new HashMap<Integer, Integer>();
        sum.put(0, -1);

        int currSum = 0;
        int maxlen = 0;
        for (int i = 0; i < len; i++) {
            int addNum = (nums[i] == 0)? -1: 1;
            currSum += addNum;
            if (sum.containsKey(currSum)) {
                maxlen = Math.max(maxlen, i - sum.get(currSum));
            }
            else {
                sum.put(currSum, i);
            }
        }

        return maxlen;
    }
}

class Solution529 {
    private int getAdj(char[][] board, int row, int col, int rowlen, int collen) {
        int adj = 0;
        boolean addRow = row + 1 < rowlen;
        boolean addCol = col + 1 < collen;
        boolean reduceRow = row - 1 >= 0;
        boolean reduceCol = col - 1 >= 0;
        if (addRow && board[row + 1][col] == 'M') {
            adj++;
        }
        if (addCol && board[row][col + 1] == 'M') {
            adj++;
        }
        if (reduceRow && board[row - 1][col] == 'M') {
            adj++;
        }
        if (reduceCol && board[row][col - 1] == 'M') {
            adj++;
        }
        if (addRow && addCol && board[row + 1][col + 1] == 'M') {
            adj++;
        }
        if (addRow && reduceCol && board[row + 1][col - 1] == 'M') {
            adj++;
        }
        if (reduceRow && addCol && board[row - 1][col + 1] == 'M') {
            adj++;
        }
        if (reduceRow && reduceCol && board[row - 1][col - 1] == 'M') {
            adj++;
        }
        return adj;
    }

    private void dfsMark(char[][] board, int row, int col, int rowlen, int collen) {
        if (row >= 0 && row < rowlen && col >= 0 && col < collen && board[row][col] == 'E') {
            int adj = getAdj(board, row, col, rowlen, collen);
            if (adj == 0) {
                board[row][col] = 'B';
                dfsMark(board, row + 1, col, rowlen, collen);
                dfsMark(board, row, col + 1, rowlen, collen);
                dfsMark(board, row - 1, col, rowlen, collen);
                dfsMark(board, row, col - 1, rowlen, collen);
                dfsMark(board, row + 1, col + 1, rowlen, collen);
                dfsMark(board, row - 1, col + 1, rowlen, collen);
                dfsMark(board, row - 1, col - 1, rowlen, collen);
                dfsMark(board, row + 1, col - 1, rowlen, collen);
            } else {
                board[row][col] = (char) (adj + '0');
            }
        }
    }

    /**
     * Time: O(V + E)
     * Space:
     */
    public char[][] updateBoard(char[][] board, int[] click) {
        int rowlen = board.length;
        int collen = board[0].length;
        int row = click[0];
        int col = click[1];

        if (board[row][col] == 'M') {
            board[row][col] = 'X';
        } else if (board[row][col] == 'E') {
            dfsMark(board, row, col, rowlen, collen);
        }
        return board;
    }
}