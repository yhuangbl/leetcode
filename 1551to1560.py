from collections import defaultdict

class Solution1557:
    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        all_nodes = set(range(n))
        graph = defaultdict(list)
        for e in edges:
            graph[e[0]].append(e[1])
        
        for i in range(n):
            if i in all_nodes:
                stack = [] + graph[i]
                self.dfs(stack, graph, all_nodes)
        
        return all_nodes
    
    def dfs(self, stack: List[int], graph: Dict[int, list], all_nodes: Set[int]):
        while stack:
            front = stack.pop()
            if front in all_nodes:
                all_nodes.remove(front)
            nexts = graph[front]
            for n in nexts:
                if n in all_nodes:
                    stack.append(n