from collections import Counter
from collections import defaultdict
from queue import PriorityQueue

class Solution451:
    def frequencySort(self, s: str) -> str:
        d = defaultdict(int) # defaultdict usage
        for c in s:
            d[c] += 1
        q = PriorityQueue()
        for c, cnt in d.items():
            q.put((cnt * -1, c)) # default to min heap => * -1 to become max heap (sort of)
        res = ""
        while not q.empty():
            (cnt, c) = q.get()
            for _ in range(cnt * -1): # optimization: res += (c * (cnt*-1)) <- faster 
                res += c
        return res
    
    def frequencySortWithCounter(self, s: str) -> str:
        counter = Counter(s) # counter is the Pythonic way to count hashable, iterable stuffs (list/string)
        q = PriorityQueue()
        for (c, cnt) in counter.items():
            q.put((cnt * -1, c))
        res = ""
        while not q.empty():
            (cnt, c) = q.get()
            res += (c * (cnt*-1))
        return res