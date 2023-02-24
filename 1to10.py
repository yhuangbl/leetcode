class Solution2:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        l1p = l1
        l2p = l2
        result = None
        resultp = None
        carry = 0
        while l1p is not None or l2p is not None or carry > 0:
            new_val = (0 if l1p is None else l1p.val) + (0 if l2p is None else l2p.val) + carry
            if new_val >= 10:
                new_val -= 10
                carry = 1
            else:
                carry = 0
            new_node = ListNode(new_val)
            if result is None:
                result = new_node
                resultp = new_node
            else:
                resultp.next = new_node
                resultp = new_node
            
            if l1p is not None:
                l1p = l1p.next
            if l2p is not None:
                l2p = l2p.next
        