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

class Solution:
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
