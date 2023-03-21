# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution230:
    # In order traversal 
    # Time: O(n); Space: O(n)
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        rank = []
        if root:
            self.kthSmallestHelper(root, rank)
        return rank[k-1] if k-1 < len(rank) else -1

    def kthSmallestHelper(self, root: TreeNode, rank: List[int]):
        if root.left:
            self.kthSmallestHelper(root.left, rank)
        rank.append(root.val)
        if root.right:
            self.kthSmallestHelper(root.right, rank)
    
    # In order traversal + early stopping
    # Time: O(n); Space: O(k)
    def kthSmallest(self, root: Optional[TreeNode], k: int) -> int:
        rank = []
        if root:
            self.kthSmallestHelper(root, rank, k)
        return rank[k-1] if k-1 < len(rank) else -1

    def kthSmallestHelper(self, root: TreeNode, rank: List[int], k: int):
        if root.left:
            self.kthSmallestHelper(root.left, rank, k)
        rank.append(root.val)
        if k-1 < len(rank):
            return
        if root.right:
            self.kthSmallestHelper(root.right, rank, k)