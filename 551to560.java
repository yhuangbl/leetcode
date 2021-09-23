class Solution560 {
    /**
     * Time: O(n^2); Space: O(n^2)
     * Can we optimize it?
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int ways = 0;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    dp[i][j] = nums[i];
                }
                else {
                    dp[i][j] = dp[i][j - 1] + nums[j];
                }
                if (dp[i][j] == k) {
                    ways++;
                }
            }
        }
        return ways;
    }

    /**
     * Time: O(n^2); Space: O(n)
     * Can we optimize it?
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int[] sums = new int[len + 1];
        int sum = 0;
        for (int i = 1; i <= len; i++) {
            sum += nums[i - 1];
            sums[i] = sum;
        }

        int ways = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int ij = sums[j] - sums[i];
                if (ij == k) {
                    ways++;
                }
            }
        }
        return ways;
    }
}