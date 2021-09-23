class Solution438 {
    private boolean isPatternEqual(int[] pattern1, int[] pattern2) {
        for (int i = 0; i < 26; i++) {
            if (pattern1[i] != pattern2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time: O(s* max(26, p))
     * Space: O(26)
     * HashMap記frequency的話很慢！
     * 因為Time:O(s * plogp)
     */
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        if (slen == 0 || plen == 0 || slen < plen) {
            return Collections.emptyList();
        }
        int[] pattern = new int[26];
        for (int i = 0; i < plen; i++) {
            pattern[p.charAt(i) - 'a']++;
        }

        List<Integer> matches = new ArrayList<Integer>();
        for (int i = 0; i <= slen - plen; i++) {
            int[] currPattern = new int[26];
            for (int j = 0; j < plen; j++) {
                currPattern[s.charAt(i + j) - 'a']++;
            }
            if (isPatternEqual(pattern, currPattern)) {
                matches.add(i);
            }
        }
        return matches;
    }

    /**
     * Siding window
     * Time: O(S)
     * Space: O(P)
     */
    public List<Integer> findAnagrams(String s, String p) {
        int slen = s.length();
        int plen = p.length();
        if (slen == 0 || plen == 0 || slen < plen) {
            return Collections.emptyList();
        }

        int start = 0;
        int end = 0;
        List<Integer> matches = new ArrayList<Integer>(); // save results

        // create a pattern
        Map<Character, Integer> pattern = new HashMap<Character, Integer>();
        for (char c: p.toCharArray()) {
            pattern.put(c, pattern.getOrDefault(c, 0) + 1);
        }
        int count = pattern.size(); // how many distinct character to record

        // sliding windows algorithm
        while (end < slen) {
            char c = s.charAt(end);
            if (pattern.containsKey(c)) {
                int newCnt = pattern.get(c) - 1;
                pattern.put(c, newCnt);
                if (newCnt == 0) {
                    count--;
                }
            }
            end++;

            while (count == 0) {
                if (end - start == plen) {
                    matches.add(start);
                }
                char cS = s.charAt(start);
                if (pattern.containsKey(cS)) {
                    int newCntS = pattern.get(cS) + 1;
                    pattern.put(cS, newCntS);
                    if (newCntS > 0) {
                        count++;
                    }
                }
                start++;
            }
        }

        return matches;
    }
}
