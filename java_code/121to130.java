class Solution121 {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;

        int revP = Integer.MIN_VALUE;
        int buyP = prices[0];
        for (int i = 0; i < prices.length; i++) {
            buyP = Math.min(buyP, prices[i]);
            revP = Math.max(revP, prices[i] - buyP);
        }
        return revP;
    }
}

class Solution122 {
    /**
     * Greedy
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0 || len == 1)
            return 0;

        int profit = 0;
        int buyp = -1;

        for (int i = 0; i < len; i++) {
            int left = (i-1 >= 0)? prices[i-1]: Integer.MAX_VALUE;
            int right = (i+1 < len)? prices[i+1]: Integer.MIN_VALUE;

            if (buyp == -1) {
                if (prices[i] <= left && prices [i] <= right)
                    buyp = prices[i];
            }
            else {
                if (prices[i] >= left && prices[i] >= right) {
                    profit += (prices[i] - buyp);
                    buyp = -1;
                }
            }
        }
        return profit;
    }
}

class Solution125 {
    public boolean isPalindrome(String s) {
        if (s == null) return true;
        int len = s.length();
        if (len == 0) return true;

        int start = 0;
        int end = len - 1;
        String str = s.toLowerCase();
        while (start <= end) {
            char startc = str.charAt(start);
            char endc = str.charAt(end);
            if (startc == ' ' || !Character.isLetterOrDigit(startc)) {
                start++;
                continue;
            }
            if (endc == ' '|| !Character.isLetterOrDigit(endc)) {
                end--;
                continue;
            }

            if (startc != endc) {
                return false;
            }

            start++;
            end--;
        }
        return true;
    }
}

class Solution130 {
    /**
     * 每個O dfs看有沒有到達邊邊
     */
    public void solve(char[][] board) {
        if (board == null)
            return;
        int rowlen = board.length;
        if (rowlen <= 0)
            return;
        if (!(board[0] instanceof char[])) // instanceof的用法：是一個operator
            return;
        int collen = board[0].length;

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == 'O') {
                    Stack<List<Integer>> os = new Stack<List<Integer>>();
                    Set<List<Integer>> checked = new HashSet<List<Integer>>();
                    List<Integer> curr = Arrays.asList(i, j);
                    os.push(curr);
                    checked.add(curr);

                    boolean shouldFlip = true;
                    while (!os.isEmpty()) {
                        List<Integer> top = os.pop();
                        int x = top.get(0);
                        int y = top.get(1);

                        List<Integer> right = Arrays.asList(x+1, y);
                        if (x + 1 < rowlen && board[x+1][y] == 'O' &&
                            !checked.contains(right)) {
                            os.push(right);
                            checked.add(right);
                        }
                        List<Integer> left = Arrays.asList(x-1, y);
                        if (x - 1 >= 0 && board[x-1][y] == 'O' &&
                            !checked.contains(left)) {
                            os.push(left);
                            checked.add(left);
                        }
                        List<Integer> down = Arrays.asList(x, y+1);
                        if (y + 1 < collen && board[x][y+1] == 'O' &&
                            !checked.contains(down)) {
                            os.push(down);
                            checked.add(down);
                        }
                        List<Integer> up = Arrays.asList(x, y-1);
                        if (y - 1 >= 0 && board[x][y-1] == 'O' &&
                            !checked.contains(up)) {
                            os.push(up);
                            checked.add(up);
                        }

                        if (x + 1 >= rowlen || x - 1 < 0 ||
                            y + 1 >= collen || y - 1 < 0) {
                            shouldFlip = false;
                            break;
                        }

                    }

                    if (shouldFlip) {
                        for (List<Integer> point: checked) {
                            int x = point.get(0);
                            int y = point.get(1);
                            board[x][y] = 'X';
                        }
                    }
                }
            }
        }
    }

    /**
     * 思路：換個方式想，只有跟邊緣相接的O是不適surrounded的，do dfs from there
     * 這樣可以快10倍！
     */
    public void solve2(char[][] board) {
        if (board == null)
            return;
        int rowlen = board.length;
        if (rowlen <= 0)
            return;
        if (!(board[0] instanceof char[])) // instanceof的用法：是一個operator
            return;
        int collen = board[0].length;

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == (rowlen - 1)
                    || j == 0 || j == (collen - 1))) {
                    Queue<List<Integer>> os = new LinkedList<List<Integer>>();
                    Set<List<Integer>> checked = new HashSet<List<Integer>>();
                    List<Integer> curr = Arrays.asList(i, j);
                    os.add(curr);
                    checked.add(curr);

                    while (!os.isEmpty()) {
                        List<Integer> front = os.remove();
                        int x = front.get(0);
                        int y = front.get(1);
                        board[x][y] = '$';

                        List<Integer> down = Arrays.asList(x + 1, y);
                        if (x + 1 < rowlen && board[x+1][y] == 'O' &&
                            !checked.contains(down)) {
                            os.add(down);
                            checked.add(down);
                        }
                        List<Integer> up = Arrays.asList(x - 1, y);
                        if (x - 1 >= 0 && board[x-1][y] == 'O' &&
                            !checked.contains(up)) {
                            os.add(up);
                            checked.add(up);
                        }
                        List<Integer> right = Arrays.asList(x, y + 1);
                        if (y + 1 < collen && board[x][y+1] == 'O' &&
                            !checked.contains(right)) {
                            os.add(right);
                            checked.add(right);
                        }
                        List<Integer> left = Arrays.asList(x, y - 1);
                        if (y - 1 >= 0 && board[x][y-1] == 'O' &&
                            !checked.contains(left)) {
                            os.add(left);
                            checked.add(left);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }

    /** 我是分隔線 **/
    private void dfsMark(char[][] board, int i, int j) {
        int rowlen = board.length;
        int collen = board[0].length;

        board[i][j] = '$';
        if (i > 0)
            dfsMark(board, i - 1, j);
        if (i + 1 < rowlen - 1)
            dfsMark(board, i + 1, j);
        if (j > 0)
            dfsMark(board, i, j - 1);
        if (j + 1 < collen - 1)
            dfsMark(board, i, j + 1);
    }

    /**
     * Recursion也可以！ 但有可能stackoverflow if board is super large
     */
    public void solve3(char[][] board) {
        if (board == null)
            return;
        int rowlen = board.length;
        if (rowlen <= 0)
            return;
        if (!(board[0] instanceof char[])) // instanceof的用法：是一個operator
            return;
        int collen = board[0].length;

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == 'O' && (i == 0 || i == (rowlen - 1)
                    || j == 0 || j == (collen - 1))) {
                    dfsMark(board, i, j);
                }
            }
        }

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }
}

class Solution127 {
    private boolean oneCharDiff(String target, String testWord, int len) {
        int diff = 0;
        for (int i = 0; i < len; i++) {
            if (target.charAt(i) != testWord.charAt(i)) {
                diff++;
                if (diff > 1)
                    return false;
            }
        }
        return (diff == 1);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) {
            return 0;
        }

        int len = beginWord.length();
        int wordListSize = wordList.size();
        Queue<String> levelWords = new LinkedList<String>();
        Set<String> used = new HashSet<String>();
        levelWords.add(beginWord);
        int levelCnt = 1;
        int nextLevelCnt = 0;
        int depth = 1;

        while (!levelWords.isEmpty()) {
            levelCnt--;
            String front = levelWords.remove();
            for (String word: wordList) {
                if (!used.contains(word) && oneCharDiff(front, word, len)) {
                    if (endWord.equals(word)) {
                        return depth + 1;
                    }
                    levelWords.add(word);
                    used.add(word);
                    nextLevelCnt++;
                    if (used.size() == wordListSize)
                        return 0;
                }
            }
            if (levelCnt == 0) {
                depth++;
                levelCnt = nextLevelCnt;
                nextLevelCnt = 0;
            }
        }
        return 0;
    }
}