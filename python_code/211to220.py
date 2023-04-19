from queue import PriorityQueue

class Solution215:
    # Time: O(nlogn)
    def findKthLargest(self, nums: List[int], k: int) -> int:
        q = PriorityQueue() # max heap
        for n in nums:
            q.put(n*-1)
        for _ in range(k-1):
            q.get()
        return q.get()*-1
    
    # Time: O(nlogk) Optimized Priority Queue => building a size-k heap
    def findKthLargest2(self, nums: List[int], k: int) -> int:
        q = PriorityQueue() # min heap
        for n in nums:
            if q.qsize() < k:
                q.put(n)
            else:
                if q.queue[0] < n:
                    q.get()
                    q.put(n)
        return q.get()
    
    # Time: O(n) Quickselect; worst case O(n^2)
    def findKthLargest3(self, nums: List[int], k: int) -> int:
        s_idx = 0
        total = len(nums)
        e_idx = total-1
        target_idx = total-k
        while s_idx <= e_idx: 
            p = self.partition(nums, s_idx, e_idx)
            if p == target_idx:
                break
            elif target_idx > p:
                s_idx = p+1
            else:
                e_idx = p-1
        return nums[target_idx]
        
    def partition(self, nums: List[int], s: int, e: int) -> int:
        pivot = nums[e]
        boundary = s-1
        for i in range(s, e+1):
            if nums[i] < pivot:
                boundary += 1
                self.swap(nums, i, boundary)
        final_boundary = boundary+1
        self.swap(nums, final_boundary, e)
        return final_boundary

    def swap(self, nums: List[int], a: int, b: int):
        tmp = nums[b]
        nums[b] = nums[a]
        nums[a] = tmp
    
    