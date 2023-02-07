# cannot use greedy because nodes might be negative.
class Solution120:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        length = len(triangle)
        index = 0
        row = 0
        path_sum = [[triangle[0][0]]]
        best = None
        for r in range(1, length):
            row_sum = []
            for i in range(r+1):
                left = path_sum[r-1][i-1] if i-1 >= 0 else 100000
                right = path_sum[r-1][i] if i < r else 100000
                min_sum = triangle[r][i] + min(left, right)
                row_sum.append(min_sum)
            path_sum.append(row_sum)

        last_row = length-1
        best = path_sum[last_row][0]
        for i in range(1, length):
           curr = path_sum[last_row][i]
           if curr < best:
               best = curr

        return best