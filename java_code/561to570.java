class Solution567 {
    /**
     * Sliding window method
     * Time: O(s2.length()); Space: O(s1.length())
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> target_char_count = new HashMap<Character, Integer>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (target_char_count.containsKey(c)) {
                int new_value = target_char_count.get(c) + 1;
                target_char_count.put(c, new_value);
            } else {
                target_char_count.put(c, 1);
            }
        }

        Map<Character, Integer> used_char_count = new HashMap<Character, Integer>();
        int missing_count = s1.length();
        for (int j = 0; j < s1.length(); j++) {
            char c = s2.charAt(j);
            if (target_char_count.containsKey(c)) {
                boolean has_char = used_char_count.containsKey(c);
                if (has_char) {
                    int new_used_count = used_char_count.get(c) + 1;
                    used_char_count.put(c, new_used_count);
                    if (new_used_count <= target_char_count.get(c)) {
                        missing_count--;
                    }
                } else {
                    used_char_count.put(c, 1);
                    missing_count--;
                }
            }
        }
        if (missing_count == 0) {
            return true;
        }

        // sliding window
        int left = 0;
        int right = left + s1.length();
        while (right < s2.length()) {
            char left_char = s2.charAt(left);
            if (used_char_count.containsKey(left_char)) {
                int new_used_count = used_char_count.get(left_char) - 1;
                used_char_count.put(left_char, new_used_count);
                if (new_used_count < target_char_count.get(left_char)) {
                    missing_count++;
                }
            }
            char right_char = s2.charAt(right);
            if (target_char_count.containsKey(right_char)) {
                int new_used_count = used_char_count.containsKey(right_char) ? used_char_count.get(right_char) + 1 : 1;
                used_char_count.put(right_char, new_used_count);
                if (new_used_count <= target_char_count.get(right_char)) {
                    missing_count--;
                }
            }
            if (missing_count == 0) {
                return true;
            }
            left++;
            right++;
        }

        return false;
    }
}