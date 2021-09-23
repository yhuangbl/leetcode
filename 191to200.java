/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution199 {
    /**
     * BFS: get right most of the level
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> right = new ArrayList<Integer>();
        if (root == null) {
            return right;
        }
        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        int curr = 1;
        int childs = 0;
        while (!nodes.isEmpty()) {
            TreeNode front = nodes.remove();
            curr--;
            if (front.left != null) {
                nodes.add(front.left);
                childs++;
            }
            if (front.right != null) {
                nodes.add(front.right);
                childs++;
            }
            if (curr == 0) {
                right.add(front.val);
                curr = childs;
                childs = 0;
            }
        }
        return right;
    }
}

class Solution200 {
    /**
     * iterative
     */
    public int numIslands1(char[][] grid) {
        int rowlen = grid.length;
        if (rowlen <= 0 || !(grid[0] instanceof char[]))
            return 0;
        int collen = grid[0].length;
        int islandNum = 0;

        // 可以改成 boolean[][] checked (default value is false)
        // 會快一些
        Set<List<Integer>> checked = new HashSet<List<Integer>>();
        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (grid[i][j] == '1') {
                    List<Integer> newland = Arrays.asList(i, j);
                    if (!checked.contains(newland)) {
                        Queue<List<Integer>> lands
                                = new LinkedList<List<Integer>>();
                        lands.add(newland);
                        islandNum++;
                        while (!lands.isEmpty()) {
                            List<Integer> front = lands.remove();
                            int x = front.get(0);
                            int y = front.get(1);

                            List<Integer> down = Arrays.asList(x + 1, y);
                            if (x + 1 < rowlen && grid[x+1][y] == '1' &&
                                !checked.contains(down)) {
                                lands.add(down);
                                checked.add(down);
                            }
                            List<Integer> up = Arrays.asList(x - 1, y);
                            if (x - 1 >= 0 && grid[x-1][y] == '1' &&
                                !checked.contains(up)) {
                                lands.add(up);
                                checked.add(up);
                            }
                            List<Integer> right = Arrays.asList(x, y + 1);
                            if (y + 1 < collen && grid[x][y+1] == '1' &&
                                !checked.contains(right)) {
                                lands.add(right);
                                checked.add(right);
                            }
                            List<Integer> left = Arrays.asList(x, y - 1);
                            if (y - 1 >= 0 && grid[x][y-1] == '1' &&
                                !checked.contains(left)) {
                                lands.add(left);
                                checked.add(left);
                            }
                        }
                    }
                }
            }
        }
        return islandNum;
    }

    /** 我是分隔線 **/
    private void dfsVisit(char[][] grid, boolean[][] checked, int x, int y,
                          int rowlen, int collen) {
        if (x < 0 || x >= rowlen || y < 0 || y >= collen ||
            checked[x][y] || grid[x][y] == '0')
            return;
        checked[x][y] = true;
        dfsVisit(grid, checked, x + 1, y, rowlen, collen);
        dfsVisit(grid, checked, x - 1, y, rowlen, collen);
        dfsVisit(grid, checked, x, y + 1, rowlen, collen);
        dfsVisit(grid, checked, x, y - 1, rowlen, collen);
    }

    /**
     * recursion
     */
    public int numIslands2(char[][] grid) {
        int rowlen = grid.length;
        if (rowlen <= 0 || !(grid[0] instanceof char[]))
            return 0;
        int collen = grid[0].length;
        int islandNum = 0;

        boolean[][] checked = new boolean[rowlen][collen];
        for (int i = 0; i < rowlen; i++) {
            for (int j = 0; j < collen; j++) {
                if (grid[i][j] == '1' && !checked[i][j]) {
                    islandNum++;
                    dfsVisit(grid, checked, i, j, rowlen, collen);
                }
            }
        }
        return islandNum;
    }
}