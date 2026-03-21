# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def getmid(self, head):
        if not head:
            return None , None , None
        
        if not head.next:
            return None , head, head.next
        slow = fast = head
        prev = None
        while fast and fast.next:
            prev = slow
            slow = slow.next
            fast = fast.next.next
        
        nxt = slow.next if slow else None
        return prev, slow, nxt

    def sortedListToBST(self, head: Optional[ListNode]) -> Optional[TreeNode]:
        if not head:
            return None

        def convertSorted(head):
            if not head:
                return None
            
            prev, mid , nxt = self.getmid(head)
            
            root = TreeNode(mid.val)
            if head.next is None:
                head = None
            if prev:
                prev.next = None
            mid.next = None
            
            root.left  = convertSorted(head)
            root.right = convertSorted(nxt)
            
            return root
        
        return convertSorted(head)