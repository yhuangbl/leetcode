// To ask: what to do when no result found? throw an exception or return default
// values or return empty array?
class Solution1 {
    /**
     * Time: O(n^2); Space: O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        int[] result = new int[2]; // java array initialization
        int numElements = nums.length;

        for (int i = 0; i < numElements; i++) {
            for (int j = 0; j < numElements; j++) {
                if (i == j) {
                    continue;
                }
                else {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Time: O(n); Space: O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // map initialization

        for (int i = 0; i < nums.length; i++) {
            int remaining = target - nums[i];
            if (map.containsKey(remaining)) { // containsKey not contains
                result[0] = i;
                result[1] = map.get(remaining);
                return result;
                // more elegant way to do sy:
                // return new int[]{i, map.get(remaining)};
            }
            else {
                map.put(nums[i], i); // put not add
            }
        }
        return result;
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
class Solution2 {
    /**
     * Time complexity: O(max(n, m)) n, m is the length of linked list
     * Why can't we add two numbers and convert it back to list?
     * because different number types have their upper bound and lower bound,
     * cannot represnt all numbers
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1p = l1;
        ListNode l2p = l2;

        ListNode result = null;
        ListNode resultp = null;
        boolean carry = false;
        while (l1p != null || l2p != null || carry) {
            int sum = 0;
            if (l1p != null)  sum += l1p.val; // Java can't do if (l1p)
            if (l2p != null) sum += l2p.val;
            if (carry) {
                sum += 1;
                carry = false;
            }
            if (sum >= 10) {
                sum -= 10;
                carry = true;
            }
            if (result == null) {
                result = new ListNode(sum);
                resultp = result;
            }
            else {
                ListNode newNode = new ListNode(sum);
                resultp.next = newNode;
                resultp = newNode;
            }
            if (l1p != null) l1p = l1p.next;
            if (l2p != null) l2p = l2p.next;
        }
        return result;
    }
}

class Solution3 {
    /**
     * Time: O(n^2); Space: O(min(m,n)) where m is the hashset size
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int longestLen = 1;
        int strLen = s.length(); // string length in java
        for (int i = 0; i < strLen; i++) {
            Set<Character> usedChar = new HashSet<Character>();
            int len = 1;
            usedChar.add(s.charAt(i)); // not s[i]
            for (int j = i + 1; j < strLen; j++) {
                if (!usedChar.contains(s.charAt(j))) {
                    len++;
                    usedChar.add(s.charAt(j));
                }
                else {
                    break;
                }
            }
            longestLen = Math.max(longestLen, len);
        }
        return longestLen;
    }

    /**
     * 2 pointers /sliding window
     * Time: O(n); Space: O(min(m,n)) where m is the hashset size
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        
        int left = 0;
        int right = 0;
        int max = 1;
        Set<Character> unique_chars = new HashSet<Character>();
        while (right < s.length()) {
            max = Math.max(max, right-left);
            while (unique_chars.contains(s.charAt(right))) {
                unique_chars.remove(s.charAt(left++));
            }
            unique_chars.add(s.charAt(right++));
        }
        return Math.max(max, right-left);
    }
}

class Solution4 {
    /**
     * Time: O(n+m)
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nums1Len = nums1.length;
        int nums2Len = nums2.length;
        double medianPos = (nums1Len + nums2Len + 1) / 2.0;

        int nums1Idx = 0;
        int nums2Idx = 0;
        int nums1Curr;
        int nums2Curr;
        double curr = 0;
        double median = -1.0;
        boolean flag = false;
        boolean shouldStop1 = false;
        boolean shouldStop2 = false;
        // or cleaner can do: for (int curr = 0; curr <= medianPos + 1; i++)
        while (true) {
            curr++;
            // Key assumption: You may assume nums1 and nums2 cannot be both
            // empty. => but nums1 OR nums2 can be empty; should handle extreme
            // conditions
            if (nums1Len == 0) {
                nums1Curr = Integer.MAX_VALUE;
            }
            else {
                nums1Curr = nums1[nums1Idx];
            }
            if (nums2Len == 0) {
                nums2Curr = Integer.MAX_VALUE;
            }
            else {
                nums2Curr = nums2[nums2Idx];
            }

            // what to do when we exhauste one of the array: use two boolean
            // flags
            if ((shouldStop2 || (nums1Curr <= nums2Curr)) && !shouldStop1) {
                if (flag) {
                    median += nums1Curr;
                    return median / 2.0;
                }
                if (curr == medianPos) {
                    return nums1Curr;
                }
                else if (medianPos - curr == 0.5) {
                    flag = true;
                    median = nums1Curr;
                }
                if (nums1Idx < nums1Len -1) {
                    nums1Idx++;
                }
                else {
                    shouldStop1 = true;
                }
            }
            else if ((shouldStop1 || (nums1Curr > nums2Curr)) && !shouldStop2) {
                if (flag) {
                    median += nums2Curr;
                    return median / 2.0;
                }
                if (curr == medianPos) {
                    return nums2Curr;
                }
                else if (medianPos - curr == 0.5) {
                    flag = true;
                    median = nums2Curr;
                }
                if (nums2Idx < nums2Len -1) {
                    nums2Idx++;
                }
                else {
                    shouldStop2 = true;
                }
            }
            if (curr == (nums1Len + nums2Len)) { // should not enter here
                break;
            }
        }
        return median;
    }
    /**
     * Time: O(log(n+m))
     * TODO: understand this and redo this
     * https://medium.com/@hazemu/finding-the-median-of-2-sorted-arrays-in-logarithmic-time-1d3f2ecbeb46
     * key: when you see log, think of recursion, binary search
     */
}

