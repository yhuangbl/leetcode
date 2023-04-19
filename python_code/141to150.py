# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution142:
    # Time & Memory: O(n)
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        ptrs = set()
        curr_ptr = head
        while curr_ptr is not None:
            if curr_ptr in ptrs:
                return curr_ptr
            ptrs.add(curr_ptr)
            curr_ptr = curr_ptr.next
        return None
    
    # Time: O(n); Memory optimization: O(1) => 2 pointers
    """          Loop is R
       - - - -   After p steps, slow will be at the intersect and fast is P above
      |       |  Everytime, fast can outrun by 1; There are R - P left to run for fast
       - - - -   When they meet, there is R - (R - P) = P left to intersect
      |          So if we increment the head and the slow, they will meet at intersect
    P |
    """
    def detectCycle2(self, head: Optional[ListNode]) -> Optional[ListNode]:
        slow = head
        fast = head
        while fast and fast.next:
            slow, fast = slow.next, fast.next.next
            if slow == fast:
                # detected
                curr = head
                while curr != slow:
                    curr, slow = curr.next, slow.next
                return curr
        return None