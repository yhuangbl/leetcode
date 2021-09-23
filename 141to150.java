/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution141 {
    /**
     * Time complexity: O(n); Space Complexity: O(n)
     */
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> nodes = new HashSet<ListNode>();
        ListNode p = head;
        while (p != null) {
            if (nodes.contains(p)) return true;
            nodes.add(p);
            p = p.next;
        }
        return false;
    }

    /**
     * Two pointers:
     * Time complexity: O(n); Space Complexity: O(1)
     */
    public boolean hasCycle2(ListNode head) {
        if (head == null) return false;
        Set<ListNode> nodes = new HashSet<ListNode>();
        ListNode fastp = head.next;
        ListNode slowp = head;
        while (slowp != null) {
            if (fastp == null || fastp.next == null) return false;
            if (fastp == slowp) return true;
            fastp = fastp.next.next;
            slowp = slowp.next;
        }
        return false;
    }
}

class Solution146 {
    class LRUCacheEntry {
        public int key;
        public int val;
        public LRUCacheEntry next;
        public LRUCacheEntry prev;

        public LRUCacheEntry(int key, int val, LRUCacheEntry next, LRUCacheEntry prev) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    class LRUCache {
        private LRUCacheEntry head;
        private LRUCacheEntry tail;
        private int capacity;
        private int maxCapcity;

        public LRUCache(int capacity) {
            this.head = null;
            this.tail = null;
            this.capacity = 0;
            this.maxCapcity = capacity;
        }

        private void moveEntryToHead(LRUCacheEntry entry) {
            if (entry != head) {
                LRUCacheEntry nextEntry = entry.next;
                LRUCacheEntry prevEntry = entry.prev;

                if (prevEntry != null) {
                    prevEntry.next = nextEntry;
                }
                if (nextEntry != null) {
                    nextEntry.prev = prevEntry;
                }

                if (entry == tail) {
                    tail = prevEntry;
                }

                entry.next = head;
                entry.prev = null;
                head.prev = entry;
                head = entry;
            }
        }

        private void putEntryToHead(int key, int value) {
            LRUCacheEntry newHead = new LRUCacheEntry(key, value, head, null);
            if (head != null) {
                head.prev = newHead;
            }
            head = newHead;
            if (tail == null) {
                tail = newHead;
            }
        }

        private void evict() {
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                LRUCacheEntry prevEntry = tail.prev;
                tail = prevEntry;
                prevEntry.next = null;
            }
            capacity--;
        }

        /**
         * Time: O(n)
         */
        public int get(int key) {
            LRUCacheEntry prev = null;
            LRUCacheEntry curr = this.head;
            while (curr != null) {
                if (curr.key == key) {
                    moveEntryToHead(curr);
                    return curr.val;
                }
                prev = curr;
                curr = curr.next;
            }
            return -1;
        }

        /**
         * Time: (n)
         */
        public void put(int key, int value) {
            boolean update = false;
            LRUCacheEntry prev = null;
            LRUCacheEntry curr = this.head;
            while (curr != null) {
                if (curr.key == key) {
                    update = true;
                    curr.val = value;
                    moveEntryToHead(curr);
                    break;
                }
                prev = curr;
                curr = curr.next;
            }

            if (!update) {
                if (capacity == maxCapcity) {
                    evict();
                }
                putEntryToHead(key, value);
                capacity++;
            }
        }
    }

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */

    /**
     * Can we optimize to O(1) for both get & put? Yes! use hashmap!
     */
    class LRUCacheEntry {
        public int key;
        public int val;
        public LRUCacheEntry next;
        public LRUCacheEntry prev;

        public LRUCacheEntry(int key, int val, LRUCacheEntry next, LRUCacheEntry prev) {
            this.key = key;
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    class LRUCache {
        private LRUCacheEntry head;
        private LRUCacheEntry tail;
        private int capacity;
        private int maxCapcity;
        private Map<Integer, LRUCacheEntry> keyToVal;

        public LRUCache(int capacity) {
            this.head = null;
            this.tail = null;
            this.capacity = 0;
            this.maxCapcity = capacity;
            this.keyToVal = new HashMap<Integer, LRUCacheEntry>();
        }

        private void moveEntryToHead(LRUCacheEntry entry) {
            if (entry != head) {
                LRUCacheEntry nextEntry = entry.next;
                LRUCacheEntry prevEntry = entry.prev;

                if (prevEntry != null) {
                    prevEntry.next = nextEntry;
                }
                if (nextEntry != null) {
                    nextEntry.prev = prevEntry;
                }

                if (entry == tail) {
                    tail = prevEntry;
                }

                entry.next = head;
                entry.prev = null;
                head.prev = entry;
                head = entry;
            }
        }

        private void putEntryToHead(int key, int value) {
            LRUCacheEntry newHead = new LRUCacheEntry(key, value, head, null);
            keyToVal.put(key, newHead);
            if (head != null) {
                head.prev = newHead;
            }
            head = newHead;
            if (tail == null) {
                tail = newHead;
            }
        }

        private void evict() {
            keyToVal.remove(tail.key);
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                LRUCacheEntry prevEntry = tail.prev;
                tail = prevEntry;
                prevEntry.next = null;
            }
            capacity--;
        }

        /**
         * Time: O(1)
         */
        public int get(int key) {
            LRUCacheEntry found = keyToVal.getOrDefault(key, null);
            if (found != null) {
                moveEntryToHead(found);
                return found.val;
            }
            else {
                return -1;
            }
        }

        /**
         * Time: O(1)
         */
        public void put(int key, int value) {
            LRUCacheEntry found = keyToVal.getOrDefault(key, null);
            if (found != null) {
                found.val = value;
                moveEntryToHead(found);
            }
            else {
                if (capacity == maxCapcity) {
                    evict();
                }
                putEntryToHead(key, value);
                capacity++;
            }
        }
    }
}

class Solution150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> numbers = new Stack<Integer>();

        for (String token: tokens) {
            if ("+".equals(token) || "-".equals(token) ||
                "*".equals(token) || "/".equals(token)) {
                if (!numbers.empty()) {
                    int second = numbers.pop();
                    if (numbers.empty()) {
                        continue;
                    }
                    int first = numbers.pop();
                    int temp = 0;
                    switch (token) {
                        case "+":
                            temp = first + second;
                            break;
                        case "-":
                            temp = first - second;
                            break;
                        case "*":
                            temp = first * second;
                            break;
                        case "/":
                            temp = first / second;
                            break;
                    }
                    numbers.push(temp);
                }
            }
            else {
                numbers.push(Integer.parseInt(token));
            }
        }
        if (!numbers.empty()) return numbers.pop();
        else return 0;
    }
}