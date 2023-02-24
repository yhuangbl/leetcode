# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution142:
    # Speed & Memory: O(n)
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        ptrs = set()
        curr_ptr = head
        while curr_ptr is not None:
            if curr_ptr in ptrs:
                return curr_ptr
            ptrs.add(curr_ptr)
            curr_ptr = curr_ptr.next
        return None