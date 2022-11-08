class Solution704 {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int start = 0;
        int end = len - 1;
        while (start <= end) {
                int mid = (start + end) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }
}