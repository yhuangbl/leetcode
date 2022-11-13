class Solution875 {
    private boolean canFinish(int[] piles, int H, int K) {
        int numHour = 0;

        Queue<Integer> rest = new LinkedList<Integer>();
        for (int num : piles) {
            if (num > K) {
                rest.add(num % K);
                numHour += num / K;
            } else {
                numHour++;
            }
        }

        while (!rest.isEmpty()) {
            int num = rest.remove();
            if (num > K) {
                rest.add(num - K);
            }
            numHour++;
        }
        return numHour <= H;
    }

    /**
     * Time: O(nlogn)
     */
    public int minEatingSpeed(int[] piles, int H) {
        int maxPile = Integer.MAX_VALUE;
        for (int num : piles) {
            maxPile = Math.max(num, maxPile);
        }

        int low = 1;
        int high = maxPile;
        Integer smallest = null;
        while (low <= high) {
            int mid = (low + (high - low) / 2);
            if (canFinish(piles, H, mid)) {
                smallest = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return smallest;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution876 {
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast.next == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast.next == null) {
                break;
            }
            fast = fast.next;
        }
        return slow;
    }
}