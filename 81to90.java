class Solution88 {
    /**
     * Time compleixty: O(m+n); Space complexity: O(m+n)
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] num = new int[m+n];
        int idx = 0;
        int idx1 = 0;
        int idx2 = 0;
        // two pointers: 任何一個pointerc沒跑完都不算結束
        while (idx1 < m || idx2 < n) {
            Integer curr1 = null;
            Integer curr2 = null;
            if (idx1 < m) {
                curr1 = nums1[idx1];
            }
            if (idx2 < n) {
                curr2 = nums2[idx2];
            }

            if (curr1 != null && curr2 != null) {
                if (curr1 <= curr2) {
                    num[idx] = curr1;
                    idx1++;
                }
                else {
                    num[idx] = curr2;
                    idx2++;
                }
            }
            else if (curr1 != null) {
                num[idx] = curr1;
                idx1++;
            }
            else if (curr2 != null) {
                num[idx] = curr2;
                idx2++;
            }
            idx++;
        }

        for (int i = 0; i < (m+n); i++) nums1[i] = num[i];
    }

    /**
     * Time complexity: O(m+n); Space complexity: O(1)
     * <key>: 不要只想著正著，還可以倒著呢
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = m + n - 1;
        int idx1 = m - 1;
        int idx2 = n - 1;

        while (idx1 >= 0 || idx2 >= 0) {
            if (idx1 >= 0 && idx2 >= 0) { // cleaner
                nums1[idx] = (nums1[idx1] > nums2[idx2])? nums1[idx1--]: nums2[idx2--];
            }
            else if (idx1 >= 0) {
                nums1[idx] = nums1[idx1--];
            }
            else if (idx2 >= 0) {
                nums1[idx] = nums2[idx2--];
            }
            idx--;
        }
    }
}