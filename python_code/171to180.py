# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator173:

    # Memory: O(n) because storing all nodes
    # optimize to O(h) => use generator/ only storing the height in the recursion stack!
    def __init__(self, root: Optional[TreeNode]):
        self.rank = []
        self.idx = -1
        if root: 
            self.__init_helper(root, self.rank)
    
    def __init_helper(self, root: TreeNode, rank: List[int]):
        if root.left:
            self.__init_helper(root.left, rank)
        rank.append(root.val)
        if root.right:
            self.__init_helper(root.right, rank)

    def next(self) -> int:
        self.idx += 1
        return self.rank[self.idx] if self.idx < len(self.rank) else -1

    def hasNext(self) -> bool:
        return self.idx < len(self.rank)-1

class BSTIterator173Generator:
    def __init__(self, root: Optional[TreeNode]):
        self.iter = self.__traverse_in_order(root)
        self.nxt = next(self.iter, None) # cannot be named as self.next because it's a reserve keyword
    
    def __traverse_in_order(self, node: Optional[TreeNode]) -> Generator[int, None, None]: # Generator type hint [yield, send, return]
        if node:
            yield from self.__traverse_in_order(node.left)
            yield node.val
            yield from self.__traverse_in_order(node.right)

    def next(self) -> int:
        res, self.nxt = self.nxt, next(self.iter, None)
        return res

    def hasNext(self) -> bool:
        return self.nxt is not None


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()