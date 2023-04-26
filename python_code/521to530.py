class Solution529:
    DIRECTIONS = [[-1, -1], [-1, 0], [-1, 1], [0, -1], [0, 1], [1, -1], [1, 0], [1, 1]]

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        row, col = click[0], click[1]
        if board[row][col] == "M":
            board[row][col] = "X"
            return board
        # if the square is E => Find all adjacent E recursively until boundary/mine
        self.search(board, row, col, len(board), len(board[0]))
        return board
    
    # BFS
    def search(self, board: List[List[str]], row: int, col: int, m: int, n: int):
        if row < 0 or row >= m or col < 0 or col >= n or board[row][col] != 'E':
            return

        num_mines = self.getAdjacentMines(board, row, col, m, n)
        if num_mines != 0:
            board[row][col] = str(num_mines)
        else:
            board[row][col] = 'B'
            for d in self.DIRECTIONS:
                self.search(board, row+d[0], col+d[1], m, n)
    
    def getAdjacentMines(self, board: List[List[str]], row: int, col: int, m: int, n: int) -> int:
        num_mines = 0
        for d in self.DIRECTIONS: 
            new_row, new_col = row+d[0], col+d[1]
            if new_row >= 0 and new_row < m and new_col >= 0 and new_col < n and board[new_row][new_col] == 'M':
                num_mines += 1
        return num_mines

