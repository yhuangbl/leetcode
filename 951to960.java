class Solution953 {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null) {
            return true;
        }
        int len = words.length;
        if (len == 0) {
            return true;
        }

        for (int i = 0; i < len - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int j = 0;
            int firstlen = first.length();
            int secondlen = second.length();
            for (; j < Math.min(firstlen, secondlen); j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    break;
                }
            }
            String orderSubstr = first.substring(0, j);
            for (int k = 0; k < orderSubstr.length() - 1; k++) {
                char firstC = orderSubstr.charAt(k);
                char secondC = orderSubstr.charAt(k + 1);
                if (order.indexOf(firstC) > order.indexOf(secondC)) {
                    return false;
                }
            }

            char firstC = (j < firstlen)? first.charAt(j): 0;
            char secondC = (j < secondlen)? second.charAt(j): 0;
            if (firstC != 0 && secondC == 0) {
                return false;
            }

            if (firstC != 0 && secondC != 0 && order.indexOf(firstC) > order.indexOf(secondC)) {
                return false;
            }
        }
        return true;
    }
}