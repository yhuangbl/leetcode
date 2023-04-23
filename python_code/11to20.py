from collections import OrderedDict

class Solution11:
    # Greedy + 2 pointers
    # Idea: if we move the lower one, total area might increase
    # However, if we move the higher one, total area most decrease 
    def maxArea(self, height: List[int]) -> int:
        sp, ep = 0, len(height)-1
        area = 0
        while sp < ep:
            area = max(min(height[sp], height[ep]) * (ep-sp), area)
            if height[sp] > height[ep]:
                ep -= 1
            else:
                sp += 1
        return area

class Solution15:
    # 2 pointers at the twoSum helper function
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        ans, d = [], OrderedDict()
        nums.sort()
        for n in nums:
            if n in d:
                d[n] += 1
            else:
                d[n] = 1
        
        for n, cnt in d.items():
            if cnt >= 3 and n * 3 == 0:
                ans.append([n] * 3)
            if cnt >= 2:
                target = n*-2
                if target in d and target != n:
                    ans.append([n]*2 + [target])
            if cnt >= 1:
                two_sum_ans = self.twoSum(d, n)
                ans += two_sum_ans
        return ans
    
    def twoSum(self, d: dict[int, int], c: int) -> List[List[int]]:
        ans, target = [], c * -1
        nums = [k for k in d.keys() if k > c]
        s, e = 0, len(nums) - 1
        while s < e:
            s_val = nums[s]
            e_val = nums[e]
            curr_val = s_val + e_val 
            if curr_val == target:
                ans.append([c, s_val, e_val])
                s, e = s+1, e-1
            elif curr_val < target:
                s += 1
            else :
                e -= 1
        return ans



        