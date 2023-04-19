class Solution53 {
    /**
     * Dynamic programming
     * <key> if it's continous, the dp array will be defined as t[i] = <target>
     *     that ends at xi
     * Time: O(n); Space: O(n)
     */
    public int maxSubArray(int[] nums) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len == 0)
            return Integer.MIN_VALUE;

        int sums[] = new int[len];
        sums[0] = nums[0];
        int largest = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = Math.max(nums[i] + sums[i-1], nums[i]);
            largest = Math.max(sums[i], largest);
        }
        return largest;
    }

    /**
     * Dynamic programming
     * Time: O(n); Space: O(1)
     */
    public int maxSubArray(int[] nums) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len == 0)
            return Integer.MIN_VALUE;

        int prevSum = nums[0];
        int largest = nums[0];
        for (int i = 1; i < len; i++) {
            int sum = Math.max(nums[i] + prevSum, nums[i]);
            largest = Math.max(sum, largest);
            prevSum = sum;
        }
        return largest;
    }

    /**
     * Divide and conquer
     * Time: O(nlogn)
     */
    private int maxSubArrayCross(int[] nums, int startIdx, int midIdx, int endIdx) {
        int right = nums[midIdx+1];
        int rightMax = right;
        for (int i = midIdx+2; i <= endIdx; i++) {
            right += nums[i];
            if (right > rightMax)
                rightMax = right;
            // 不能夠一小於current max就break因為可能後面會出現更大的elements
        }
        // 要包含中間：從中間倒推
        int left = nums[midIdx];
        int leftMax = left;
        for (int i = midIdx-1; i >= startIdx; i--) {
            left += nums[i];
            if (left > leftMax)
                leftMax = left;
        }
        return leftMax + rightMax;
    }

    private int maxSubArrayHelper(int[] nums, int startIdx, int endIdx) {
        if (startIdx == endIdx)
            return nums[startIdx];
        if (endIdx == startIdx+1) {
            int sum = nums[startIdx] + nums[endIdx];
            return Math.max(Math.max(nums[startIdx], nums[endIdx]), sum);
        }

        int mid = (startIdx + endIdx) / 2;
        int leftArray = maxSubArrayHelper(nums, startIdx, mid);
        int rightArray = maxSubArrayHelper(nums, mid+1 , endIdx);
        int crossing = maxSubArrayCross(nums, startIdx, mid, endIdx);
        // 沒有三個的Math.max就先取前兩個的max再跟最後一個比
        return Math.max(Math.max(leftArray, rightArray), crossing);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len == 0)
            return Integer.MIN_VALUE;

        return maxSubArrayHelper(nums, 0, len-1);
    }
}

class Solution54 {
    private boolean allVisited(boolean[][] visited, int collen, int rowlen, int x, int y) {
        if (x + 1 < collen && !visited[y][x + 1]) {
            return false;
        }
        if (x - 1 >= 0 && !visited[y][x - 1]) {
            return false;
        }
        if (y + 1 < rowlen && !visited[y + 1][x]) {
            return false;
        }
        if (y - 1 >= 0 && !visited[y - 1][x]) {
            return false;
        }
        return true;
    }

    /**
     * Time: O(m*n); Space: O(m*n)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int rowlen = matrix.length;
        if (rowlen == 0) {
            return Collections.emptyList();
        }
        int collen = matrix[0].length;
        if (collen == 0) {
            return Collections.emptyList();
        }

        int dirIdx = 0;
        // direction: 0 -> right; 1 -> down; 2: left; 3: up
        List<Integer> order = new ArrayList<Integer>();
        // current index
        int x = 0;
        int y = 0;
        // visited array
        boolean[][] visited = new boolean[rowlen][collen];

        while (true) {
            visited[y][x] = true;
            order.add(matrix[y][x]);

            // check breaking condition: all directions visited
            if (allVisited(visited, collen, rowlen, x, y)) {
                break;
            }

            if (dirIdx == 0) { // move right
                if (x + 1 >= collen || visited[y][x + 1]) {
                    dirIdx++;
                    y++;
                } else {
                    x++;
                }
            } else if (dirIdx == 1) { // move down
                if (y + 1 >= rowlen || visited[y + 1][x]) {
                    dirIdx++;
                    x--;
                } else {
                    y++;
                }
            } else if (dirIdx == 2) { // move left
                if (x - 1 < 0 || visited[y][x - 1]) {
                    dirIdx++;
                    y--;
                } else {
                    x--;
                }

            } else if (dirIdx == 3) { // move up
                if (y - 1 < 0 || visited[y - 1][x]) {
                    dirIdx = 0;
                    x++;
                } else {
                    y--;
                }
            }
        }
        return order;
    }
}

class Solution55 {
    /**
     * Greedy algorithm
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return true;
        }

        int reach = 0;
        for (int i = 0; i < len; i++) {
            if (reach >= len - 1) {
                return true;
            }
            if (i > reach) {
                return false;
            }
            reach = Math.max(reach, i + nums[i]);
        }
        return reach >= len - 1;
    }
}

class Solution56 {
    /**
     * Time: O(n^2); Space: O(n)
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }
        int len = intervals.length;
        if (len == 0) {
            return intervals;
        }
        if (!(intervals[0] instanceof int[])) {
            return intervals;
        }

        Comparator<int[]> arrayComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        Arrays.sort(intervals, arrayComparator);

        boolean[] merged = new boolean[len];
        List<int[]> mergedIntervals = new ArrayList<int[]>();
        for (int i = 0; i < len; i++) {
            if (!merged[i]) {
                int start = intervals[i][0];
                int end = intervals[i][1];
                int mergeEnd = end;
                for (int j = i + 1; j < len ;j++) {
                    int newStart = intervals[j][0];
                    if (newStart >= start && newStart <= end) {
                        mergeEnd = Math.max(mergeEnd, intervals[j][1]);
                        end = mergeEnd;
                        merged[j] = true;
                    }
                    else {
                        break;
                    }
                }
                mergedIntervals.add(new int[]{start, mergeEnd});
            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }

    /**
     * improve version!
     * Time: O(nlogn); Space: O(n)
     * 因為sort by start time了，所以我們只需要知道前一個interval就好
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null) {
            return null;
        }
        int len = intervals.length;
        if (len == 0) {
            return intervals;
        }
        if (!(intervals[0] instanceof int[])) {
            return intervals;
        }

        Comparator<int[]> arrayComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        };
        Arrays.sort(intervals, arrayComparator);

        boolean[] merged = new boolean[len];
        List<int[]> mergedIntervals = new ArrayList<int[]>();
        for (int i = 0; i < len; i++) {
            if (mergedIntervals.isEmpty()) {
                mergedIntervals.add(intervals[i]);
            }
            else {
                int[] lastInterval = mergedIntervals.get(mergedIntervals.size() - 1);
                int lastStart = lastInterval[0];
                int lastEnd = lastInterval[1];
                int currStart = intervals[i][0];
                if (currStart >= lastStart && currStart <= lastEnd) {
                    lastInterval[1] = Math.max(lastEnd, intervals[i][1]);
                }
                else {
                    mergedIntervals.add(intervals[i]);
                }

            }
        }
        return mergedIntervals.toArray(new int[mergedIntervals.size()][2]);
    }
}