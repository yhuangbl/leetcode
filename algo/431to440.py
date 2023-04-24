from collections import Counter, defaultdict

class Solution438:
    # Sliding window 
    def findAnagrams(self, s: str, p: str) -> List[int]:
        ans = []
        t, w = dict(Counter(p)), defaultdict(int)
        s_len, p_len = len(s), len(p)
        s_idx, e_idx = 0, p_len-1

        while e_idx < s_len:
            if s_idx == 0: # first window
                for i in range(s_idx, e_idx+1):
                    w[s[i]] += 1
            else: # Remove the last and add the new
                w[last] -= 1
                if w[last] == 0:
                    del w[last] # delete dictionary key value pair
                w[s[e_idx]] += 1
            if w == t:
                ans.append(s_idx)
            last = s[s_idx]
            s_idx, e_idx = s_idx+1, e_idx+1
        return ans