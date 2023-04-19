class Solution189 {
    public void rotate(int[] nums, int k) {
        // edge case
        // [-1] 2; [1, 2] 3 (or k might be really large)
        if (k > nums.length) {
            k = k % nums.length;
        }

        int[] saved = new int[k];
        for (int i = 0; i < k; i++) {
            saved[i] = nums[nums.length - k + i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            nums[nums.length - 1 - i] = nums[nums.length - 1 - k - i];
        }
        for (int i = 0; i < k; i++) {
            nums[i] = saved[i];
        }
    }
}