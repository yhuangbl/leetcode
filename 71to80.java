class Solution75 {
    /**
     * Two pass: counting sort
     * time: O(n); Space O(1)
     */
    public void sortColors1(int[] nums) {
        int[] count = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int cat = 0;
        int remain = count[cat];
        for (int i = 0; i < nums.length; i++) {
            // need to keep checking until there is a category with count
            while (remain == 0) {
                remain = count[++cat];
            }
            nums[i] = cat;
            remain--;
        }
    }

    /**
     * One pass: 3 pointers
     * time: O(n); Space O(1)
     * https://www.cnblogs.com/springfor/p/3872313.html
     */
    public void sortColors2(int[] nums) {
        int not0 = 0;
        int not2 = nums.length - 1;

        while (nums[not0] == 0)
            not0++;
        while (nums[not2] == 2)
            not2--;

        int idx = not0;
        while (idx <= not2) {
            if (nums[idx] == 0) {
                nums[idx] = nums[not0];
                nums[not0] = 0;
                not0++;
                idx++;
            }
            else if (nums[idx] == 2) {
                nums[idx] = nums[not2];
                nums[not2] = 2;
                not2--;
            }
            else {
                idx++;
            }
        }
    }
}

class Solution76 {
    /**
     * Key: O(n) + substring -> use sliding window
     * Time: O(n)
     * Space: O(n)
     */
    public String minWindow(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        int resultStart = -1;
        int resultLen = Integer.MAX_VALUE;

        if (tlen > slen) {
            return "";
        }

        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        for (char c: t.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        int counter = freq.size();

        int start = 0, end = 0;

        while (end < slen) {
            char c = s.charAt(end);
            if (freq.containsKey(c)) {
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) == 0) {
                    counter--;
                }
            }
            end++;

            while (counter == 0) {
                char startC = s.charAt(start);
                if (freq.containsKey(startC)) {
                    freq.put(startC, freq.get(startC) + 1);
                    if (freq.get(startC) > 0) {
                        counter++;
                    }
                }

                int currLen = end - start;
                if (currLen < resultLen) {
                    resultLen = currLen;
                    resultStart = start;
                }

                start++;
            }
        }
        return (resultStart == -1)? "": s.substring(resultStart, resultStart + resultLen);
    }
}

class Solution78 {
    /**
     * 另外一種recursion的方式可以看這裡：
     * https://github.com/grandyang/leetcode/issues/78
     * 可是順序不對
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(Collections.emptyList());

        if (nums == null)
            return result;
        int len = nums.length;
        if (len == 0)
            return result;

        for (int i = 0; i < len; i++) {
            List<List<Integer>> newSubsets = new ArrayList<List<Integer>>();
            for (int j = 0; j < result.size(); j++) {
                List<Integer> subset = new ArrayList<Integer>(result.get(j));
                subset.add(nums[i]);
                Collections.sort(subset);
                newSubsets.add(subset);
            }
            result.addAll(newSubsets);
        }

        return result;
    }
}

class Solution79 {
    /**
     * Performance improvement
     * int[] 比 List<Integer>快
     * 不用 left || right || up || down, 任何一個true就可以return了
     */
    private boolean existHelper(char[][] board, String word, int row, int col, int rowNum, int colNum, Stack<List<Integer>> used) {
        int wordlen = word.length();
        if (wordlen == 0) {
            return true;
        }

        if (row >= rowNum || col >= colNum || row < 0 || col < 0) {
            return false;
        }

        if (word.charAt(0) == board[row][col]) {
            String subString = word.substring(1, wordlen);
            used.push(Arrays.asList(row, col));

            List<Integer> leftIdx = Arrays.asList(row, col-1);
            boolean left = false;
            if (!used.contains(leftIdx)) {
                left = existHelper(board, subString, row, col-1, rowNum, colNum, used);
            }

            List<Integer> rightIdx = Arrays.asList(row, col+1);
            boolean right = false;
            if (!used.contains(rightIdx)) {
                right = existHelper(board, subString, row, col+1, rowNum, colNum, used);
            }

            List<Integer> upIdx = Arrays.asList(row-1, col);
            boolean up = false;
            if (!used.contains(upIdx)) {
                up = existHelper(board, subString, row-1, col, rowNum, colNum, used);
            }

            List<Integer> downIdx = Arrays.asList(row+1, col);
            boolean down = false;
            if (!used.contains(downIdx)) {
                down = existHelper(board, subString, row+1, col, rowNum, colNum, used);
            }
            boolean result = left || right || up || down;
            if (!result) {
                used.pop();
            }
            return result;
        }
        return false;
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || word == null) {
            return false;
        }

        int wordlen = word.length();
        if (wordlen == 0) {
            return false;
        }
        int rowNum = board.length;
        if (rowNum <= 0) {
            return false;
        }
        if (!(board[0] instanceof char[])) {
            return false;
        }
        int colNum = board[0].length;

        char first = word.charAt(0);
        boolean result = false;

        Stack<List<Integer>> used = new Stack<List<Integer>>();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                result = result || existHelper(board, word, i, j, rowNum, colNum, used);
                if (result) {
                    List<List<Integer>> order = new ArrayList(used);
                    for (List<Integer> point: order) {
                        System.out.println(point.get(0) + " " + point.get(1));
                    }
                    return true;
                }
            }
        }

        return false;
    }
}