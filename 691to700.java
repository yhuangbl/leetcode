class Solution695 {
    /**
     * Optimization: 
     * - Time
     *    - Validate before adding to stack
     *    - grid[i][j] == 2 before adding to stack to make sure each is only in stack/queue once
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
                    // areaOfIslandDFS or areaOfIslandBFS here
                    maxArea = Math.max(maxArea, areaOfIsland(grid, i, j, m, n));
                }
            }
        }
        return maxArea;
    }

    public int areaOfIslandDFS(int[][] grid, int sr, int sc, int m, int n) {
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        s.add(new Pair<>(sr, sc));
        grid[sr][sc] = 2;

        int area = 0;
        while (!s.isEmpty()) {
            Pair<Integer, Integer> curr = s.pop();
            int r = curr.getKey();
            int c = curr.getValue();
            area++;

            if (r + 1 < m && grid[r + 1][c] == 1) {
                grid[r + 1][c] = 2;
                s.add(new Pair<>(r + 1, c));
            }
            if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                grid[r - 1][c] = 2;
                s.add(new Pair<>(r - 1, c));
            }
            if (c + 1 < n && grid[r][c + 1] == 1) {
                grid[r][c + 1] = 2;
                s.add(new Pair<>(r, c + 1));
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                grid[r][c - 1] = 2;
                s.add(new Pair<>(r, c - 1));
            }
        }
        return area;
    }

    public int areaOfIslandBFS(int[][] grid, int sr, int sc, int m, int n) {
        Queue<Pair<Integer, Integer>> s = new LinkedList<>();
        s.add(new Pair<>(sr, sc));
        grid[sr][sc] = 2;

        int area = 0;
        while (!s.isEmpty()) {
            Pair<Integer, Integer> curr = s.poll();
            int r = curr.getKey();
            int c = curr.getValue();
            area++;

            if (r + 1 < m && grid[r + 1][c] == 1) {
                grid[r + 1][c] = 2;
                s.add(new Pair<>(r + 1, c));
            }
            if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                grid[r - 1][c] = 2;
                s.add(new Pair<>(r - 1, c));
            }
            if (c + 1 < n && grid[r][c + 1] == 1) {
                grid[r][c + 1] = 2;
                s.add(new Pair<>(r, c + 1));
            }
            if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                grid[r][c - 1] = 2;
                s.add(new Pair<>(r, c - 1));
            }
        }
        return area;
    }
}