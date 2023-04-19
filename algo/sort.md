| | Time | Space
| **Merge Sort** | O(nlogn) | O(n)
| **Quick Sort** | O(nlogn) | O(nlogn)
| Heap Sort | O(nlogn) | O(1) in place
| Insertion Sort | O(n^2) | O(1)
| Radix Sort | O(nlog(n, k)) | O(n)
| Bucket Sort | O(n) | 

## Merge sort template

```
if left == right then return
mid = (left + right) / 2
mergesort(A, left, mid)
mergesort(A, mid + 1, right)
merge(A, left, mid, right)
```

Merge: 2 pointers to get the smaller one

## Quick sort template

```
if left >= right then return
pos = Partition(A, left, right)
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

## Quick select template

- a selection algorithm to find the k-th smallest element in an unordered list.
- from quick sort
- Time complexity: O(n) on average ([performance comparison between quick sort and quick select](https://stackoverflow.com/questions/56940793/quickselect-time-complexity-explained))

```
while left <= right:
    pivot_idx = Partition(A, left, right)
    if k = pivot_idx
        return A[k]
    else if k > p
      left = p+1
    else
        right = p-1
```