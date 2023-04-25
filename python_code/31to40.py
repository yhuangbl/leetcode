class Solution33:
    # Time: O(logn) => Use binary search to find pivot/min first
    def search(self, nums: List[int], target: int) -> int:
        min_idx = self.searchMin(nums)
        total_len = len(nums)
        if target > nums[total_len-1]:
            return self.searchRange(nums, target, 0, min_idx-1)
        else:
            return self.searchRange(nums, target, min_idx, total_len)
    
    def searchMin(self, nums: List[int]) -> int:
        s_idx, e_idx = 0, len(nums)-1
        last = nums[e_idx]
        if nums[s_idx] < last: # no rotation
            return s_idx
        while s_idx <= e_idx:
            m_idx = self.getMidIdx(s_idx, e_idx)
            if m_idx-1 >= 0 and nums[m_idx] < nums[m_idx-1]:
                return m_idx
            if nums[m_idx] > last:
                s_idx = m_idx+1
            else:
                e_idx = m_idx-1
        return -1
    
    # normal binary search
    def searchRange(self, nums:List[int], target: int, s_idx: int, e_idx: int) -> int:
        while s_idx <= e_idx:
            m_idx = self.getMidIdx(s_idx, e_idx)
            curr = nums[m_idx]
            if curr == target:
                return m_idx
            elif target > curr:
                s_idx = m_idx+1
            else:
                e_idx = m_idx-1
        return -1
    
    def getMidIdx(self, s_idx, e_idx):
        return s_idx + (e_idx-s_idx)//2

class Solution34:
    # Time: O(logn) => Binary search with special matching conditions
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        left_idx = self.searchBoundary(nums, target, 0, True)
        if left_idx == -1:
            return [-1, -1]
        right_idx = self.searchBoundary(nums, target, left_idx, False)
        return [left_idx, right_idx]
    
    def searchBoundary(self, nums: List[int], target: int, start_idx: int, is_left: bool) -> int:
        total_len = len(nums)
        s_idx, e_idx = start_idx, total_len-1
        while s_idx <= e_idx:
            m_idx = s_idx + (e_idx-s_idx)//2
            curr = nums[m_idx]
            neighbor_idx = m_idx-1 if is_left else m_idx+1
            if curr == target and (neighbor_idx < 0 or neighbor_idx >= total_len):
                return m_idx
            neighbor = nums[neighbor_idx]
            if curr == target:
                if neighbor != target: 
                    return m_idx
                else: # neighbor == target
                    if is_left:
                        e_idx = m_idx-1
                    else:
                        s_idx = m_idx+1
            elif curr > target:
                e_idx = m_idx-1
            else:
                s_idx = m_idx+1
        return -1

class Solution39:
    def combinationSum(self, candidates: List[int], target: int) -> List[List[int]]:
        ans, state = [], []
        self.search(candidates, state, 0, target, ans)
        return ans
    
    def search(self, candidates: List[int], state: List[int], curr_sum: int, target: int, ans: List[List[int]]):
        if self.isValid(curr_sum, target):
            ans.append(state)
            return
        
        difference = target - curr_sum
        if difference >= 0:
            for n in self.getNexts(candidates, state, difference):
                self.search(candidates, state + [n], curr_sum + n, target, ans )
    
    def isValid(self, curr_sum: int, target: int) -> bool: 
        return curr_sum == target
    
    def getNexts(self, candidates: List[int], state: List[int], difference: int) -> List[int]:
        last = state[-1] if len(state) > 0 else 0
        return [c for c in candidates if c >= last and c <= difference]

class Solution40:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        ans, state = [], []
        candidates_map = Counter(candidates)
        self.search(candidates_map, state, 0, target, ans)
        return ans
    
    def search(self, candidates_map: dict[int, int], state: List[int], curr_sum: int, target: int, ans: List[List[int]]):
        if self.isValid(curr_sum, target):
            ans.append(state)
            return
        
        difference = target - curr_sum
        if difference > 0:
            for n in self.getNext(candidates_map, state, difference):
                self.search(candidates_map, state+[n], curr_sum+n, target, ans)
    
    def isValid(self, curr_sum: int, target: int) -> bool:
        return curr_sum == target
    
    def getNext(self, candidates_map: dict[int, int], state: List[int], difference: int) -> List[int]:
        state_map = Counter(state)
        last = state [-1] if len(state) > 0 else 0
        return [c for c, count in candidates_map.items() if c <= difference and c >= last and (state_map[c] if c in state_map else 0) < count  ]
