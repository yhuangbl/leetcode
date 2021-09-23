class Solution202 {
    private int compute(int n) {
        int result = 0;
        while (n / 10 > 0) {
            int m = n % 10;
            result += m * m;
            n = n / 10;
        }
        result += n * n;
        return result;
    }

    private boolean isHappy(int n, Set<Integer> occured) {
        if (n / 10 == 0 && n == 1) return true;
        int result = compute(n);
        if (occured.contains(result) && result != 1) return false;
        occured.add(result);
        return isHappy(result, occured);
    }

    public boolean isHappy(int n) {
        if (n / 10 == 0 && n == 1) return true;
        Set<Integer> occured = new HashSet<Integer>();
        occured.add(n);
        int result = compute(n);
        if (occured.contains(result) && result != 1) return false;
        occured.add(result);
        return isHappy(result, occured);
    }
}

class Solution207 {
    /**
     * key: 等於是在問有沒有cycle (用dfs找cycle)
     */
    private boolean dfsVisit(Map<Integer, List<Integer>> adjList,
                             boolean[] visited, boolean[] recurStack, int curr) {
        if (recurStack[curr])
            return true;
        if (visited[curr])
            return false;

        visited[curr] = true;
        recurStack[curr] = true;
        List<Integer> dests = adjList.get(curr);
        if (dests != null && dests.size() > 0) {
            for (int d: dests) {
                if (dfsVisit(adjList, visited, recurStack, d))
                    return true;
            }
        }
        recurStack[curr] = false;
        return false;
    }

    private boolean hasCycle(int numCourses, Map<Integer, List<Integer>> adjList) {
        boolean[] visited = new boolean[numCourses];
        boolean[] recurStack = new boolean[numCourses];
        for (int i = 0; i < adjList.size(); i++) {
            if (dfsVisit(adjList, visited, recurStack, i))
                return true;
        }
        return false;
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        if (prerequisites == null)
            return true;

        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int from = edge[0];
            int to = edge[1];
            if (!adjList.containsKey(from))
                adjList.put(from, new ArrayList<Integer>());
            adjList.get(from).add(to);
        }

        return !hasCycle(numCourses, adjList);
    }

    /** 我是分隔線 **/
    private boolean hasCycle(int numCourses, Map<Integer, List<Integer>> adjList, int[] indegree) {
        Queue<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                nodes.add(i);
            }
        }
        while (!nodes.isEmpty()) {
            int front = nodes.remove();
            if (adjList.containsKey(front)) {
                for (int adjNode: adjList.get(front)) {
                    indegree[adjNode]--;
                    if (indegree[adjNode] == 0)
                        nodes.add(adjNode);
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] > 0)
                return true;
        }
        return false;
    }

    /**
     * 也可以用Topological Sort (BFS/DFS都可以做到)找cycle
     * 假如到最後還有人in-degree不是0就代表有cycle了
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null)
            return true;

        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int from = edge[0];
            int to = edge[1];
            if (!adjList.containsKey(from))
                adjList.put(from, new ArrayList<Integer>());
            adjList.get(from).add(to);
            indegree[to]++;
        }

        return !hasCycle(numCourses, adjList, indegree);
    }
}

class Solution210 {
    /**
     * 基本上就是207 Topological Sort 把order印出來而已！
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] emptyArr = new int[0];
        if (prerequisites == null)
            return emptyArr;

        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] edge = prerequisites[i];
            int from = edge[1]; // input 的第一個是to 第二個是from
            int to = edge[0]; // 因為第二個是prerequisite,要先有二才有一
            if (!adjList.containsKey(from))
                adjList.put(from, new ArrayList<Integer>());
            adjList.get(from).add(to);
            indegree[to]++;
        }

        List<Integer> order = new ArrayList<Integer>();
        Queue<Integer> nodes = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                nodes.add(i);
                order.add(i);
            }
        }
        while (!nodes.isEmpty()) {
            int front = nodes.remove();
            if (adjList.containsKey(front)) {
                for (int adjNode: adjList.get(front)) {
                    indegree[adjNode]--;
                    if (indegree[adjNode] == 0) {
                        nodes.add(adjNode);
                        order.add(adjNode);
                    }
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] > 0)
                return emptyArr;
        }

        int orderSize = order.size();
        int[] orderArr = new int[orderSize];
        for (int i = 0; i < orderSize; i++)
            orderArr[i] = order.get(i);
        return orderArr;
    }
}