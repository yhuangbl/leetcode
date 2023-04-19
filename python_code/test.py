from queue import PriorityQueue

def kClosest(points, k):
        q = PriorityQueue() # max heap
        for p in points:
            print(p)
            dist = p[0]**2 + p[1]**2
            if q.qsize() < k or q.queue[0][0]*-1 > dist: #if smaller than current max
                print("should add")
                q.get()
                print("remove first")
                q.put((dist*-1, p))
                print("now put")
        return [q.get()[1] for _ in range(k)]

kClosest([[1,3],[-2,2]], 1)