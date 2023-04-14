from queue import PriorityQueue

class Solution215:
    def findKthLargest(self, nums: List[int], k: int) -> int:
        q = PriorityQueue()
        for n in nums:
            q.put(n*-1)
        for _ in range(k-1):
            q.get()
        return q.get()*-1