from collections import Counter
from queue import PriorityQueue

class Solution347:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        c = Counter(nums)
        q = PriorityQueue()
        for (c, cnt) in c.items():
            q.put((cnt*-1, c))
        res = []
        for _ in range(k):
            res.append(q.get()[1])
        return res