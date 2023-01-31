class Solution198:
    def rob(self, nums: List[int]) -> int:
        total = [nums[0]]
        max_val = total[0]
        for i in range(1, len(nums)):
            picked_val = nums[i] + total[i-2] if i-2 >= 0 else nums[i]
            val = max(picked_val, total[i-1])
            total.append(val)
            max_val = max(max_val, val)
        return total[-1]