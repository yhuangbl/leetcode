class Solution62 {
    /**
     * Time: O(mn); Space: O(mn)
     */
    public int uniquePaths(int m, int n) {
        int[][] routes = new int[m+1][n+1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    continue; // default val for int array is 0
                else if (i == 1 || j == 1)
                    routes[i][j] = 1;
                else {
                    for (int k = i; k >= 1; k--)
                        routes[i][j] += routes[k][j-1];
                }
            }
        }
        if (routes[m][n] < 0) // handle overflow
            return Integer.MAX_VALUE;
        else
            return routes[m][n];
    }
}

class Solution67 {
    public String addBinary(String a, String b) {
        int alen = a.length();
        int blen = b.length();
        int len = Math.max(alen, blen) + 1;
        int[] aArr = new int[len];
        int[] bArr = new int[len];
        int[] carry = new int[len];
        int[] result = new int[len];

        int idx = 0;
        for (int i = alen - 1; i >= 0; i--) {
            aArr[idx++] = Character.getNumericValue(a.charAt(i));
        }
        idx = 0;
        for (int i = blen - 1; i >= 0; i--) {
            bArr[idx++] = Character.getNumericValue(b.charAt(i));
        }

        for (int i = 0; i < len; i++) {
            int r = aArr[i] + bArr[i] + carry[i];
            if (r >= 2) {
                r = r - 2;
                carry[i + 1] = 1;
            }
            result[i] = r;
        }

        String resultStr = "";
        boolean start = false;
        for (int i = len - 1; i >= 0; i--) {
            if (result[i] > 0 && !start) {
                start = true;
            }
            if (start) {
                resultStr += result[i];
            }
        }
        // corner case
        if ("".equals(resultStr)) {
            resultStr = "0";
        }

        return resultStr;
    }
}

class Solution70 {
    /**
     * Time: O(n); Space: O(n)
     */
    public int climbStairs1(int n) {
        int[] dp = new int[n+1];
        if (n == 1) // corner case: return before base case
            return 1;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * Time: O(n); Space: O(1)
     */
    public int climbStairs2(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        // base case
        int a = 1;
        int b = 2;
        int result = a + b;

        for (int i = 3; i <= n; i++) {
            result = a + b;
            a = b;
            b = result;
        }
        return result;
    }
}