from queue import PriorityQueue

class Solution973:
    
    # Priority Queue
    # Time: O(nlogn)
    def kClosest(self, points: List[List[int]], k: int) -> List[List[int]]:
        q = PriorityQueue()
        for p in points:
            q.put((p[0]**2 + p[1]**2, p))
        res = []
        for _ in range(k):
            res.append(q.get()[1])
        return res
    
    # Optimized Priority Queue => building a size-k heap
    # Time: O(nlogk) 
    def kClosest2(self, points: List[List[int]], k: int) -> List[List[int]]:
        q = PriorityQueue() # max heap
        for p in points:
            dist = p[0]**2 + p[1]**2
            if q.qsize() < k:
                q.put((dist*-1, p))
            elif q.queue[0][0]*-1 > dist: #if smaller than current max
                q.get()
                q.put((dist*-1, p))
        return [q.get()[1] for _ in range(k)]
    
    # Optimized quick selection with random pivot
    # Time: O(n)
    def kClosest3(self, points: List[List[int]], k: int) -> List[List[int]]:
        s_idx, e_idx = 0, len(points)-1
        while s_idx <= e_idx:
            p_idx = self.partitionRandom(points, s_idx, e_idx)
            if p_idx == k:
                break
            elif p_idx > k:
                e_idx = p_idx-1
            else:
                s_idx = p_idx+1
        return points[:k]
    
    def partitionRandom(self, points: List[List[int]], s_idx: int, e_idx: int) -> int:
        r_idx = randint(s_idx, e_idx)
        points[r_idx], points[e_idx] = points[e_idx], points[r_idx]
        return self.partition(points, s_idx, e_idx)
    
    def partition(self, points: List[List[int]], s_idx: int, e_idx: int) -> int:
        boundary_idx, pivot = s_idx-1, points[e_idx][0]**2 + points[e_idx][1]**2
        for i in range(s_idx, e_idx+1):
            if points[i][0]**2 + points[i][1]**2 < pivot:
                boundary_idx += 1
                points[boundary_idx], points[i] = points[i], points[boundary_idx]
        points[e_idx], points[boundary_idx+1] = points[boundary_idx+1], points[e_idx]
        return boundary_idx+1 