## Binary Search
- Search a sorted array by repeatedly dividing the search interval in half. Begin with an interval covering the whole array. If the value of the search key is less than the item in the middle of the interval, narrow the interval to the lower half. Otherwise narrow it to the upper half. Repeatedly check until the value is found or the interval is empty.
- Usually use in a **sorted** array
- Time complexity: O(logn)
- can be recursive or iterative
- (https://www.geeksforgeeks.org/binary-search/)
- template:
```
while left <= right:
    mid = (left + right) / 2
    if x == A[mid]:
        return mid
    else if x > A[mid]:
        left = mid + 1
    else:
         right = mid -1
```

## Two pointers
- searching pairs in array, string, linked list
- variation
    - 1 fast pointer & 1 slow pointer (https://leetcode.com/problems/remove-duplicates-from-sorted-array/)
    - 從頭尾 (https://www.geeksforgeeks.org/two-pointers-technique/)

## BFS, DFS & Tree traversal order:
- time complexity: O(N) for binary tree/ O(V+E) for Graph
- DFS: (記root在哪)
    - inorder: left, root, right
    - preorder: root, left, right
    - postorder: left, right, root
    - 用recursion: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    - 用stack for iterative method: https://www.techiedelight.com/depth-first-search/
    - application: cycle detection
- BFS:
    - level-order
    - 用queue (both recursive and iterative): https://www.techiedelight.com/breadth-first-search/
    - find smallest distance (e.g. use Topological Sort)
    - application: Topological Sort

## Backtracking
- Algorithm: Backtracking is an algorithmic-technique for solving problems **recursively** by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints of the problem at any point of time.
- can analyze with a "State Space Tree" (all the possible states (solution or nonsolution) of the problem from the root as an initial state to the leaf as a terminal state.)
- Problem properties: These problems can only be solved by trying every possible configuration and each configuration is tried only once.
- Decision Problem: search for a feasible solution.
- Optimization Problem: search for the best solution.
- Enumeration Problem: search for all feasible solutions.
https://www.geeksforgeeks.org/introduction-to-backtracking-data-structure-and-algorithm-tutorials/
- template (python; [source](https://christianjmills.com/posts/backtracking-notes/index.html))
```
# Check if the current state is a valid solution
def is_valid_state(state):
    # check if is a valid solution
    return True

# Get list of potential next steps
def get_candidates(state):
    return []

# Recursively, perform a depth-first search to find valid solutions
def search(state, solutions):
    # Check is the state is valid
    if is_valid_state(state):
        # Add a copy of the valid state to list of solutions
        solutions.append(state.copy())
        # return # uncomment if you only need to find one valid solution

    # Iterate through the candidates that can be used
    # to construct the next state
    for candidate in get_candidates(state):
        # Add candidate to the current state
        state.add(candidate)
        # Call search function with updated state
        search(state, solutions)
        # Remove the current candidate from the current state
        state.remove(candidate)

# Entry point to the program
# responsible for returning the valid solutions
def solve():
    # start with an empty list of solutions
    solutions = []
    # start with an empty state
    state = set()
    # initiate the recursive search
    search(state, solutions)
    # return the final list of solutions
    return solutions
```

## Quick sort template
```
if left >= right then return
pos = Partition(arr, left, right)
quicksort(A, left, pos - 1)
quicksort(A, pos + 1, right)
```

Partition:
```
pivot = A[right]
boundary = left - 1 // end of small
for i from left to right:
    if A[i] < pivot:
        i++
        swap A[boundary] & A[i]
big_boundary = boundary + 1
swap A[big_boundary] & A[right]
return big_boundary
```

## Merge sort template
```
if left == right then return
mid = (left + right) / 2
mergesort(A, left, mid)
mergesort(A, mid + 1, right)
merge(A, left, mid, right)
```


## Sliding window
Key idea: having computed the sum of 1st window (**fixed** size k), in order to get the sum of the next overlapping window we just need to leave out the leftmost item value and add the new (rightmost) item’s value, so we are essentially saving the re-computation of the sum for the non-changing part of the window.

Template:
```
public class Solution {
    public List<Integer> slidingWindowTemplateByHarryChaoyangHe(String s, String t) {
        //init a collection or int value to save the result according the question.
        List<Integer> result = new LinkedList<>();
        if(t.length()> s.length()) return result;
        
        //create a hashmap to save the Characters of the target substring.
        //(K, V) = (Character, Frequence of the Characters)
        Map<Character, Integer> map = new HashMap<>();
        for(char c : t.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        //maintain a counter to check whether match the target string.
        int counter = map.size();//must be the map size, NOT the string size because the char may be duplicate.
        
        //Two Pointers: begin - left pointer of the window; end - right pointer of the window
        int begin = 0, end = 0;
        
        //the length of the substring which match the target string.
        int len = Integer.MAX_VALUE; 
        
        //loop at the begining of the source string
        while(end < s.length()){
            char c = s.charAt(end);//get a character
            
            if( map.containsKey(c) ){
                map.put(c, map.get(c)-1);// plus or minus one
                if(map.get(c) == 0) counter--;//modify the counter according the requirement(different condition).
            }
            end++;
            
            //increase begin pointer to make it invalid/valid again
            while(counter == 0) { /* counter condition. different question may have different condition */){
                char tempc = s.charAt(begin);//***be careful here: choose the char at begin pointer, NOT the end pointer
                if(map.containsKey(tempc)){
                    map.put(tempc, map.get(tempc) + 1);//plus or minus one
                    if(map.get(tempc) > 0) counter++;//modify the counter according the requirement(different condition).
                }
                
                /* save / update(min/max) the result if find a target*/
                // result collections or result int value
                
                begin++;
            }
        }
        return result;
    }
}
```