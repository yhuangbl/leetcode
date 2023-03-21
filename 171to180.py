# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class BSTIterator173:

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


# Your BSTIterator object will be instantiated and called as such:
# obj = BSTIterator(root)
# param_1 = obj.next()
# param_2 = obj.hasNext()