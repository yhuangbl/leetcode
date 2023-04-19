class Solution136:
    # Time: O(n); Space: O(n)
    def singleNumber(self, nums: List[int]) -> int:
        unique = set()
        for i in nums:
            if i in unique:
                unique.remove(i)
            else:
                unique.add(i)
        return list(unique)[0]
    
    # Time: O(n); Space: O(1)
    # check the same number -> XOR
    def singleNumber2(self, nums: List[int]) -> int:
        ans = nums[0]
        for i in range(1, len(nums)):
            ans ^= nums[i]
        return ans