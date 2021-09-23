class Solution253 {
    /**
     * Time: O(n^2); Space: O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        List<Integer> endTimes = new ArrayList<Integer>();

        Comparator<int[]> timeComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int startDiff = a[0] - b[0];
                if (startDiff == 0) {
                    return a[1] - b[1];
                }
                return startDiff;
            }
        };

        Arrays.sort(intervals, timeComparator);
        int len = intervals.length;
        for (int i = 0; i< len; i++) {
            boolean found = false;
            int start = intervals[i][0];
            int end = intervals[i][1];
            for (int j = 0; j < endTimes.size(); j++) {
                if (endTimes.get(j) <= start) {
                    endTimes.set(j, end);
                    found = true;
                    break;
                }
            }
            if (!found) {
                endTimes.add(end);
            }
        }
        return endTimes.size();
    }

    /**
     * Time: O(nlogn) Because we use prioirty queue / heap
     * Space: O(n)
     */
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();

        Comparator<int[]> timeComparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                int startDiff = a[0] - b[0];
                if (startDiff == 0) {
                    return a[1] - b[1];
                }
                return startDiff;
            }
        };

        Arrays.sort(intervals, timeComparator);
        int len = intervals.length;
        for (int i = 0; i< len; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            Integer earliestEnd = endTimes.peek();
            if (earliestEnd != null && earliestEnd <= start) {
                endTimes.remove();
                endTimes.add(end);
            }
            else {
                endTimes.add(end);

            }
        }
        return endTimes.size();
    }
}