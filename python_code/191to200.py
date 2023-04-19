class Solution191:
    def hammingWeight(self, n: int) -> int:
        if n == 1:
            return 1
        if n == 0: 
            return 0
        # in Python: / is true division (floating point); // is integer division (floor)
        return self.hammingWeight(n//2) + (1 if n%2 == 1 else 0)
    
    # bit manipulation => how many 1s? use magic number
    def hammingWeight2(self, n: int) -> int:
        weight = 0
        while n > 0: 
            n = n & (n-1)
            weight += 1
        return weight

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