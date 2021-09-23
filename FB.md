### 2 Add two numbers
Remember the carry

### 46 permutation
dfs with visited array
dfs with swapping O(n!)

### 54 Spiral Matrix
Use colidx, rowidx instead of x,y; it's confusing

### 78 subset
iterative bfs
[]
[] [1]
[] [1] [2] [1,2]
[] [1] [2] [1,2] [3] [2,3] [1,2,3]

### 104 Maximum Depth of Binary Tree
Recursion

### 160 Intersection of Two Linked Lists
Find depth for each node; move forward the deeper one

### 173 Binary Search Tree Iterator
1. flatten inorder traversal
2. stack to stimulate inorder traversal (iterative)

### 199 Binary Tree Right Side View
BFS get the right most at each level

### 207 Course Schedule
topological sort to find cycle

### 215 Kth Largest Element in an Array
quick sort don't forget to do if left <= right!!!!

### 240 Search a 2D Matrix II
binary search (to speed up!) + dfs

### 301 Remove Invalid Parentheses
BFS 爆搜

### 523 Continuous Subarray Sum
dp

### 529 Minesweeper
DFS

### 548 Split Array with Equal Sum
固定中間的pointer; 用set記得移動前面pointer的sum;移動後面pointer看sum在不在set裡面
優化速度: 可以先算好sum

### 560 Subarray Sum Equals K
sum[i]代表sum from 0 to i - 1

sum[i, j] = sum[j] - sum[i]

O(n^3) -> O(n^2)

### 636 Exclusive Time of Functions
Stack

### 865 Smallest Subtree with all the Deepest Nodes
如果左右deepest depth是一樣的 那就是自己

如果不一樣，找deeper的就好

### 896 Monotonic Array

### 953 Verify Alien Dictionary


### 973 K Closest Points to Origin
Priority Queue

### 987 Vertical Order Traversal of a Binary Tree


### 1123 (沒寫過)

### random stuffs
是给一个matrix只包括0和1，求包含1的最小col值。先沟通了暴力解法和时间复杂度，然后在小哥的提醒下，用binary search 查找每一行，然后再比较得出
最小值。最后我提出如果matrix里没有1的情况下，我的解法是有bug的。小哥同意，然后说时间复杂度。我说n*m，小哥说这是binary search，我说但是可能
么有1啊。。最坏情况就是n *m 的，后来沟通说average， 我说那就是n * logm.
