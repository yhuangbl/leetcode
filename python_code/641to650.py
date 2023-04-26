class MyCircularDeque641:

    # Think about edge cases: from empty to become non-empty and 
    # from non-empty to become empty
    def __init__(self, k: int):
        self.max_size = k
        self.curr_size = 0
        self.head = None
        self.end = None

    def insertFront(self, value: int) -> bool:
        if self.curr_size < self.max_size:
            node = Node(value)
            if self.head is not None:
                node.next = self.head
                self.head.prev = node
                self.head = node
            else: # empty
                self.head = node
                self.end = node
            self.curr_size += 1
            return True
        return False
        
    def insertLast(self, value: int) -> bool:
        if self.curr_size < self.max_size:
            node = Node(value)
            if self.end is not None:
                self.end.next = node
                node.prev = self.end
                self.end = node
            else: #empty
                self.head = node
                self.end = node
            self.curr_size += 1
            return True
        return False    

    def deleteFront(self) -> bool:
        if self.head is not None:
            self.head = self.head.next
            if self.head is not None:
                self.head.prev = None
            else: # become empty
                self.end = None
            self.curr_size -= 1
            return True
        return False

    def deleteLast(self) -> bool:
        if self.end is not None:
            self.end = self.end.prev
            if self.end is not None:
                self.end.next = None
            else: # become empty
                self.head = None
            self.curr_size -= 1
            return True
        return False

    def getFront(self) -> int:
        if self.head is not None:
            return self.head.val
        return -1

    def getRear(self) -> int:
        if self.end is not None:
            return self.end.val
        return -1
        
    def isEmpty(self) -> bool:
        return self.curr_size == 0
        
    def isFull(self) -> bool:
        return self.curr_size == self.max_size


class Node: # Double linked list node
    def __init__(self, val: int): 
        self.val = val
        self.next = None
        self.prev = None
        


# Your MyCircularDeque object will be instantiated and called as such:
# obj = MyCircularDeque(k)
# param_1 = obj.insertFront(value)
# param_2 = obj.insertLast(value)
# param_3 = obj.deleteFront()
# param_4 = obj.deleteLast()
# param_5 = obj.getFront()
# param_6 = obj.getRear()
# param_7 = obj.isEmpty()
# param_8 = obj.isFull()