class Solution695 {
    /**
     * Key: use DFS instead of BFS (why??)
     * Optimization: 
     * - Time
     *    - DFS
     *    - Validate before adding to stack
     * - Space
     *    - grid value = 2 instead of visited 2D array (because it says grid value can only be 0 or 1)
     */
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, areaOfIsland(grid, i, j, m, n));
                }
            }
        }
        return maxArea;
    }

    public int areaOfIsland(int[][] grid, int sr, int sc, int m, int n) {
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        s.push(new Pair<>(sr, sc));

        int area = 0;
        while (!s.isEmpty()) {
            Pair<Integer, Integer> curr = s.pop();
            int r = curr.getKey();
            int c = curr.getValue(); 
            // need this because when the node was first added, it might not be seen yet
            // However, during DFS traversal of some previous node's descendants, it might be seen now 
            // This is a graph not a tree!
            if (grid[r][c] == 1) { 
                area++;
                grid[r][c] = 2;
            }

            if (r + 1 < m && grid[r + 1][c] == 1) {
                s.add(new Pair<>(r + 1, c));
            }
            if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                s.add(new Pair<>(r - 1, c));
            }
            if (c + 1 < n && grid[r][c + 1] == 1) {
                s.add(new Pair<>(r, c + 1));
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                s.add(new Pair<>(r, c - 1));
            }

        }
        return area;
    }
}