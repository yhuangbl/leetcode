class Solution33 {
    /**
     * Time complexity: O(n) because of private search
     */
    private int search(int[] nums, int startIdx, int endIdx, int target) {
        for (int i = startIdx; i <= endIdx; i++) {
            if (target == nums[i]) {
                return i;
            }
        }
        return -1;
    }

    public int search1(int[] nums, int target) {
        // always remember to add corner cases
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            }
            else {
                return -1;
            }
        }

        int mid = (nums.length - 1) / 2;
        int first_start = nums[0];
        int first_end;
        int second_start;
        int second_end = nums[nums.length-1];

        while (true) {
            first_end = nums[mid];
            second_start = nums[mid+1];

            if (first_end < first_start) {
                mid--;
                continue;

            }
            else if (second_start > second_end) {
                mid++;
                continue;

            }
            else {
                break;
            }
        }

        if (target >= second_start && target <= second_end) {
            return search(nums, mid+1, nums.length-1, target);
        }
        else {
            return search(nums, 0, mid, target);
        }

    }

    /** 我是分隔線 **/
    private int binarySearch(int[] nums, int startIdx, int endIdx, int target) {
        if (startIdx == endIdx) { // write base case first
            if (nums[startIdx] == target) {
                return startIdx;
            }
            return -1;
        }
        int midIdx = (startIdx + endIdx) / 2;
        if (target == nums[midIdx]) { // be careful about index and value
            return midIdx;
        }
        else if (target > nums[midIdx]) {
            return binarySearch(nums, midIdx+1, endIdx, target);
        }
        else {
            return binarySearch(nums, startIdx, midIdx, target);
        }
    }

    /**
     * Time complexity: O(n) because find min is still O(n)
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        if (nums.length == 1) {
            if (nums[0] == target) {
                return 0;
            }
            else {
                return -1;
            }
        }

        int mid = (nums.length - 1) / 2;
        int first_start = nums[0];
        int first_end;
        int second_start;
        int second_end = nums[nums.length-1];

        while (true) {
            first_end = nums[mid];
            second_start = nums[mid+1];

            if (first_end < first_start) {
                mid--;
                continue;

            }
            else if (second_start > second_end) {
                mid++;
                continue;

            }
            else {
                break;
            }
        }

        if (target >= second_start && target <= second_end) {
            return binarySearch(nums, mid+1, nums.length-1, target);
        }
        else {
            return binarySearch(nums, 0, mid, target);
        }
    }

    /** 我是分隔線 **/
    private int binarySearch(int[] nums, int startIdx, int endIdx, int target) {
        if (startIdx == endIdx) {
            if (nums[startIdx] == target) {
                return startIdx;
            }
            return -1;
        }
        int midIdx = (startIdx + endIdx) / 2;
        if (target == nums[midIdx]) {
            return midIdx;
        }
        else if (target > nums[midIdx]) {
            return binarySearch(nums, midIdx+1, endIdx, target);
        }
        else {
            return binarySearch(nums, startIdx, midIdx, target);
        }
    }

    private int findMin(int[] nums, int startIdx, int endIdx) {
        while (startIdx < endIdx) {
            int mid = (startIdx + endIdx) / 2;
            if (nums[mid] > nums[endIdx])
                startIdx = mid + 1;
            else
                endIdx = mid;
        }
        return startIdx;
    }

    /**
     * 我是真正的O(logn) time complexity because I also use binary search to find
     * min index
     */
    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        if (nums.length == 1)
            return (nums[0] == target)? 0: -1;

        int endIdx = nums.length - 1;
        int minIdx = findMin(nums, 0, endIdx);

        if (minIdx == 0)
            return binarySearch(nums, 0, endIdx, target);
        else {
            if (target < nums[0])
                return binarySearch(nums, minIdx, endIdx, target);
            else
                return binarySearch(nums, 0, minIdx - 1, target);
        }
    }
}

