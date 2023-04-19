class Solution301 {
    private boolean isValid(String s) {
        Stack<Character> parentheses = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                boolean wrong = true;
                if (parentheses.isEmpty()) {
                    return false;
                }
                while (!parentheses.isEmpty()) {
                    char p = parentheses.pop();
                    if (p == '(') {
                        wrong = false;
                        break;
                    }
                }
                if (wrong) {
                    return false;
                }
            }
            else {
                parentheses.push(c);
            }
        }

        while (!parentheses.isEmpty()) {
            if (parentheses.pop() == '(') {
                return false;
            }
        }
        return true;
    }

    private String deleteChar(String s, int idx, int len) {
        if (idx == 0) {
            return s.substring(1, len);
        }
        else if (idx == len - 1) {
            return s.substring(0, len - 1);
        }
        else {
            return s.substring(0, idx) + s.substring(idx + 1, len);
        }
    }

    public List<String> removeInvalidParentheses(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        if (len == 0) {
            return Arrays.asList("");
        }

        Queue<String> possibles = new LinkedList<String>();
        possibles.add(s);
        List<String> result = new ArrayList<String>();
        Set<String> checked = new HashSet<String>();
        checked.add(s);
        int validLen = -1;
        while (!possibles.isEmpty()) {
            String front = possibles.remove();
            int frontLen = front.length();
            if (isValid(front)) {
                if (validLen == -1) {
                    validLen = frontLen;
                    result.add(front);
                }
                else if (frontLen == validLen) {
                    if (!result.contains(front)) {
                        result.add(front);
                    }
                }
                else if (frontLen < validLen) {
                    break;
                }
            }
            else {
                for (int i = 0; i < frontLen; i++) {
                    char c = front.charAt(i);
                    if (c == '(' || c == ')') {
                        String newStr = deleteChar(front, i, frontLen);
                        if (!checked.contains(newStr)) {
                            possibles.add(newStr);
                            checked.add(newStr);
                        }
                    }
                }
            }
        }

        return result;
    }
}