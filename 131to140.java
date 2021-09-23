class Solution134 {
    /**
     * Time: O(n^2)
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;

        int tank;
        for (int i = 0; i < len; i++) {
            tank = 0;
            for (int k = i; ;k++) {
                int idx = k % len;
                tank += gas[idx];
                if (tank >= cost[idx]) {
                    tank -= cost[idx];
                    if (idx == i-1 || ((i == 0) && (idx == len - 1)))
                        return i;
                }
                else
                    break;

            }
        }
        return -1;
    }
}

class Solution136 {
    /**
     * Time: O(n); Space: O(n)
     */
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> usedNums = new HashMap<Integer, Integer>();
        for (int num: nums) {
            if (usedNums.containsKey(num)) { // map 是 containsKey!!
                usedNums.put(num, 2);
            }
            else {
                usedNums.put(num, 1);
            }
        }
        for (int num: nums) {
            if (usedNums.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }

    /**
     * Time: O(n); Space: O(1)
     */
    public int singleNumber2(int[] nums) {
        Arrays.sort(nums);

        int prev = -1;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                prev = nums[i];
            }
            else {
                if (nums[i] != prev) return prev;
            }
        }
        return nums[nums.length-1]; // 如果到這裡都沒有 表示不成對的是最後一個
    }
}

/**
 * // Definition for a Node.
 * class Node {
 *    public int val;
 *    public Node next;
 *    public Node random;
 *
 *    public Node() {}
 *
 *    public Node(int _val,Node _next,Node _random) {
 *        val = _val;
 *        next = _next;
 *        random = _random;
 *    }
 * };
 */
class Solution138 {
    /**
     * Time complexity: O(n); space complexity: O(n)
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node newHead = new Node(head.val, null, null);
        Node newp = newHead;
        Map<Node, Node> oldToNew = new HashMap<Node, Node>();
        oldToNew.put(head, newHead);

        Node oldp = head.next;
        while (oldp != null) {
            Node newNode = new Node(oldp.val, null, null);
            oldToNew.put(oldp, newNode);
            newp.next = newNode;
            newp = newp.next;
            oldp = oldp.next;
        }

        oldp = head;
        newp = newHead;
        while (oldp != null) {
            Node oldRand = oldp.random;
            Node newRand = oldToNew.get(oldRand);
            newp.random = newRand;
            oldp = oldp.next;
            newp = newp.next;
        }

        return newHead;
    }
}

class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // dp: int[i]: s[i] ends with a word in wordDict
        int strlen = s.length();
        int[] dp = new int[strlen];
        for (int i = 0; i < strlen; i++) {
            dp[i] = -1;
        }
        int wordDictLen = wordDict.size();
        for (int start = 0; start < strlen; start++) {
            for (int end = start; end < strlen; end++) {
                String word = s.substring(start, end + 1);
                if (wordDict.contains(word)) {
                    if (start == 0 || dp[start - 1] != -1) {
                        dp[end] = wordDict.indexOf(word);
                    }
                }
            }
        }
        return dp[strlen - 1] != -1;
    }
}

class Solution140 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        // dp: List<Integer>[i]: s[i] ends with a set of possible words in wordDict
        int strlen = s.length();
        List<Integer>[] dp = new ArrayList[strlen];

        int wordDictLen = wordDict.size();
        for (int start = 0; start < strlen; start++) {
            for (int end = start; end < strlen; end++) {
                String word = s.substring(start, end + 1);
                if (wordDict.contains(word)) {
                    if (start == 0 || dp[start - 1] != null) {
                        if (dp[end] == null) {
                            dp[end] = new ArrayList<Integer>();
                        }
                        int idx = wordDict.indexOf(word);
                        if (!dp[end].contains(idx)) {
                            dp[end].add(idx);
                        }
                    }
                }
            }
        }

        Comparator<String> stringComparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                int alen = a.replaceAll(" ", "").length();
                int blen = b.replaceAll(" ", "").length();
                return alen - blen;
            }
        };
        PriorityQueue<String> result = new PriorityQueue<String>(1, stringComparator);
        int endIdx = strlen - 1;
        if (dp[endIdx] == null) {
            return Collections.emptyList();
        }

        for (int idx: dp[endIdx]) {
            result.add(wordDict.get(idx));
        }

        while (!result.isEmpty()) {
            String front = result.peek();
            int frontlen = front.replaceAll(" ", "").length();
            if (frontlen == strlen) {
                break;
            }

            front = result.remove();
            int newEnd = endIdx - frontlen;
            if (dp[newEnd] != null) {
                for (int idx: dp[newEnd]) {
                    String newStr = wordDict.get(idx) + " " + front;
                    if (!result.contains(newStr)) {
                        result.add(newStr);
                    }
                }
            }
        }

        return Arrays.asList(result.toArray(new String[result.size()]));
    }
}}