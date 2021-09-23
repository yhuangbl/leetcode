class Solution680 {
    private boolean verifyPalindrome(String s, int len) {
        int left = 0;
        int right = len - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private List<Integer> getDiffPalindrome(String s, int len) {
        int left = 0;
        int right = len - 1;
        List<Integer> diff = null;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                diff = Arrays.asList(left, right);
                return diff;
            }
            left++;
            right--;
        }
        return diff;
    }

    private String removeIdxFromString(String s, int len, int idx) {
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

    /**
     * Time: O(n)
     */
    public boolean validPalindrome(String s) {
        int len = s.length();

        // dont delete
        List<Integer> diff = getDiffPalindrome(s, len);
        if (diff == null) {
            return true;
        }
        // delete one
        boolean result = false;
        for (int d: diff) {
            String newStr = removeIdxFromString(s, len, d);
            result = result || verifyPalindrome(newStr, len - 1);
        }
        return result;
    }
}