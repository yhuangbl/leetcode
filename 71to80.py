class Solution77: # combination

    # time complexity: O(k * C(n,k))
    # first level C(n, 1) + second level C(n, 2) + third level C(n, 3) + ... + C(n, k)
    def combine(self, n: int, k: int) -> List[List[int]]:
        return self.search([], [], n, k)
    
    def is_valid(self, state: list, k: int) -> bool: 
        return len(state) == k 
    
    def get_candidates(self, state: list, n: int, k: int) -> List[int]:
        if len(state) == k:
            return []

        candidates = []
        for i in range(1 if len(state) == 0 else state[-1], n+1):
            if i not in state:
                candidates.append(i)
        return candidates
    
    def search(self, state: list, ans: List[List[int]], n, k) -> List[List[int]]:
        if self.is_valid(state, k): 
            ans.append(state.copy())
        
        for candidate in self.get_candidates(state, n, k):
            state.append(candidate)
            self.search(state, ans, n, k)
            state.remove(candidate)

        return ans
##############################################################
    def combine(self, n: int, k: int) -> List[List[int]]:
        print(n, k)
        if k == 0:
            return [list()]
        if n == k: 
            return [list(range(1, n+1))]
        # cannot use append here to replace x + [n] because append doesn't return value
        return self.combine(n-1, k) + [x + [n] for x in self.combine(n-1, k-1)]