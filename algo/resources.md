## Determine time complexity
https://www.geeksforgeeks.org/analysis-of-algorithms-set-4-analysis-of-loops/

https://www.geeksforgeeks.org/analysis-algorithm-set-4-master-method-solving-recurrences/

## algo types
- Basic Problems (Be aware of Type, Overflow!):
  - DP
  - Heap
  - BFS, DFS
  - Recursion
  - Two Pointers
  - Binary Search
  - Bit Manipulation
  - Divide-and-Conquer
  - Number Theory
  - Strings
  - Linked List
  - Trie
  - Sliding windows (substring)
- Advanced Techniques:
  - Z-Value, KMP
  - Suffix Array
  - Fenwick Tree
  - Disjoint Set
  - Segment Tree
  - Max Flow
  - Bipartite Matching
  - NP-Complete Problems
  
## common edge cases
- String
    - Empty string
    - Long string
    - Unicode string (special characters)
    - If limited to a specific set of characters, what happens when some are not in the range
    - Odd/even length string
    - Null (as argument)
    - Non-null terminated
- Integer:
    - 0
    - Min/MaxInt -> overflow
    - Negative/Positive
- Sort algorithm:
    - Empty input
    - 1 element input
    - Very long input (maybe of length max(data type used for index))
    - Garbage inside the collection that will be sorted
    - Null input
    - Duplicate elements
    - Collection with all elements equal
    - Odd/even length input
- Graph
    - directed or undirected
    - always connected?