class Solution34 {
    /**
     * My first version code
     * Time: O(longn); Space: O(1)
     * really not clean ugh ;( and very error-prone
     */
    public int[] searchRange1(int[] nums, int target) {
        int idxMin = 0;
        int idxMax = nums.length - 1;
        int[] result = {-1, -1};
        int savedMid = -1;
        int savedMin = -1;
        boolean toLarge = true;
        boolean setSmall = false;

        while (idxMin <= idxMax) {
            int mid = (idxMin + idxMax) / 2;
            if (nums[mid] < target) {
                idxMin = mid + 1;
            }
            else if (nums[mid] > target) {
                idxMax = mid - 1;
                if (nums[idxMin] > target && result[1] != -1) {
                    toLarge = true;
                    idxMax = savedMid -1;
                    idxMin = savedMin;
                }
            }
            else {
                if (result[0] == -1 && result[1] == -1) {
                    result[0] = mid;
                    result[1] = mid;
                    savedMid = mid;
                    savedMin = idxMin;
                }
                else if (result[1] != -1 && mid > result[1])
                    result[1] = mid;
                else if (result[0] != -1 && mid < result[0])
                    result[0] = mid;

                if (((mid == nums.length - 1) || nums[mid+1] > target) && result[0] != -1)
                    toLarge = false;
                if (toLarge)
                    idxMin = mid + 1;
                else {
                    if (!setSmall) {
                        idxMax = savedMid -1;
                        idxMin = savedMin;
                        setSmall = true;
                    }
                    else
                        idxMax =  mid - 1;

                }
            }
        }
        return result;
    }

    /**
     * Cleaner solution: use 2 binary search to find rightmost and leftmost
     */
    public int[] searchRange2(int[] nums, int target) {
        int idxMin = 0;
        int idxMax = nums.length - 1;
        int[] result = {-1, -1};

        // find left boundary
        while (idxMin <= idxMax) {
            int idxMid = (idxMin + idxMax) / 2;
            int mid = nums[idxMid];
            if (mid > target)
                idxMax = idxMid - 1;
            else if (mid < target)
                idxMin = idxMid + 1;
            else {
                result[0] = idxMid;
                idxMax = idxMid - 1;
            }
        }

        // find right boundary
        idxMin = 0;
        idxMax = nums.length - 1;
        while (idxMin <= idxMax) {
            int idxMid = (idxMin + idxMax) / 2;
            int mid = nums[idxMid];
            if (mid > target)
                idxMax = idxMid - 1;
            else if (mid < target)
                idxMin = idxMid + 1;
            else {
                result[1] = idxMid;
                idxMin = idxMid + 1;
            }
        }

        return result;
    }
}

class Solution36 {
    public boolean isValidSudoku(char[][] board) {
        if (board == null) return false;

        int len = 9;

        List<Set<Character>> rowSet = new ArrayList<Set<Character>>();
        for (int i = 0; i < len; i++) {
            rowSet.add(new HashSet<Character>());
        }
        List<Set<Character>> colSet = new ArrayList<Set<Character>>();
        for (int i = 0; i < len; i++) {
            colSet.add(new HashSet<Character>());
        }
        List<Set<Character>> blockSet = new ArrayList<Set<Character>>();
        for (int i = 0; i < len; i++) {
            blockSet.add(new HashSet<Character>());
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (!(c == '.')) {
                    Set<Character> row = rowSet.get(i);
                    if (row.contains(c)) {
                        return false;
                    }
                    else {
                        row.add(c);
                    }

                    Set<Character> col = colSet.get(j);
                    if (col.contains(c)) {
                        return false;
                    }
                    else {
                        col.add(c);
                    }

                    int blockid = i / 3 * 3 + j / 3;
                    Set<Character> block = blockSet.get(blockid);
                    if (block.contains(c)) {
                        return false;
                    }
                    else {
                        block.add(c);
                    }
                }
            }
        }
        return true;
    }
}