class Solution5 {
    private boolean isPalindrome(String s) {
        int leftIdx = 0;
        int rightIdx = s.length() - 1;
        while (leftIdx <= rightIdx) {
            if (s.charAt(leftIdx) != s.charAt(rightIdx)) {
                return false;
            }
            leftIdx++; // while loop: remember to update index
            rightIdx--;
        }
        return true;
    }

    /**
     * Brute-force
     * Time: O(n^3); Space: O(1)
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int strlen = s.length();
        int longestlen = 0;
        String palindrome = "";
        for (int i = 0; i < strlen; i++) {
            for (int j = i; j < strlen; j++) {
                String candidate = s.substring(i, j + 1);
                if (isPalindrome(candidate)) {
                    int currlen = j - i + 1;
                    if (currlen > longestlen) {
                        longestlen = currlen;
                        palindrome = candidate;
                    }
                }
            }
        }
        return palindrome;
    }

    /**
     * Dynamic programming
     * <key> dynamic programming: find base case and use base case to build up
     * the solution
     * Time: O(n^2); Space: O(n^2)
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        int strlen = s.length();
        boolean[][] isPalindrome = new boolean[strlen][strlen];

        // base case
        int longest = 1;
        String palindrome = s.substring(0, 1);
        for (int i = 0; i < strlen; i++) {
            isPalindrome[i][i] = true;
            if ((i+1) < strlen) {
                if (s.charAt(i) == s.charAt(i+1)) {
                    isPalindrome[i][i+1] = true;
                    if (2 > longest) {
                        longest = 2;
                        palindrome = s.substring(i, i+2);
                    }
                }
                else {
                    isPalindrome[i][i+1] = false;
                }
            }
        }


        // Dynamic Programming
        // 為什麼不能for (int i = 0;...) for (int j = i + 2; ....):
        // 因為要由內往外build -> 這樣0, 5或在1, 4之前，就不對了
        for (int k = 3; k <= strlen; k++) {
            for (int startIdx = 0; startIdx <= (strlen-k); startIdx++) {
                int endIdx = startIdx+k-1;
                if (s.charAt(startIdx) == s.charAt(endIdx)) {
                    if (isPalindrome[startIdx+1][endIdx-1]) {
                        isPalindrome[startIdx][endIdx] = true;
                        if (k > longest) {
                            longest = k;
                            palindrome = s.substring(startIdx, endIdx+1);
                        }
                        continue;
                    }
                }
                isPalindrome[startIdx][endIdx] = false;
            }
        }

        return palindrome;
    }
}

class Solution8 {
    //有library function Character.isDigit()
    private boolean isDigit(Character c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private int charToInt(Character c) { // int d = str.charAt(i) - '0';
        switch (c) {
            case '0':
                return 0;
            case '1':
                return 1;
            case '2':
                return 2;
            case '3':
                return 3;
            case '4':
                return 4;
            case '5':
                return 5;
            case '6':
                return 6;
            case '7':
                return 7;
            case '8':
                return 8;
            case '9':
                return 9;
        }
        return -1;
    }

    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        // can just trim the white space using str.trim() (eliminates leading &
        // trailing spaces)
        boolean firstNonWhite = true;
        List<Character> digits = new ArrayList<Character>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (firstNonWhite && c == ' ') {
                continue;
            }
            else {
                if (firstNonWhite && (c == '+' || c== '-')) {
                    digits.add(c);
                    firstNonWhite = false;
                }
                else if (isDigit(c)) {
                    digits.add(c);
                    firstNonWhite = false;
                }
                else {
                    break;
                }
            }
        }

        if (digits.size() == 0) {
            return 0;
        }

        int result = 0;
        int numLen = digits.size(); // Collector: size() not length()
        boolean isPost = true;
        // 為什麼倒過來算不行(i.e. 從個位數開始算) -> 因為沒辦法辨別正負
        // -> infinity數字不完全一樣
        for (int i = 0; i < numLen; i++) {
            if (i == 0) {
                char firstC = digits.get(0);
                if (firstC == '-') {
                    isPost = false;
                }
                else if (firstC == '+') {
                    continue;
                }
                else {
                    int d = charToInt(firstC);
                    if (d >= 0) {
                        result += (Math.pow(10, numLen - 1) * d); // exponent: Math.pow
                    }
                }
            }
            else if (i == 1) {
                int d = charToInt(digits.get(i));
                if (d >= 0) {
                    result += (Math.pow(10, (numLen - i - 1)) * d);
                }
                if (!isPost) {
                    result *= -1;
                }
            }
            else {
                int d = charToInt(digits.get(i));
                if (d >= 0) {
                    if (isPost) {
                        result += (Math.pow(10, (numLen - i - 1)) * d);
                    }
                    else {
                        result -= (Math.pow(10, (numLen - i - 1)) * d);
                    }
                }
            }
        }

        return result;
    }
}