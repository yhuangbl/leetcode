class Solution70:
    # Time: O(n); Space: O(n)
    # can optimize Space to O(1) if get rid of unused ones
    def climbStairs(self, n: int) -> int:
        ways = [1, 2]
        for i in range(2, n):
            ways.append(ways[i-1] + ways[i-2])
        return ways[n-1]