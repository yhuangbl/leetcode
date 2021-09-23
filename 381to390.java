class Solution387 {
    /**
     * Time: O(n); Space: O(n)
     */
    public int firstUniqChar(String s) {
        int idx = Integer.MAX_VALUE;
        Map<Character, Integer> unique = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (unique.containsKey(c)) {
                unique.put(c, -1);
            }
            else {
                unique.put(c, i);
            }
        }

        for (Map.Entry<Character, Integer> entry: unique.entrySet()) {
            int val = entry.getValue();
            if (val != -1) {
                idx = Math.min(idx, val);
            }
        }

        if (idx == Integer.MAX_VALUE) {
            idx = -1;
        }
        return idx;
    }
}