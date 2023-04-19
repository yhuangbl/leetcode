from queue import PriorityQueue

class Solution973:
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        q = PriorityQueue()
        for p in points:
            q.put((p[0]**2 + p[1]**2, p))
        res = []
        for _ in range(k):
            res.append(q.get()[1])
        return res