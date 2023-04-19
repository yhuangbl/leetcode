/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/**
 * reference:
 * https://zhuhan0.blogspot.com/2017/08/leetcode-341-flatten-nested-list.html
 */
public class NestedIterator341 implements Iterator<Integer> {
    private Stack<NestedInteger> listStack;
    private Integer top;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.listStack = new Stack<NestedInteger>();
        // 是size不是length; 要倒序(stack是last in first out)
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            this.listStack.push(nestedList.get(i));
        }
        this.top = null;
    }

    @Override
    public Integer next() {
        return this.top;
    }

    @Override
    public boolean hasNext() {
        while (!listStack.empty()) {
            NestedInteger topNestedInteger = this.listStack.pop();
            if (topNestedInteger.isInteger()) {
                this.top = topNestedInteger.getInteger();
                return true;
            } else {
                List<NestedInteger> topNestedIntList = topNestedInteger.getList();
                for (int i = topNestedIntList.size() - 1; i >= 0; i--) {
                    this.listStack.push(topNestedIntList.get(i));
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList); while (i.hasNext()) v[f()]
 * = i.next();
 */

class Solution344 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}