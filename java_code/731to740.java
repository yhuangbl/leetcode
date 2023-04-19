class Solution733 {
    // Iterative BFS
    public int[][] floodFillIterativeBFS(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if (startColor == color) {
            return image;
        }

        int m = image.length;
        int n = image[0].length;
        Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();
        q.add(new Pair<>(sr, sc));
        while (!q.isEmpty()) {
            Pair<Integer, Integer> curr = q.poll();
            int currR = curr.getKey();
            int currC = curr.getValue();
            image[currR][currC] = color;

            if (currR + 1 < m && image[currR + 1][currC] == startColor) {
                q.add(new Pair<>(currR + 1, currC));
            }
            if (currR - 1 >= 0 && image[currR - 1][currC] == startColor) {
                q.add(new Pair<>(currR - 1, currC));
            }
            if (currC + 1 < n && image[currR][currC + 1] == startColor) {
                q.add(new Pair<>(currR, currC + 1));
            }
            if (currC - 1 >= 0 && image[currR][currC - 1] == startColor) {
                q.add(new Pair<>(currR, currC - 1));
            }
        }
        return image;
    }

    public int[][] floodFillRecurisveBFS(int[][] image, int sr, int sc, int color) {
        if (image[sr][sc] == color) {
            return image;
        }
        Queue<Pair<Integer, Integer>> q = new LinkedList<Pair<Integer, Integer>>();
        q.add(new Pair<>(sr, sc));

        return fillBFS(image, color, image[sr][sc], q);
    }

    private int[][] fillBFS(int[][] image, int color, int startColor, Queue<Pair<Integer, Integer>> q) {
        if (q.isEmpty()) {
            return image;
        }

        Pair<Integer, Integer> curr = q.poll();
        int r = curr.getKey();
        int c = curr.getValue();

        image[r][c] = color;

        int m = image.length;
        int n = image[0].length;
        if (r + 1 < m && image[r + 1][c] == startColor) {
            q.add(new Pair<>(r + 1, c));
        }
        if (r - 1 >= 0 && image[r - 1][c] == startColor) {
            q.add(new Pair<>(r - 1, c));
        }
        if (c + 1 < n && image[r][c + 1] == startColor) {
            q.add(new Pair<>(r, c + 1));
        }
        if (c - 1 >= 0 && image[r][c - 1] == startColor) {
            q.add(new Pair<>(r, c - 1));
        }
        return fillBFS(image, color, startColor, q);
    }

    // Recursive DFS
    public int[][] floodFillRecursiveDFS(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        return fillDFS(image, sr, sc, color, startColor);
    }

    private int[][] fillDFS(int[][] image, int r, int c, int color, int startColor) {
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length || image[r][c] != startColor
                || image[r][c] == color) {
            return image;
        }

        image[r][c] = color;

        fillDFS(image, r + 1, c, color, startColor);
        fillDFS(image, r - 1, c, color, startColor);
        fillDFS(image, r, c + 1, color, startColor);
        fillDFS(image, r, c - 1, color, startColor);

        return image;
    }

    // Iterative DFS
    public int[][] floodFillIterativeDFS(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if (startColor == color) {
            return image;
        }

        int m = image.length;
        int n = image[0].length;

        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(sr, sc));

        while (!stack.isEmpty()) {
            Pair<Integer, Integer> curr = stack.pop();
            int currR = curr.getKey();
            int currC = curr.getValue();
            image[currR][currC] = color;

            if (currR + 1 < m && image[currR + 1][currC] == startColor) {
                stack.push(new Pair<>(currR + 1, currC));
            }
            if (currR - 1 >= 0 && image[currR - 1][currC] == startColor) {
                stack.push(new Pair<>(currR - 1, currC));
            }
            if (currC + 1 < n && image[currR][currC + 1] == startColor) {
                stack.push(new Pair<>(currR, currC + 1));
            }
            if (currC - 1 >= 0 && image[currR][currC - 1] == startColor) {
                stack.push(new Pair<>(currR, currC - 1));
            }
        }
        return image;
    }
}