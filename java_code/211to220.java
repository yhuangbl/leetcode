class Solution215 {
    /**
     * Time: O(nlong)
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null)
            return -1;
        int len = nums.length;
        if (len == 0)
            return -1;

        Arrays.sort(nums);
        return nums[len-k];
    }

    /**
     * 我是分隔線！
     */
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums, int left, int right) {
        int small = left - 1;
        int big = left;
        int pivot = nums[right];
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, big);
                small++;
                big++;
            }
        }
        swap(nums, right, big);
        return big;
    }

    /**
     * Solution 2: quick sort
     * Quick sort template:
     * if left >= right then retrun
     * pos = Partition(arr, left, right)
     * quicksort(A, left, pos - 1)
     * quicksort(A, pos + 1, right)
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null)
            return -1;
        int len = nums.length;
        if (len == 0)
            return -1;


        int left = 0;
        int right = len - 1;
        int target = len - k;
        while (left <= right) {
            int pos = partition(nums, left, right);
            if (pos == target) {
                return nums[pos];
            }
            else if (pos < target) {
                left = pos + 1;
            }
            else {
                right = pos - 1;
            }
        }
        return -1; // should not reach here
    }
}