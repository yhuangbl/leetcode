class Solution997:
    # Time: O(E); Space: O(n)
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        graph = { i: set() for i in range(1, n+1)} # key: int; value: Set[int]
        for t in trust:
            graph[t[0]].add(t[1])
        
        judges = {k for (k, v) in graph.items() if len(v) == 0}
        if len(judges) != 1:
            return -1

        (judge,) = judges
        for (k, v) in graph.items():
            if k != judge:
                if judge not in v:
                    return -1
        return judge