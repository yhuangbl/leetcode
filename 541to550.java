class Solution548 {
    private int getSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * Time: O(n^2)
     * Space: O(n)
     */
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        if (n < 7) {
            return false;
        }

        int[] sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int j = 3; j < n - 2; j++) {
            Set<Integer> sumsAppear = new HashSet<Integer>();
            for (int i = 1; i + 1 < j; i++) {
                int sum1 = sums[i - 1];
                int sum2 = sums[j - 1] - sums[i];
                if (sum1 == sum2) {
                    sumsAppear.add(sum1);
                }
            }
            for (int k = j + 2; k < n - 1; k++) {
                int sum1 = sums[k - 1] - sums[j];
                int sum2 = sums[n - 1] - sums[k];
                if (sum1 == sum2 && sumsAppear.contains(sum1)) {
                    return true;
                }
            }
        }
        return false;
    }
}