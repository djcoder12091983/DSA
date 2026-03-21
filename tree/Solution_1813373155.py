# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:

    def __init__(self):
        self.dp = {}
        self.id = 1

    def maximize(self, node, selected):
        if node is None:
            return 0

        if hasattr(node, 'id') == False:
            # assign id
            node.id = self.id
            self.id += 1

        # id assigned
        key = str(node.id) + '-' + str(selected)
        if key in self.dp:
            return self.dp[key]
        
        # whether current node is selected or not
        # in both cases left and right may not be selected
        left_not = self.maximize(node.left, 0)
        right_not = self.maximize(node.right, 0)

        val = 0
        if selected:
            val += node.val
            # we can't select it's children
            val += left_not
            val += right_not
        else:
            # here we can have two choices either we can select or not select
            val += max(self.maximize(node.left, 1), left_not)
            val += max(self.maximize(node.right, 1), right_not)
        
        # store computed value
        self.dp[key] = val
        return val

    def rob(self, root: Optional[TreeNode]) -> int:
        # either select root or not select
        return max(self.maximize(root, 1), self.maximize(root, 0))