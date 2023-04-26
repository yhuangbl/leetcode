class Solution62:
    # DP: time O(m*n); T(r,c) = T(r+1,c) + T(r,c+1)
    def uniquePaths(self, m: int, n: int) -> int:
        ways = [[None] * n for _ in range(m)] # m * n 2D array
        # Base cases
        ways[m-1][n-1] = 1 # clarify this 
        if m >= 2:
            ways[m-2][n-1] = 1
        if n >= 2:
            ways[m-1][n-2] = 1
        
        for row in range(m-1, -1, -1): # stop index should be -1 to include 0
            for col in range(n-1, -1, -1):
                if ways[row][col] is None: # ignore base cases
                    right = ways[row][col+1] if col+1 < n else 0
                    down = ways[row+1][col] if row+1 < m else 0
                    ways[row][col] = right + down
        
        return ways[0][0]

class Solution70:
    # Time: O(n); Space: O(n)
    # can optimize Space to O(1) if get rid of unused ones
    def climbStairs(self, n: int) -> int:
        ways = [1, 2]
        for i in range(2, n):
            ways.append(ways[i-1] + ways[i-2])
        return ways[n-1]