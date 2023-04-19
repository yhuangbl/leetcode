class Solution755 {
    private int findLeft(int[] heights, int curr) {
        int idx = curr;
        while (idx - 1 >= 0) {
            int currH = heights[idx];
            int leftH = heights[idx - 1];
            if (leftH < currH) {
                idx--;
            } else if (leftH == currH) {
                int firstIdx = idx;
                int nextIdx = idx - 1;
                while (nextIdx >= 0 && heights[firstIdx] == heights[nextIdx]) {
                    nextIdx--;
                }
                if (nextIdx == -1 || heights[nextIdx] > heights[firstIdx]) {
                    return firstIdx;
                } else {
                    idx = nextIdx;
                }
            } else {
                break;
            }
        }
        return idx;
    }

    private int findRight(int[] heights, int curr, int len) {
        int idx = curr;
        while (idx + 1 < len) {
            int currH = heights[idx];
            int rightH = heights[idx + 1];
            if (rightH < currH) {
                idx++;
            } else if (rightH == currH) {
                int firstIdx = idx;
                int nextIdx = idx + 1;
                while (nextIdx < len && heights[firstIdx] == heights[nextIdx]) {
                    nextIdx++;
                }
                if (nextIdx == len || heights[nextIdx] > heights[firstIdx]) {
                    return firstIdx;
                } else {
                    idx = nextIdx;
                }
            } else {
                break;
            }
        }
        return idx;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     */
    public int[] pourWater(int[] heights, int V, int K) {
        int len = heights.length;
        while (V > 0) {
            int leftIdx = findLeft(heights, K);
            if (leftIdx == K) {
                int rightIdx = findRight(heights, K, len);
                heights[rightIdx]++;
            } else {
                heights[leftIdx]++;
            }
            V--;
        }
        return heights;
    }
}