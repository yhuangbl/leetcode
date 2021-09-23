class Solution269 {
    private Map<Character, Set<Character>> buildAdjList(String[] words) {
        char firstc = 0; // non-empty words
        Map<Character, Set<Character>> adjList = new HashMap<Character, Set<Character>>();
        for (int i = 0; i < words.length - 1; i ++) {
            String first = words[i];
            String second = words[i + 1];

            int j = 0;
            int firstlen = first.length();
            int secondlen = second.length();
            for (; j < Math.min(firstlen, secondlen); j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    break;
                }
            }

            String sameSubstring = first.substring(0, j);
            if (sameSubstring.length() == 1 && !adjList.containsKey(sameSubstring.charAt(0))) {
                adjList.put(sameSubstring.charAt(0), new HashSet<Character>());
            }
            for (int k = 0; k < sameSubstring.length() - 1; k++) {
                char prev = sameSubstring.charAt(k);
                char next = sameSubstring.charAt(k + 1);
                if (!adjList.containsKey(prev)) {
                    adjList.put(prev, new HashSet<Character>());
                }
                if (!adjList.containsKey(next)) {
                    adjList.put(next, new HashSet<Character>());
                }
            }

            char firstNext = (j >= firstlen)? 0: first.charAt(j);
            char secondNext = (j >= secondlen)? 0: second.charAt(j);

            if (firstNext != 0 && secondNext != 0) {
                Set<Character> existing = adjList.getOrDefault(firstNext, new HashSet<Character>());
                existing.add(secondNext);
                adjList.put(firstNext, existing);
            }

            for (int k = j; k < firstlen; k++) {
                char c = first.charAt(k);
                if (!adjList.containsKey(c)) {
                    adjList.put(c, new HashSet<Character>());
                }
            }

            for (int k = j; k < secondlen; k++) {
                char c = second.charAt(k);
                if (!adjList.containsKey(c)) {
                    adjList.put(c, new HashSet<Character>());
                }
            }
        }
        return adjList;
    }

    private Map<Character, Integer> computeIndegree(Map<Character, Set<Character>> adjList) {
        Map<Character, Integer> indegree = new HashMap<Character, Integer>();
        for (Map.Entry<Character, Set<Character>> entry: adjList.entrySet()) {
            Character key = entry.getKey();
            if (!indegree.containsKey(key)) {
                indegree.put(key, 0);
            }
            Set<Character> children = entry.getValue();
            for (Character c: children) {
                indegree.put(c, indegree.getOrDefault(c, 0) + 1);
            }
        }
        return indegree;
    }

    private String getOrder(Map<Character, Set<Character>> adjList, Map<Character, Integer> indegree) {
        Queue<Character> zeroIndegree = new LinkedList<Character>();
        for (Map.Entry<Character, Integer> entry: indegree.entrySet()) {
            if (entry.getValue() == 0) {
                Character c = entry.getKey();
                zeroIndegree.add(c);
            }
        }

        List<Character> order = new ArrayList<Character>();
        while (!zeroIndegree.isEmpty()) {
            Character c = zeroIndegree.remove();
            order.add(c);
            for (Character child: adjList.getOrDefault(c, Collections.emptySet())) {
                indegree.put(child, indegree.get(child) - 1);
                if (indegree.get(child) == 0) {
                    zeroIndegree.add(child);
                }
            }
        }

        for (Integer degree: indegree.values()) {
            if (degree > 0) {
                return "";
            }
        }
        return order.stream().map(String::valueOf).collect(Collectors.joining());
    }

    /**
     * Think of it like topological sort
     */
    public String alienOrder(String[] words) {
        if (words.length == 1) {
            return words[0];
        }
        Map<Character, Set<Character>> adjList = buildAdjList(words);
        Map<Character, Integer> indegree = computeIndegree(adjList);
        return getOrder(adjList, indegree);
    }
}