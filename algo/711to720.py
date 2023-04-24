class Solution713:
    def numSubarrayProductLessThanK(self, nums: List[int], k: int) -> int:
        s_idx, e_idx, nums_len = 0, 0, len(nums)
        curr, last = 1, None
        ans = 0
        while s_idx < nums_len:
            if s_idx > e_idx:
                e_idx = s_idx
                last, curr = None, 1
            while e_idx < nums_len:
                if last is not None:
                    curr /= last
                    last = None
                else : 
                    curr *= nums[e_idx]
                if curr >= k:
                    break
                e_idx += 1
            num_ans = e_idx - s_idx
            if num_ans > 0:
                ans += num_ans
            last = nums[s_idx]
            s_idx += 1
        return ans