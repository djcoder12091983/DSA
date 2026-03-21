"""
# Definition for a Node.
class Node:
    def __init__(self, val: int = 0, left: 'Node' = None, right: 'Node' = None, next: 'Node' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next
"""

class Solution:

    def connect_next(self, node, parent, type):
        if node is None:
            return
        
        # assuming every nodes have exactly two nodes
        if type == 'L':
            node.next = parent.right
        elif type == 'R' and parent.next is not None:
            node.next = parent.next.left

        self.connect_next(node.left, node, 'L')
        self.connect_next(node.right, node, 'R')

        return node

    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        return self.connect_next(root, None, 'S')