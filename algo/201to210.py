class Solution209:
    # sliding window: O(n)
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        s_idx, e_idx, nums_len = 0, 0, len(nums)
        min_len = nums_len + 1
        curr, last = 0, None

        while s_idx < nums_len:
            while e_idx >= s_idx and e_idx < nums_len:
                if last is not None:
                    curr -= last
                    last = None
                else:
                    curr += nums[e_idx]
                if curr >= target:
                    curr_len = e_idx - s_idx + 1
                    min_len = min(curr_len, min_len)
                    break # more positive numbers; won't get to target with min length again
                e_idx += 1
            last = nums[s_idx]
            s_idx += 1

        return min_len if min_len <= nums_len else 0