class Solution841:
    '''
    Performance optimization:
    * Use a counter to keep track of visited room
    * Use a stack (dfs) => graph problem can use DFS/BFS so we don't need to constantly copy the keys
    '''
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        keys = set(rooms[0])
        keys.add(0)
        visited = [True] + [False for _ in range(len(rooms)-1)]
        return self.canVisitAllRoomsHelper(rooms, keys, visited)
    
    def canVisitAllRoomsHelper(self, room: List[List[int]], keys: Set[int], visited: List[bool]) -> bool:
        for k in keys:
            visited[k] = True
        if all(visited):
            return True
        for k in keys:
            room_new_keys = set(room[k])
            diff_keys = room_new_keys.difference(keys)
            if diff_keys:
                new_keys = keys.copy()
                new_keys.update(room_new_keys)
                visited_copy = visited.copy()
                if self.canVisitAllRoomsHelper(room, new_keys, visited_copy):
                    return True
        return False