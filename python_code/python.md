## Collections

`from collections import <class name>`

- Counters
- OrderedDict: in the order of key inserted
- DefaultDict
- PriorityQueue: default to min heap (if max heap, priority * -1)
  - get size: `q.qsize()`
  - remove first: `q.get()`
  - get first value but don't remove: `q.queue[0]`

## Generators

- `yield` instead of `return`: used to produce a value from the generator and pause the generator function's execution until the next value is requested
- `yield from <iterable>` v.s. `yield <value>`
- `next(generator, default_value)`: the code within the function is executed up to `yield`

Example: 173
