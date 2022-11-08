class Solution273 {
    private String generatePartialStr(String partial, String addStr) {
        return ("".equals(partial)) ? addStr : (" " + addStr);
    }

    /**
     * edge cases: 0, 1000100, 100, 101
     */
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        List<String> numbers = Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                "Nine");
        List<String> tenMulti = Arrays.asList("NA", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
                "Eighty", "Ninety");
        List<String> specials = Arrays.asList("Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
                "Seventeen", "Eighteen", "Nineteen");
        List<String> levels = Arrays.asList("NA", "Thousand", "Million", "Billion");

        String result = "";
        int level = 0;
        while (num > 0) {
            int lastThree = num % 1000;
            if (lastThree == 0) {
                // ignore
            } else {
                String partial = "";
                int hundred = lastThree / 100;
                int mid = lastThree % 100 / 10;
                int last = lastThree % 10;
                if (hundred > 0) {
                    partial += numbers.get(hundred) + " Hundred";
                }

                if (mid > 1 || (mid == 1 && last == 0)) {
                    String addLast = (last > 0) ? " " + numbers.get(last) : "";
                    String addStr = generatePartialStr(partial, tenMulti.get(mid) + addLast);
                    partial += addStr;
                } else if (mid == 1) {
                    String addStr = generatePartialStr(partial, specials.get(last));
                    partial += addStr;
                } else if (mid == 0) {
                    if (last != 0 || (last == 0 && hundred == 0)) {
                        String addStr = generatePartialStr(partial, numbers.get(last));
                        partial += addStr;
                    }
                }

                if (level > 0) {
                    partial += " " + levels.get(level);
                }

                if (!"".equals(result)) {
                    result = partial + " " + result;
                } else {
                    result = partial;
                }

            }
            level++;
            num = num / 1000;
        }
        return result.trim();
    }
}

/*
 * The isBadVersion API is defined in the parent class VersionControl. boolean
 * isBadVersion(int version);
 */

public class Solution278 extends VersionControl {
    public int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2; // faster than (start + end)/2 (WHY!); also avoid overflow
            if (isBadVersion(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

class Solution279 {
    public int numSquares(int n) {
        if (n == 0) {
            return 0;
        }

        double square = Math.sqrt(n);
        if (Math.floor(square) == square) {
            return 1;
        }

        int num = n;
        for (int i = 2; i * i <= n; i++) {
            int currSquare = i * i;
            int quot = n / currSquare;
            int remain = n % currSquare;
            num = Math.min(num, quot + numSquares(remain));
        }
        return num;
    }
}