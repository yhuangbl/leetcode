# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution103:
    # BFS; Time: O(n); Space: O(n)
    def zigzagLevelOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        stack = []
        queue = [root] if root else []
        is_queue = True
        output = []

        while (stack and not is_queue) or (queue and is_queue):
            level = []
            if is_queue:
                for i in range(len(queue)):
                    node = queue[i]
                    level.append(queue[i].val)
                    if node.left:
                        stack.append(node.left)
                    if node.right:
                        stack.append(node.right)
                queue = []
                stack_len = len(stack)
                
            else:
                for i in range(stack_len):
                    level.append(stack[stack_len-1-i].val)
                    node = stack[i]
                    if node.left:
                        queue.append(node.left)
                    if node.right:
                        queue.append(node.right)
                stack = []
            
            output.append(level)
            level = []
            is_queue = not is_queue

        return output

class Solution105:
    # Time complexity: O(n^2) => to optimize it: use a dict/hashmap to remember the location!
    def buildTree(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder:
            return None
        
        root_val = preorder[0]
        root = TreeNode(root_val)

        root_val_idx = None
        for i in range(len(inorder)):
            if inorder[i] == root_val:
                root_val_idx = i
                break;       

        if root_val_idx is not None:
            root.left = self.buildTree(preorder[1:1+root_val_idx], inorder[:root_val_idx])
            root.right = self.buildTree(preorder[1+root_val_idx:], inorder[root_val_idx+1:])
        return root

    # Time complexity: O(n)
    def buildTree2(self, preorder: List[int], inorder: List[int]) -> Optional[TreeNode]:
        if not preorder:
            return None
        
        l = len(preorder)
        i_idx_map = {n: i for (i,n) in enumerate(inorder)}
        return self.buildTreeHelper(preorder, inorder, i_idx_map, 0, l-1, 0, l-1)
    
    def buildTreeHelper(self, preorder: List[int], inorder: List[int], i_idx_map: Dict[int, int], p_start, p_end, i_start, i_end) -> Optional[TreeNode]:
        if p_start > p_end or i_start > i_end: 
            return None
        
        root_val = preorder[p_start]
        root = TreeNode(root_val)
        i_idx = i_idx_map[root_val]
        left_len = i_idx - i_start

        root.left = self.buildTreeHelper(preorder, inorder, i_idx_map, p_start+1, p_start+left_len, i_start, i_idx-1)
        root.right = self.buildTreeHelper(preorder, inorder, i_idx_map, p_start+1+left_len, p_end, i_idx+1, i_end)
        return root