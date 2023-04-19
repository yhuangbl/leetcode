// Hint: first apply the idea discussed using an additional array and 
// the in-place solution will pop up eventually.
class Solution283 {
    public void moveZeroes(int[] nums) {
        int write_idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[write_idx++] = nums[i];
            }
        }
        while (write_idx < nums.length) {
            nums[write_idx++] = 0;
        }
    }
}