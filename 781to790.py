class Solution784:
    def is_valid(self, state: str, target_len: int) -> bool:
        return len(state) == target_len
    
    def get_candidates(self, state: str, s: str, target_len: int) -> List[str]:
        curr_idx = len(state)
        if target_len == curr_idx:
            return [] 
        curr = s[curr_idx]
        if curr.isalpha():
            return [curr.lower(), curr.upper()]
        return [curr]
    
    def search(self, state: str, target_len: int, s: str, ans: List[str]):
        if self.is_valid(state, target_len):
            ans.append(state)
        
        for c in self.get_candidates(state, s, target_len):
            state += c
            self.search(state, target_len, s, ans)
            state = state[:-1] # -1 means the last element in the iterable

    def letterCasePermutation(self, s: str) -> List[str]:
        target_len = len(s)
        ans = []
        state = ""

        self.search(state, target_len, s, ans)
        return ans