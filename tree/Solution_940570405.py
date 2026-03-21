# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
def pathsumhelper(root,targetsum,res,smallans):
    if root == None:
        return None
    if root.left == None and root.right == None:
        if targetsum - root.val == 0:
            ans1=copy.deepcopy(smallans)
            ans1.append(root.val)
            res.append(ans1)
    smallans.append(root.val)

    pathsumhelper(root.left,targetsum-root.val,res,smallans)
    pathsumhelper(root.right,targetsum-root.val,res,smallans)

    smallans.pop(-1)


class Solution:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> List[List[int]]:
        res=[]
        smallans=[]
        pathsumhelper(root,targetSum,res,smallans)
        return res