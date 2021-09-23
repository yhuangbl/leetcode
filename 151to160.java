class MinStack155 {
    private int currlen;
    private int min;
    private List<Integer> stackList;

    /** initialize your data structure here. */
    public MinStack() {
        this.currlen = 0;
        this.min = Integer.MAX_VALUE;
        this.stackList = new ArrayList<Integer>();
    }

    public void push(int x) {
        if (x < this.min) {
            this.min = x;
        }
        this.stackList.add(x);
        this.currlen++;
    }

    /**
     * pop is O(n) operation
     */
    public void pop() {
        if (this.currlen > 0) {
            int val = this.stackList.remove(this.currlen-1);
            this.currlen--;
            if (val == this.min) { // need to update min!!
                this.min = Integer.MAX_VALUE;
                for (Integer i: this.stackList) {
                    this.min = Math.min(this.min, i);
                }
            }
        }
    }

    public int top() {
        if (this.currlen > 0) {
            return this.stackList.get(this.currlen-1);
        }
        return -1;
    }

    public int getMin() {
        if (this.currlen > 0) {
            return this.min;
        }
        return -1;
    }
}

class MinStackO1 {
    private int currlen;
    private int min;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        this.currlen = 0;
        this.min = Integer.MAX_VALUE;
        this.stack = new Stack<Integer>();
    }

    public void push(int x) {
        // need to use <= because everytime val == this.min
        // in pop, we pop one extra
        if (x <= this.min) {
            stack.push(this.min);
            this.min = x;
        }
        this.stack.push(x);
        this.currlen++;
    }

    /**
     * pop is O(1)!
     */
    public void pop() {
        if (this.currlen > 0) {
            int val = this.stack.pop();
            this.currlen--;
            if (val == this.min) {
                this.min = this.stack.pop();
            }
        }
    }

    public int top() {
        if (this.currlen > 0) {
            return this.stack.peek();
        }
        return -1;
    }

    public int getMin() {
        if (this.currlen > 0) {
            return this.min;
        }
        return -1;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution158 extends Reader4 {
    private char[] buf4 = new char[4];
    private int b4 = 0;
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int i = 0;
        int readCnt = 0;

        while (i < n) {
            if (b4 == 0 || buf4[b4] == 0) {
                buf4 = new char[4];
                int rd = read4(buf4);
                if (rd == 0) {
                    break;
                }
            }
            buf[i++] = buf4[b4];
            b4 = (b4 + 1) % 4;
        }
        return i;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution160 {
    private int getDepth(ListNode node) {
        ListNode curr = node;
        int depth = 0;
        while (curr != null) {
            depth++;
            curr = curr.next;
        }
        return depth;
    }

    private ListNode moveForward(ListNode node, int num) {
        while (num > 0) {
            node = node.next;
            num--;
        }
        return node;
    }

    private ListNode findSame(ListNode headA, ListNode headB) {
        ListNode currA = headA;
        ListNode currB = headB;
        while (currA != null && currB != null && currA != currB) {
            currA = currA.next;
            currB = currB.next;
        }

        return (currA == null || currB == null)? null: currA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int depthA = getDepth(headA);
        int depthB = getDepth(headB);

        if (depthA > depthB) {
            headA = moveForward(headA, depthA - depthB);
        }
        else if (depthB > depthA) {
            headB = moveForward(headB, depthB - depthA);
        }

        return findSame(headA, headB);
    }
}
