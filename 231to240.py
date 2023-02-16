class Solution231:
    INT_LEN = 32
    
    def isPowerOfTwo(self, n: int) -> bool:
        if n == 0: 
            return False
        if n == 1:
            return True
        if n%2 == 1:
            return False
        return self.isPowerOfTwo(n/2)

    # bit manipulation with loop
    def isPowerOfTwo2(self, n: int) -> bool:
        if n < 0: # bit can represent negative; but it's not power of 2
            return False
        
        num = 0
        for _ in range(self.INT_LEN):
            last = n & 1
            if last == 1:
                num += 1
            n = n >> 1
        return num == 1
    
    # bit manipulation O(1) => 2^n has only one 1; use magic number
    def isPowerOfTwo3(self, n: int) -> bool:
        return n > 0  and (n & (n-1) == 0)