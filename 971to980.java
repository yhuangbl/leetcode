class Solution973 {
    private class Pair {
        private int idx;
        private int dist;

        public Pair(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public int getIdx() {
            return idx;
        }

        public int getDist() {
            return dist;
        }
    }

    /**
     * Time comlexity: O(nlogn)
     * Time comlexity can be improved to O(nlogk) if we always maintain only
     * k elements in the priority queue
     * Space complexity: O(n) can be improved to O(k) using the same method
     */
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length;
        if (len == 0) {
            return new int[0][0];
        }

        int[][] result = new int[K][2];

        Comparator<Pair> pairComparator = new Comparator<Pair>() {
            @Override
            public int compare(Pair a, Pair b) {
                return a.dist - b.dist;
            }
        };
        PriorityQueue<Pair> closest = new PriorityQueue<Pair>(1, pairComparator);

        for (int i = 0; i < len; i++) {
            int x = points[i][0];
            int y = points[i][1];
            int dist = x * x + y * y;
            closest.add(new Pair(i, dist));
        }

        for (int i = 0; i < K; i++) {
            result[i] = points[closest.remove().getIdx()];
        }
        return result;
    }
}