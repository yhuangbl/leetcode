class Solution162 {
    /**
     * binary search time complexity: O(logn)
     */
    public int findPeakElement(int[] nums) {
        int idxMin = 0;
        int idxMax = nums.length - 1;

        while (idxMin <= idxMax) {
            int mid = (idxMin + idxMax) / 2;
            double left;
            double right;
            // 只有double有negative infinity;
            // Integer只有Integer.MIN_VALUE，這樣如果真的是MIN就會錯
            if (mid - 1 == -1)
                left = Double.NEGATIVE_INFINITY;
            else
                left = nums[mid - 1];

            if (mid + 1 == nums.length)
                right = Double.NEGATIVE_INFINITY;
            else
                right = nums[mid + 1];

            if (nums[mid] > left && nums[mid] > right)
                return mid;
            if (left > right)
                idxMax = mid - 1;
            else
                idxMin = mid + 1;
        }
        return -1;
    }
}

class Solution166 {
    /**
     * http://buttercola.blogspot.com/2015/08/leetcode-fraction-to-recurring-decimal.html
     */
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0)
            return "0"; // edge case
        // edge case: when result is negative
        boolean neg = ((numerator < 0) ^ (denominator < 0)) ? true : false;
        // edge case: to avoid overflow
        long numeratorL = (long) numerator;
        long denominatorL = (long) denominator;
        if (numeratorL < 0)
            numeratorL *= -1;
        if (denominatorL < 0)
            denominatorL *= -1;

        // key: if the remainder is the same, it's recurring
        Map<Long, Integer> remToPos = new HashMap<Long, Integer>();

        long quot = numeratorL / denominatorL;
        String quotStr = Long.toString(quot);
        long remain = numeratorL % denominatorL;
        if (remain == 0) {
            if (neg)
                return "-" + quotStr;
            else
                return quotStr;
        }
        int currPos = 1;
        remToPos.put(remain, currPos++);

        StringBuilder sb = new StringBuilder("");
        sb.append(".");
        while (remain != 0) {
            remain *= 10;
            long newquot = remain / denominatorL;
            remain = remain % denominatorL;
            sb.append(newquot);
            if (remToPos.containsKey(remain)) {
                int pos = remToPos.get(remain);
                String fracStr = sb.toString();
                System.out.println(fracStr);
                String recur = fracStr.substring(pos, fracStr.length());
                String resultStr = quotStr + fracStr.substring(0, pos) + "(" + recur + ")";
                if (neg)
                    resultStr = "-" + resultStr;
                return resultStr;
            } else {
                remToPos.put(remain, currPos++);
            }
        }

        String resultStr = quotStr + sb.toString();
        if (neg)
            resultStr = "-" + resultStr;
        return resultStr;
    }
}

class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                break;
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        int[] res = new int[] { left + 1, right + 1 };
        return res;
    }
}

class Solution169 {
    /**
     * Time: O(n); Space: O(n)
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        if (len == 1)
            return nums[0];
        int half = len / 2;

        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        for (int i = 0; i < len; i++) {
            int n = nums[i];
            int newCount = count.getOrDefault(n, 0);
            newCount++;
            if (newCount > half)
                return n;
            count.put(n, newCount);
        }
        return -1;
    }
}