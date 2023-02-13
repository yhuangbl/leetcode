class Solution190:
    TOTAL_LEN = 32

    def reverseBits(self, n: int) -> int:
        ans_arr = []
        ans_arr = self.reverseBitsHelper(n, ans_arr)
       
        ans_len = len(ans_arr)
        if ans_len < self.TOTAL_LEN:
            ans_arr += [0] * (self.TOTAL_LEN-ans_len)

        ans = 0
        for i in range(self.TOTAL_LEN):
            ans += (2 ** i) * ans_arr[self.TOTAL_LEN-1-i]
        return ans

    def reverseBitsHelper(self, n: int, ans: list) -> list:
        if n > 0:
            ans.append(n%2)
            self.reverseBitsHelper(n//2, ans)
        return ans