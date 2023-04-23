# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution82:
    def deleteDuplicates(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None or head.next is None:
            return head

        new_head = None
        new_head_ptr = None
        start = head
        while start is not None:
            end = start.next
            if end is None or start.val != end.val: 
                new_node = ListNode(start.val)
                if new_head is None:
                    new_head = new_node
                else:
                    new_head_ptr.next = new_node
                new_head_ptr = new_node
            else:
                while end is not None and start.val == end.val:
                    end = end.next
            start = end

        return new_head