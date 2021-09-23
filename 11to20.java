class Solution11 {
    /**
     * Time: O(n^2); Space: O(1)
     */
    public int maxArea1(int[] height) {
        int currMax = 0;

        int heightLen = height.length;
        for (int i = 0; i < heightLen; i++) {
            for (int j = i + 1; j < heightLen; j++) {
                // Math.min(height[i], height[j]
                int currHeight = (height[i] < height[j])? height[i]: height[j];
                int currArea = currHeight * (j - i);
                // currArea = Max.max(currArea, currHeight * (j - i))
                if (currArea > currMax) {
                    currMax = currArea;
                }
            }
        }
        return currMax;
    }

    /**
     * Two pointers solution
     * Time: O(n); Space: O(1)
     * https://leetcode.com/articles/container-with-most-water/
     */
    public int maxArea2(int[] height) {
        int currMax = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int currLen = Math.min(height[left], height[right]);
            currMax = Math.max(currMax, currLen * (right - left));
            if (height[left] < height[right]) {
                left++;
            }
            else {
                right--;
            }
        }
        return currMax;
    }
}

class Solution13 {
    public int romanToInt(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        int i = 0;
        int result = 0;
        int strlen = s.length();
        while (i < strlen) {
            if (i <= (strlen - 2)) {
                String firstTwo = s.substring(i, i + 2); // substring to extract 2
                if (map.containsKey(firstTwo)) { // containsKey not contains
                    result += map.get(firstTwo);
                    i += 2;
                    continue;
                }
            }
            String first = s.substring(i, i + 1);
            result += map.get(first);
            i++;
        }
        return result;
    }
}

class Solution14 {
    /**
     * Time: O(n^2), Space: O(1)
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int minlen = Integer.MAX_VALUE;
        for (String str: strs) {
            minlen = Math.min(minlen, str.length());
        }

        String prefix = "";
        for (int k = 1; k <= minlen; k++) {
            String currPrefix = strs[0].substring(0, k);
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                // correct way to do string comparision in java is equals (compare by value); ==: compare by pointer
                if (!currPrefix.equals(str.substring(0, k))) {
                    return prefix;
                }
            }
            prefix = currPrefix;
        }
        return prefix;
    }
}

class Solution15 {
    /**
     * Time complexity: O(n)
     * http://www.goodtecher.com/leetcode-15-3sum/
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length <= 2) {
            return result;
        }

        Arrays.sort(nums); // don't need to convert to list; array can sort too!

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                int small = nums[i];
                int mid = nums[j];
                int big = nums[k];

                int temp = small + mid + big;
                if (temp < 0) {
                    j++;
                }
                else if (temp > 0) {
                    k--;
                }
                else {
                    result.add(Arrays.asList(small, mid, big));
                    j++;
                    k--;

                    while (j < k && nums[j] == nums[j-1]) { // avoid duplication
                        j++;
                    }
                    while (j < k && nums[k] == nums[k+1]) { // avoid duplication
                        k--;
                    }
                }
            }
        }
        return result;
    }
}

class Solution17 {

    Map<Character, List<String>> digitToChar = new HashMap<Character, List<String>>();

    private void init() {
        this.digitToChar.put('2', Arrays.asList("a", "b", "c"));
        this.digitToChar.put('3', Arrays.asList("d", "e", "f"));
        this.digitToChar.put('4', Arrays.asList("g", "h", "i"));
        this.digitToChar.put('5', Arrays.asList("j", "k", "l"));
        this.digitToChar.put('6', Arrays.asList("m", "n", "o"));
        this.digitToChar.put('7', Arrays.asList("p", "q", "r", "s"));
        this.digitToChar.put('8', Arrays.asList("t", "u", "v"));
        this.digitToChar.put('9', Arrays.asList("w", "x", "y", "z"));
    }

    private List<String> letterCombinationsHelper(String digits) {
        int len = digits.length();
        if (len == 0) {
            return Collections.emptyList();
        }
        else if (len == 1) {
            return digitToChar.get(digits.charAt(0));
        }

        List<String> result = new ArrayList<String>();
        char d = digits.charAt(0);
        List<String> subResult = letterCombinationsHelper(digits.substring(1, len));
        for (String c: digitToChar.get(d)) {
            for (String subString: subResult) {
                result.add(c + subString);
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        init();
        return letterCombinationsHelper(digits);
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution19 {
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        int targetIdx = count - n;

        if (targetIdx == 0) {
            head = head.next;
        }
        else {
            curr = null;
            for (int i = 0; i < targetIdx; i++) {
                if (curr == null) curr = head;
                else curr = curr.next;
            }
            curr.next = curr.next.next;
        }

        return head;
    }
}

class Solution20 {
    private boolean isOpen(Character c) {
        if (c == '(' || c == '{' || c == '[') return true;
        return false;
    }

    private boolean isClose(Character c) {
        if (c == ')' || c == '}' || c == ']') return true;
        return false;
    }

    private boolean isMatch(Character openBracket, Character closeBracket) {
        if ((openBracket == '(' && closeBracket == ')') ||
                (openBracket == '{' && closeBracket == '}') ||
                (openBracket == '[' && closeBracket == ']')) return true;
        return false;
    }

    // be aware of the corner cases!!!!!!
    public boolean isValid(String s) {
        if (s == null) return true;
        int strlen = s.length();
        if (strlen == 0) return true;

        Stack<Character> brackets = new Stack<Character>();
        for (int i = 0; i < strlen; i++) {
            Character c = s.charAt(i); // not s.get(i)
            if (isOpen(c)) {
                brackets.push(c);
            }
            else if (isClose(c)) {
                if (brackets.empty()) return false;
                // throws exception if the stack is empty; so,
                // we need to check the status of the stack first!
                Character matchedOpenBracket = brackets.pop();
                if (!isMatch(matchedOpenBracket, c)) return false;
            }
        }
        if (brackets.empty()) return true;
        else return false; // if there are brackets left that are not matched!
    }
}