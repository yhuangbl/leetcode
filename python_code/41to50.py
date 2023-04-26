class Solution45:
    # DP
    def jump(self, nums: List[int]) -> int:
        n = len(nums)
        num_jumps = [None for _ in range(n)] # 1D array of length n
        num_jumps[n-1] = 0
        for i in range(n-2, -1, -1):
            biggest = nums[i]
            if biggest >= n-1:
                num_jumps[i] = 1
            else:
                new_jumps = self.getMinJumps(num_jumps, i, biggest, n)
                num_jumps[i] = 1 + new_jumps if new_jumps is not None else None # None means we cannot get to the end
        return num_jumps[0]
    
    def getMinJumps(self, num_jumps: List[int], start: int, step: int, n: int) -> int: 
        curr_min = None
        for i in range(1, step+1):
            next_i = start+i
            if next_i < n:
                curr = num_jumps[start+i]
                if curr is not None and (curr_min is None or curr < curr_min):
                    curr_min = curr
        return curr_min

class Solution46:
    def is_valid(self, state: List[int], target_len: int) -> bool: 
        return len(state) == target_len
    
    def get_candidates(self, state: List[int], nums: Set[int]) -> Set[int]:
        return nums - set(state)
    
    # backtracking就是recursion的dfs，需要一個helper function for recursion
    def search(self, state: List[int], nums_set: Set[int], target_len: int, ans: List[List[int]]): 
        if self.is_valid(state, target_len):
            ans.append(state.copy())
        
        for n in self.get_candidates(state, nums_set):
            state.append(n)
            self.search(state, nums_set, target_len, ans)
            state.pop()

    def permute(self, nums: List[int]) -> List[List[int]]:
        target_len = len(nums)
        nums_set = set(nums)
        ans = []
        state = []

        self.search(state, nums_set, target_len, ans)

        return ans

from collections import Counter

class Solution47:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        ans = []
        target_map, state = Counter(nums), []
        self.search(target_map, state, ans)
        return ans
    
    def search(self, target_map: dict[int, int], state: List[int], ans: List[List[int]]):
        if self.isValid(target_map, state):
            ans.append(state)
            return
        
        for c in self.getCandidates(target_map, state):
            self.search(target_map, state + [c], ans)
    
    def isValid(self, target_map: dict[int, int], state: List[int]) ->bool:
        return target_map == Counter(state)

    def getCandidates(self, target_map: dict[int, int], state: List[int]) -> List[int]:
        curr_map = Counter(state)
        return [t for t in target_map if t not in curr_map or curr_map[t] < target_map[t]]
