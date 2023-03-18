class MinStack155:

    def __init__(self):
        self.values = []
        self.min_values = [] 

    def push(self, val: int) -> None:
        self.values.append(val)
        if (not self.min_values) or val <= self.min_values[-1]:
            self.min_values.append(val)

    def pop(self) -> None:
        target = self.values[-1]
        self.values = self.values[:-1]
        if target == self.min_values[-1]:
            self.min_values = self.min_values[:-1]

    def top(self) -> int:
        return self.values[-1]

    def getMin(self) -> int:
        return self.min_values[-1]
        


# Your MinStack object will be instantiated and called as such:
# obj = MinStack()
# obj.push(val)
# obj.pop()
# param_3 = obj.top()
# param_4 = obj.getMin()