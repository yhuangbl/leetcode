# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution113:
    # backtracking problem
    # edge case 1: root node is target
    # edge case 2: value might be negative! (currSum might go down to targetSum)
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        output = []
        if root:
            self.pathHelper(root, targetSum, [], 0, output)

        return output
    
    def pathHelper(self, node: TreeNode, targetSum: int, path: List[int], currSum: int, output: List[List[int]]):
        if self.hasFound(currSum, node, targetSum): 
            output.append(path + [node.val])
        else:
            for c in self.getCandidates(node):
                # note here: we need to do path+[node.val] instead of path.append(node.val) and pass in path
                # because path+[node.val] can create a new object and 
                # the other reuse the same object all the way (will have all the nodes ever tried)
                self.pathHelper(c, targetSum, path+[node.val], currSum+node.val, output)

    def getCandidates(self, node: TreeNode) -> List[TreeNode]:
        candidates = []
        if node.left:
            candidates.append(node.left)
        if node.right:
            candidates.append(node.right)
        return candidates
    
    def hasFound(self, currSum: int, node: TreeNode, targetSum: int) -> bool:
        return node.left is None and node.right is None and currSum + node.val == targetSum
    

# cannot use greedy because nodes might be negative.
class Solution120:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        length = len(triangle)
        index = 0
        row = 0
        path_sum = [[triangle[0][0]]]
        best = None
        for r in range(1, length):
            row_sum = []
            for i in range(r+1):
                left = path_sum[r-1][i-1] if i-1 >= 0 else 100000
                right = path_sum[r-1][i] if i < r else 100000
                min_sum = triangle[r][i] + min(left, right)
                row_sum.append(min_sum)
            path_sum.append(row_sum)

        last_row = length-1
        best = path_sum[last_row][0]
        for i in range(1, length):
           curr = path_sum[last_row][i]
           if curr < best:
               best = curr

        return best