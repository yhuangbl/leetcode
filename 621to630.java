class Solution {
    /**
     * Time: O(n); Space: O(n)
     */
    public int leastInterval(char[] tasks, int n) {
        int len = tasks.length;
        if (len == 0) {
            return 0;
        }

        int maxCnt = -1;
        int maxTaskCnt = 0;
        Map<Character, Integer> taskCnt = new HashMap<Character, Integer>();
        for (Character task: tasks) {
            taskCnt.put(task, taskCnt.getOrDefault(task, 0) + 1);
            int newCnt = taskCnt.get(task);
            if (newCnt > maxCnt) {
                maxCnt = newCnt;
                maxTaskCnt = 1;
            }
            else if (newCnt == maxCnt) {
                maxTaskCnt++;
            }
        }
        // 這裡要取Max! 因為假如最後的是沒有任何限制的，我們算的方式會比原來的小
        return Math.max(len, (maxCnt - 1) * (n + 1) + maxTaskCnt);
    }
}