class Solution42 {
    private boolean isCovex(int[] height, int start, int end) {
        int compareH = Math.min(height[start], height[end]);
        for (int i = start + 1; i < end; i++) {
            if (height[i] >= compareH) {
                return false;
            }
        }
        return true;
    }

    /**
     * find Next High 的cases:
     * 4,2,0,3,2,5
     * 4,2,3
     * 4,3,3,9,3,0,9,2,8,3
     */
    private int findNextHigh(int[] height, int idx, int len, int high) {
        if (idx + 1 < len) {
            int low = height[idx + 1];
            if (low >= high) {
                return -1;
            }

            for (int i = len - 1; i > idx; i--) {
                if (isCovex(height, idx, i)) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * Time complexity: O(n^3) ....真糟
     */
    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }

        int len = height.length;
        if (len <= 2) {
            return 0;
        }

        int idx = 0;
        int maxVal = 0;
        int leftHigh = -1;
        // find first leftHigh
        while (idx < len) {
            if (height[idx] > 0) {
                leftHigh = idx;
                break;
            }
            idx++;
        }

        // find rest of the areas
        int area = 0;
        while (leftHigh < len) {
            int rightHigh
                    = findNextHigh(height, leftHigh, len, height[leftHigh]);
            if (rightHigh != -1) {
                int maxArea
                        = (rightHigh - leftHigh - 1)
                        * Math.min(height[leftHigh], height[rightHigh]);
                for (int i = leftHigh + 1; i < rightHigh; i++) {
                    maxArea -= height[i];
                }
                area += maxArea;
                leftHigh = rightHigh;
            }
            else {
                leftHigh++;
            }
        }
        return area;
    }

    /**
     * Better version: two pointers
     * need to find leftHigh and rightHigh; how about just use pointers?
     */
    public int trap(int[] height) {
        if (height == null) {
            return 0;
        }

        int len = height.length;
        if (len <= 2) {
            return 0;
        }

        int left = 0;
        int right = len - 1;
        int rain = 0;
        while (left <= right) {
            int currMin = Math.min(height[left], height[right]);
            if (currMin == height[left]) {
                left++;
                while (left <= right && height[left] < currMin) {
                    rain += (currMin - height[left++]);
                }
            }
            else {
                right--;
                while (left <= right && height[right] < currMin) {
                    rain += (currMin - height[right--]);
                }
            }
        }
        return rain;
    }
}

class Solution46 {
    private List<List<Integer>> permuteHelper(int[] nums, Set<Integer> visited) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean checkAny = false;
        for (int i = 0; i < len; i++) {
            if (!visited.contains(i)) {
                checkAny = true;
                Set<Integer> visitedCopy = new HashSet<Integer>(visited);
                visitedCopy.add(i);
                List<List<Integer>> subResult = permuteHelper(nums, visitedCopy);
                for (List<Integer> r: subResult) {
                    r.add(0, nums[i]);
                }
                result.addAll(subResult);
            }
        }
        if (!checkAny) {
            result.add(new ArrayList());
        }
        return result;

    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return Arrays.asList(Collections.emptyList());
        }
        Set<Integer> visited = new HashSet<Integer>();
        return permuteHelper(nums, visited);
    }
}

class Solution49 {
    /**
     * Time complexity: O(nklogk); n is the length of strs and k is the
     * maximum len of a stirng in strs (klogk is due to the sorting)
     * Space compleixty: O(nk)
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<String, List<String>>();

        for (String str: strs) {
            char temp[] = str.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);

            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<String>());
            }
            anagrams.get(key).add(str);
        }

        // return new ArrayList(anagrams.values())
        List<List<String>> result = new ArrayList<List<String>>();
        for (List<String> val: anagrams.values())  result.add(val);
        return result;
    }

    /**
     * Time compleixty: O(nk); space copmlexity: O(nk)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<String, List<String>>();

        for (String str: strs) {
            int count[] = new int[26];
            Arrays.fill(count, 0);
            for (char c: str.toCharArray()) {
                count[c-'a']++; // index, cannot just put count[c]
            }


            StringBuilder sb = new StringBuilder(""); // starts with an empty string
            for (int c: count) {
                sb.append("#");
                sb.append(c);
            }
            String key = sb.toString();

            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<String>());
            }
            anagrams.get(key).add(str);
        }

        return new ArrayList(anagrams.values())
    }
}

class Solution50 {
    public double myPow(double x, int n) {
        // System.out.println("n: " + n);
        if (x == 0)
            return 0;
        else if (n == 0)
            return 1;
        else if (n == 1)
            return x;

        boolean neg = false;
        boolean isMin = false;
        if (n < 0) {
            neg = true;
            if (n != Integer.MIN_VALUE)
                n *= -1;
            else {
                isMin = true;
                n = Integer.MAX_VALUE;
            }
        }

        // 為什麼 result = myPow(x, n/2) * myPow(x, n/2) 不行？
        // 因為這樣同樣的算式會算兩次 O(logn * logn) 所以還是 > O(logn) -> 會超時
        double intermediate = myPow(x, n/2);
        double result;
        if (n % 2 != 0)
            result = x * intermediate * intermediate;
        else
            result = intermediate * intermediate;
        if (isMin)
            result *= x;
        if (neg)
            result = 1 / result;
        return result;
    }
}