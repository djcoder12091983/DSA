"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""
from collections import deque

class Solution:
    def connect(self, root: 'Node') -> 'Node':
        if not root:
            return root
        # BF will work, DF will not work
        Q = deque()
        Q.append(root)
        while len(Q):

            n = len(Q)
            prev = None
            while n:
                node = Q.popleft()
                if prev:
                    prev.next = node # next pointer
                prev = node
                n -= 1

                if node.left:
                    Q.append(node.left)
                if node.right:
                    Q.append(node.right)

        return root