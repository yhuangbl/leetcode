class Solution785 {
    private static int allVisited(boolean[] visited) {
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * BFS: O(n^2)
     */
    public boolean isBipartite(int[][] graph) {
        int size = graph.length;
        boolean[] visited = new boolean[size];
        int[] groups = new int[size];

        // BFS using queue
        Queue<Integer> nodes = new LinkedList<Integer>();
        nodes.add(0);

        int nextNew = -1;
        while (!nodes.isEmpty() || (nextNew = allVisited(visited)) != -1) {
            Integer front = null;
            if (nodes.isEmpty()) {
                front = nextNew;
            }
            else {
                front = nodes.remove();
            }
            if (!visited[front]) {
                // add the negihbors to the BFS queue
                Set<Integer> neighborGroups = new HashSet<Integer>();
                for (int n: graph[front]) {
                    nodes.add(n);
                    if (groups[n] != 0) {
                        neighborGroups.add(groups[n]);
                    }
                }

                if (neighborGroups.size() > 1) {
                    return false;
                }

                // put it into group1 or group2
                if (neighborGroups.isEmpty() || neighborGroups.contains(2)) {
                    groups[front] = 1;
                }
                else {
                    groups[front] = 2;
                }

            }
            visited[front] = true;
        }

        return true;
    }

    /**
     * 我是分隔線
     */
    private boolean dfsFind(int[][] graph, Boolean[] groups, int node) {
        for (int neighbor: graph[node]) {
            if (groups[neighbor] == null) {
                groups[neighbor] = !groups[node];
                if (!dfsFind(graph, groups, neighbor)) {
                    return false;
                }
            }
            else {
                if (groups[neighbor] == groups[node]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int findNext(Boolean[] groups) {
        for (int i = 0; i < groups.length; i++) {
            if (groups[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * DFS: O(n)
     */
    public boolean isBipartite(int[][] graph) {
        int size = graph.length;
        Boolean[] groups = new Boolean[size]; // default value of objects is null

        if (size > 0) {
            Integer nextVisited = null;
            while ((nextVisited = findNext(groups)) != -1) {
                groups[nextVisited] = true;
                if (!dfsFind(graph, groups, nextVisited)) {
                    return false;
                }
            }
        }
        return true;
    }
}