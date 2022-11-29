class Solution994 {
    private static final int[][] DIR = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
    private static final int DIR_LENGTH = 4;
    
    // BFS - topological sort
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<int[]>();
        int m = grid.length;
        int n = grid[0].length;
        int[][] minutes = new int[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 2) {
                    minutes[i][j] = 0;
                    q.add(new int[]{i, j});
                } else {
                    minutes[i][j] = -1;
                }
            }
        }
        
        int time = 0;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            for (int k = 0; k < DIR_LENGTH; ++k) {
                int newR = r + DIR[k][0];
                int newC = c + DIR[k][1];
                if (newR >= 0 && newR < m && newC >= 0 && newC < n && minutes[newR][newC] == -1 && grid[newR][newC] == 1) {
                    minutes[newR][newC] = minutes[r][c] + 1;
                    q.add(new int[]{newR, newC});
                    time = Math.max(time, minutes[newR][newC]);
                }
            }
        }
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1 && minutes[i][j] == -1) {
                    return -1;
                }
            }
        }
        return time;
    }
}