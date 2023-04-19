class Solution1249:
    def minRemoveToMakeValid(self, s: str) -> str:
        parentheses = []
        str_len = len(s)
        for i in range(str_len):
            c = s[i]
            if c == ')':
                last_p = parentheses[-1] if parentheses else None
                if last_p and last_p[0] == '(':
                    parentheses.pop()
                else:
                    parentheses.append((c, i))
            elif c == '(':
                parentheses.append((c, i))
        
        output = ""
        last_p_idx = 0 if parentheses else None
        parentheses_len = len(parentheses)
        for i in range(str_len):
            c = s[i]
            if c == ')' or c == '(': 
                if last_p_idx != None:
                    if last_p_idx < parentheses_len and parentheses[last_p_idx][1] == i:
                        last_p_idx += 1
                    else:
                        output += c
            else: 
                output += c

        return output